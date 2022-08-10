package com.example.demo.domain;

import com.example.demo.logic.PDFPageRearrangement;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PDFBinding {

    public static void processFile(String location, HttpServletResponse response) throws IOException {
        String sourceFile = location;
        String splitFolder = "C:\\Users\\GVUDARAP\\Desktop\\PDF_Binding\\WS\\Splitted";
        String destinationFile = "C:\\Users\\GVUDARAP\\Desktop\\PDF_Binding\\WS\\Resisting_Happiness_Rearranged.pdf";
        int totalPages = SplitIntoPages.split(sourceFile, splitFolder);

        List<Integer> pageSequence = PDFPageRearrangement.getSequence((float)totalPages);
        System.out.println(pageSequence);
        MergerSplitPagesInDesiredOrder.merge(splitFolder, destinationFile, pageSequence,response);
    }

}
