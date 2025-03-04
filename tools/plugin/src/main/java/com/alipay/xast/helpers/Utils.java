/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author Nick Sanidas
 * @created 2015
 */
package com.alipay.xast.helpers;

import org.apache.commons.io.FileUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Utils {

    // Properties used by the generated test suite

    public static final String USERDIR = System.getProperty("user.dir") + File.separator;

    public static final String DATA_DIR = USERDIR + "data" + File.separator;

    public static final DocumentBuilderFactory safeDocBuilderFactory =
            DocumentBuilderFactory.newInstance();

    static {
        try {
            // Make DBF safe from XXE by disabling doctype declarations (per OWASP XXE cheat sheet)
            safeDocBuilderFactory.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (ParserConfigurationException e) {
            System.out.println(
                    "ERROR: couldn't set http://apache.org/xml/features/disallow-doctype-decl");
            e.printStackTrace();
        }
    }

    /**
     * Find the specified file on the class path and return a File handle to it. Note: If the
     * specified file is inside of a JAR on the classpath, this method will throw an error or return
     * null, as you can't return a File object for something inside a JAR. You need to instead
     * return a Stream for that resource.
     *
     * @param fileName The file to retrieve
     * @param classLoader The classloader to use
     * @return A File object referencing the specified file, if found. Otherwise null.
     */
    public static File getFileFromClasspath(String filePath, ClassLoader classLoader) {

        URL url = classLoader.getResource(filePath);
        if (url != null) {
            try {
                System.out.printf("getFileFromClasspath() url is: %s%n", url);
                URI resourceURI = url.toURI();
                String externalFormURI = url.toExternalForm();
                System.out.printf(
                        "getFileFromClasspath() url.toURI() is: %s and external form is: %s%n",
                        resourceURI, externalFormURI);

                if (externalFormURI != null) return new File(externalFormURI);
                else {
                    System.out.printf(
                            "The path for the resource: '%s' with URI: %s is null for some reason."
                                    + " So can't load that resource as a file.%n",
                            filePath, resourceURI);

                    return null;
                }
            } catch (URISyntaxException e) {
                System.out.printf(
                        "The path for the resource: '%s' can't be computed due to the following error:%n",
                        filePath);
                e.printStackTrace();
            }
        } else System.out.printf("The file '%s' cannot be found on the classpath.%n", filePath);
        return null;
    }

    /**
     * Read the given text file and return the contents.
     *
     * @param file The file to read
     * @return The file contents
     */
    public static List<String> getLinesFromFile(File file) {
        if (file == null) {
            System.out.println("ERROR: getLinesFromFile() invoked with null file parameter.");
            return null;
        }
        String filename = file.getName();
        try {
            try {
                filename = file.getCanonicalPath();
            } catch (IOException e) {
                // Do nothing, thus using default getName() value.
            }
            return getLinesFromStream(new FileInputStream(file), filename);
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file to get lines from: " + filename);
            return null;
        }
    }

    /**
     * Read the given text file and return the contents.
     *
     * @param filePath The file to read
     * @return The file contents
     */
    public static List<String> getLinesFromFile(String filePath) {
        return getLinesFromFile(new File(filePath));
    }

    public static List<String> getLinesFromStream(InputStream fileStream, String sourceFileName) {

        List<String> sourceLines = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sourceLines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Problem reading contents of file stream: " + sourceFileName);
            e.printStackTrace();
        }

        return sourceLines;
    }

    /**
     * Load a list of requests for a generated test suite from the given file.
     *
     * @param file The file to parse
     * @return A list of requests
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public static TestSuite parseHttpFile(File file) throws JAXBException, FileNotFoundException {

        TestSuite testSuite = null;
        JAXBContext context = JAXBContextFactory.createContext(new Class[] {TestSuite.class}, null);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
        testSuite = (TestSuite) unmarshaller.unmarshal(new FileReader(file));

        return testSuite;
    }

    /**
     * A utility method to read all the lines out of a CSV file that indicate failed test cases.
     *
     * @param csvFile The file to read.
     * @return A List of all the lines in the .csv that indicate a test case failure.
     */
    public static List<String> readCSVFailedTC(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        List<String> csv = new ArrayList<String>();
        String[] tempLine;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                tempLine = line.split(cvsSplitBy);
                if (tempLine[5].trim().equalsIgnoreCase("fail")) {
                    csv.add(tempLine[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csv;
    }

    // from: https://stackoverflow.com/questions/1386809/copy-directory-from-a-jar-file
    // answer by: lpiepiora.  Example usage:
    // copyFromJar("/path/to/the/dir/in/jar", Paths.get("/tmp/from-jar"))
    // Modified by DRW to handle individual source files, not just directories

    /**
     * This method copies all the files starting at source, to target. Source can be a file or
     * directory on the filesystem, or a file or directory inside a JAR file.
     *
     * @param source the file or directory to start copying from
     * @param target the destination directory
     */
    public static void copyFilesFromDirRecursively(String source, final Path target) {

        // In case the caller provides a trailing /
        if (source.endsWith("/")) source = source.substring(0, source.length() - 1);
        final String sourceLoc = source;

        final ClassLoader CL = Utils.class.getClassLoader();
        final URL srcURL = CL.getResource(sourceLoc);

        if (srcURL == null) {
            System.out.printf(
                    "ERROR: copyFilesFromDirRecursively() can't find source resource: %s%n",
                    sourceLoc);
            return;
        }

        if (srcURL.getProtocol().equals("file")) {
            // Copy the files from one directory to another using the normal
            // FileUtils.copyDirectory() method

            File sourceFile;
            try {
                sourceFile = Paths.get(srcURL.toURI()).toFile();
            } catch (URISyntaxException e1) {
                // This should never happen since CL.getResource() found it
                e1.printStackTrace();
                return;
            }
            if (sourceFile.exists())
                if (sourceFile.isDirectory())
                    try {
                        FileUtils.copyDirectory(sourceFile, target.toFile(), false);
                    } catch (IOException e) {
                        System.out.println("ERROR: couldn't copyDirectory()");
                        e.printStackTrace();
                    }
                else
                    // Simply copy file to target dir
                    try {
                        Files.copy(
                                sourceFile.toPath(),
                                Paths.get(target.toString(), sourceFile.getName()),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println("ERROR: couldn't copy source file to target directory,");
                        e.printStackTrace();
                    }
            else
                System.out.printf(
                        "ERROR: copyFilesFromDirRecursively() can't find source File: %s%n",
                        sourceLoc);

            return; // File(s) copied or it failed
        }

        if (!srcURL.getProtocol().equals("jar")) {
            System.out.printf(
                    "ERROR: source resource not a file: or jar: resource. It is: %s%n", srcURL);
            return;
        }

        // Copy the files out of the JAR to the target dir using the stackoverflow
        // copy-directory-from-a-jar-file solution
        URI resource;
        try {
            //            resource = CL.getResource("").toURI();
            resource = CL.getResource(sourceLoc).toURI();
        } catch (URISyntaxException e2) {
            System.out.printf("ERROR: couldn't find resource: %s%n", sourceLoc);
            e2.printStackTrace();
            return;
        }

        FileSystem fileSystem;
        try {
            // If the target location already exists, use it.
            fileSystem = FileSystems.getFileSystem(resource);
        } catch (FileSystemNotFoundException e) {
            try {
                // Otherwise create it
                fileSystem =
                        FileSystems.newFileSystem(resource, Collections.<String, String>emptyMap());
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }

        // The following is done to calculate whether the sourceLoc is a file or directory
        // This is needed later in visitFile() to calculate the properly replacement path
        // when copying a single file, rather than a directory of files.
        String jarSource = sourceLoc; // Default location

        URL jarURL = Utils.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            JarFile myJar = new JarFile(new File(jarURL.toURI()));
            JarEntry entry = myJar.getJarEntry(sourceLoc);
            if (entry == null) {
                System.out.printf("ERROR: Target resource file: '%s' can't be found%n", sourceLoc);
                return;
            } else if (!entry.isDirectory()) {
                // IF the source is not a directory, we use the directory containing the file as
                // the jarPath
                int slashLoc = sourceLoc.lastIndexOf('/');
                if (slashLoc == -1)
                    slashLoc = 0; // In case there is no containing directory. TODO: UNTESTED
                jarSource = sourceLoc.substring(0, slashLoc);
            }
        } catch (IOException | URISyntaxException e) {
            System.out.printf(
                    "ERROR trying to determine if: '%s' is a file or directory.%n", sourceLoc);
            e.printStackTrace();
            return;
        }
        final String jarSourcePath = jarSource;

        Path jarPath = fileSystem.getPath("/" + sourceLoc);

        try {
            Files.walkFileTree(
                    jarPath,
                    new SimpleFileVisitor<Path>() {

                        @Override
                        public FileVisitResult preVisitDirectory(
                                Path dir, BasicFileAttributes attrs) throws IOException {
                            Files.createDirectories(
                                    target.resolve(jarPath.relativize(dir).toString()));
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                throws IOException {
                            String targetLoc = file.toString().substring(1);
                            targetLoc = targetLoc.replace(jarSourcePath, target.toString());
                            Path targetPath = new File(targetLoc).toPath();
                            Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
            System.out.println("ERROR trying to copy resources from JAR file to file system.");
            e.printStackTrace();
        }
    }
}
