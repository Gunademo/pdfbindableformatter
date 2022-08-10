package com.example.demo.domain;

import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MergerSplitPagesInDesiredOrder {

    public static void merge(String splitFolder, String destinationFile, List<Integer> sequence, HttpServletResponse response) throws IOException {
        //Loading an existing PDF document
        File file1;
        PDDocument document1 = new PDDocument();
        PDPage blankPage;

        //Create PDFMergerUtility class object
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        
        //Setting the destination file path
        PDFmerger.setDestinationFileName(destinationFile);


        for (int i: sequence) {
            if (i==-1){
                file1 = new File(splitFolder+"\\blank.pdf");
            }else{
                file1 = new File(splitFolder+"\\page-"+i+".pdf");
            }
            document1 = PDDocument.load(file1);
            PDFmerger.addSource(file1);
            document1.close();
        }

    //Merging the documents
        PDFmerger.mergeDocuments(null);

        System.out.println("PDF Documents merged to a single file successfully");

    //Close documents
        document1.close();

    }
}
