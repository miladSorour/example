package org.milad.example.converFile;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DocxToPdfConverter {
    public static void main(String[] args) throws Exception {
        String inputDocx = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/LetterContent.docx";
        String outputPdf = "/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/output.pdf";

        // Load the Word document
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inputDocx));

       /* // Embed font for Persian support (example: IRANSans.ttf or tahoma.ttf)
        wordMLPackage.setFontMapper(new org.docx4j.fonts.IdentityPlusMapper());
        org.docx4j.fonts.Mapper fontMapper = wordMLPackage.getFontMapper();
        fontMapper.put("Tahoma", PhysicalFonts.get("path/to/tahoma.ttf")); // match DOCX font*/


        try (OutputStream os = new FileOutputStream(outputPdf)) {
            Docx4J.toPDF(wordMLPackage, os);

            System.out.println("PDF created successfully at: " + outputPdf);
        }
    }
}
