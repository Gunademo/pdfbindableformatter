package com.example.demo.domain;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SplitIntoPages {

    public static int split(String srcLocation, String destFolder) throws IOException {
        //Loading an existing PDF document
        File file = new File(srcLocation);
        PDDocument document = PDDocument.load(file);

        // Create a Splitter object
        Splitter splitter = new Splitter();

        //splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(document);

        //Creating an iterator object
        Iterator<PDDocument> iterator = Pages.listIterator();

        //saving splits as individual PDF document
        PDDocument pd = new PDDocument();

        PDPage blankPage = new PDPage();
        pd.addPage( blankPage );
        pd.save(destFolder+"\\blank.pdf");

        int i = 1;
        while (iterator.hasNext()) {
            pd = iterator.next();
            pd.save(destFolder+"\\page-" + i++ + ".pdf");
        }


        System.out.println("Multiple PDF files are created successfully.");
        document.close();
        return --i;
    }
}
