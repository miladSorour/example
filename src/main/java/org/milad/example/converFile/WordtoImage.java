package org.milad.example.converFile;

import com.spire.doc.*;
import com.spire.doc.documents.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class WordtoImage {
    public static void main(String[] args) throws Exception {

        //Create a Document instance
        Document document = new Document();
        //Load a Word document
        document.loadFromFile("/Users/miladsorour/IdeaProjects/interview/example/src/main/resources/LetterContent.docx");

        //Save the first page to a .png image
        BufferedImage image= document.saveToImages(0, ImageType.Bitmap);

        File file= new File("ToPNG.png");
        ImageIO.write(image, "PNG", file);
        }
    }