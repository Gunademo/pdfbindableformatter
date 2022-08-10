package com.example.demo.logic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PDFPageRearrangement {

    public static List<Integer> getSequence(float pages) {
        float totalPagesInBook = pages;
        double noOfPapersRequired = Math.ceil(totalPagesInBook / 4);
        int noOfBlanks = (int) ((4 * noOfPapersRequired) - totalPagesInBook);


        int[] finalSequence = new int[(int) (totalPagesInBook + noOfBlanks)];
        int count = 0;
        for (int i = 1; i < finalSequence.length - 1; i++) {
            finalSequence[i++] = ++count;
            finalSequence[i] = ++count;
            i++;
            i++;
        }
        finalSequence[finalSequence.length - 1] = ++count;
        for (int i = finalSequence.length - 4; i >= 0; i--) {
            if (count >= totalPagesInBook) {
                finalSequence[i--] = -1;
                if (i >= 0)
                    finalSequence[i] = -1;
                i--;
                i--;
                continue;
            }
            finalSequence[i--] = ++count;
            if (i >= 0)
                finalSequence[i] = ++count;
            i--;
            i--;
        }


        return Arrays.stream(finalSequence).boxed().collect(Collectors.toList());


    }
}
