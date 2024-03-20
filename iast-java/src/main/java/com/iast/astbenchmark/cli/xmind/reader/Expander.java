package com.iast.astbenchmark.cli.xmind.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Expander {

    public void expand(File source, File destination) throws IOException, ArchiveException {
        try (FileInputStream fis = new FileInputStream(source); ArchiveInputStream ais =
            new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, fis)) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                if (!ais.canReadEntryData(entry)) {
                    continue;
                }

                File entryFile = new File(destination, entry.getName());

                if (!entryFile.getCanonicalPath().startsWith(destination.getCanonicalPath() + File.separator)) {
                    throw new IOException("Entry is outside of the target destination: " + entry.getName());
                }

                if (entry.isDirectory()) {
                    if (!entryFile.isDirectory() && !entryFile.mkdirs()) {
                        throw new IOException("Failed to create directory: " + entryFile);
                    }
                } else {
                    File parentDir = entryFile.getParentFile();
                    if (!parentDir.isDirectory() && !parentDir.mkdirs()) {
                        throw new IOException("Failed to create directory: " + parentDir);
                    }

                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = ais.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
