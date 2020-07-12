package com.disney.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class XmlComparatorTest {
    private XmlComparator comparator;
    String basePath;

    public XmlComparatorTest() {
        File currDir = new File(".");
        basePath = currDir.getAbsolutePath();
        basePath = basePath.substring(0, basePath.length() - 1);

        comparator = new XmlComparator();
    }

    @Test
    public void whenCompareDifferentFiles_thenDifferencesFound() throws Exception {
        System.out.println("Compare 2 XML files with differences");

        String xmlPath1 = basePath + "file\\XMLCompareFile1.xml";
        String xmlPath2 = basePath + "file\\XMLCompareFile2.xml";

        CompareResult compareResult = comparator.compare(xmlPath1, xmlPath2);

        System.out.println(compareResult.toString());
        Assert.assertEquals(compareResult.areEqual, false);
    }

    @Test
    public void whenCompareSameFiles_thenDifferencesNotFound() throws Exception {
        System.out.println("Compare 2 XML files with no differences");

        String xmlPath1 = basePath + "file\\XMLCompareFile1.xml";
        String xmlPath2 = basePath + "file\\XMLCompareFile1_copy.xml";

        CompareResult compareResult = comparator.compare(xmlPath1, xmlPath2);

        System.out.println(compareResult.toString());
        Assert.assertEquals(compareResult.areEqual, true);
    }

    @Test
    public void whenCompareFilesWithDiffAttributes_thenDifferencesFound() throws Exception {
        System.out.println("Compare 2 XML files with no differences");

        String xmlPath1 = basePath + "file\\xml1.xml";
        String xmlPath2 = basePath + "file\\xml2.xml";

        CompareResult compareResult = comparator.compare(xmlPath1, xmlPath2);
        System.out.println(compareResult.toString());
        Assert.assertEquals(compareResult.areEqual, false);
    }
}