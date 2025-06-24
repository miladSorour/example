package org.milad.example.converFile;

import org.jodconverter.local.LocalConverter;
import org.jodconverter.local.office.LocalOfficeManager;

import java.io.File;

public class WordToPdfConverter {



    public static void main(String[] args)  throws Exception {
        String docxPath = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/LetterContent.docx";
        String pdfPath = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/output.png";

        // Start LibreOffice manager
        LocalOfficeManager.builder().officeHome("/Applications/LibreOffice.app/Contents").build();
        LocalOfficeManager officeManager = LocalOfficeManager.install();
        officeManager.start();

        // Input Word file and intermediate PDF
        File inputDocx = new File(docxPath);
        File outputPdf = new File(pdfPath);

        // Convert DOCX to PDF
        LocalConverter
                .make(officeManager)
                .convert(inputDocx)
                .to(outputPdf)
                .execute();

        System.out.println("Converted DOCX to PDF.");

        // Stop LibreOffice
        officeManager.stop();
    }
}