package com.alipay.xast.score.parsers;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alipay.xast.score.ResultFile;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public abstract class ReaderTestBase {

    // This list is used in the next test
    private static final List<Reader> THE_READERS = Reader.allReaders();

    @Test
    public void assertReaderIsInReaderAllReadersList() {
        boolean readerInList = false;
        String thisReaderName = this.getClass().getSimpleName(); // This gets ReaderNameTEST
        thisReaderName = thisReaderName.substring(0, thisReaderName.length() - "TEST".length());
        for (Reader reader : THE_READERS) {
            if (reader.getClass().getSimpleName().equals(thisReaderName)) {
                readerInList = true;
                break;
            }
        }
        assertTrue(
                readerInList,
                "Reader " + thisReaderName + " must be added to Reader.allReaders() list");
    }

    protected void assertOnlyMatcherClassIs(ResultFile resultFile, Class<? extends Reader> c) {
        List<Class<?>> readers =
                Reader.allReaders().stream()
                        .filter(r -> r.canRead(resultFile))
                        .map(Reader::getClass)
                        .collect(Collectors.toList());

        assertEquals(simpleNames(singletonList(c)), simpleNames(readers));

        assertTrue(readers.get(0).isAssignableFrom(c));
    }

    private List<String> simpleNames(List<Class<?>> classList) {
        return classList.stream().map(Class::getSimpleName).collect(Collectors.toList());
    }
}
