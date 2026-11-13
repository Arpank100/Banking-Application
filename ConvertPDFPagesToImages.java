package com.app.eshiksa.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ConvertPDFPagesToImages {
	public String convertPdfToImage(String sourcePath, String destinationPath, String txnId) {
		try {
			String sourceDir = sourcePath; // Pdf
			String destinationDir = destinationPath; // converted
			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				// System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				// System.out.println("Images copied to Folder: " + destinationFile.getName());
				PDDocument document = PDDocument.load(sourceFile);
				PDFRenderer renderer = new PDFRenderer(document);

				// Rendering an image from the PDF document
				BufferedImage image = renderer.renderImage(0);

				// Writing the image to a file
				ImageIO.write(image, "JPEG", new File(destinationPath + "/" + txnId));

//				System.out.println("Image created");

				// Closing the document
				document.close();
				return destinationPath + txnId;
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}