package com.jobmatcher.utility.parsers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

/**
 * Created by gevlad on 19-Jan-17.
 */
public class Parser {

    public static String parse(String fileName) throws IOException {

        String st = null;
        PDDocument document = null;

        try {

            document = PDDocument.load(new File(fileName));
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                st = Tstripper.getText(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            document.close();  //<-
        }

        return st;
    }
}


