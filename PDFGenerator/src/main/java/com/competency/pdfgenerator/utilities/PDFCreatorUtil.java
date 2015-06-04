/**
 * 
 */
package com.competency.pdfgenerator.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 * @author NES Developer
 *
 */
public class PDFCreatorUtil {
	
	private static BaseFont baseArabicFont;
	private static BaseFont baseNormalFont;
	
	public static Chunk formatChunk(String valueToBePutInPdf , boolean needsEncodingChange) throws UnsupportedEncodingException{
		Chunk chunk = new Chunk(valueToBePutInPdf);
		if(needsEncodingChange)
			chunk = new Chunk(valueToBePutInPdf !=null ? new String(valueToBePutInPdf.getBytes(PDFConstants.ENCODING_ISO_8859_1), PDFConstants.ENCODING_UTF_8) : PDFConstants.BLANK_VALUE);
		if(null != chunk.getContent() && chunk.getContent().toCharArray().length > 0){
			if(checkTextForArabic(chunk)){
				chunk.setFont(getArabicFont());
			}else{
				chunk.setFont(getNormalFont());
			}
		}
		return chunk;
	}

	/**
	 * @param chunk
	 * @return
	 */
	private static boolean checkTextForArabic(Chunk chunk) {
		boolean isArabic = false;
		for(char currentChar : chunk.getContent().toCharArray()){
			if(Character.UnicodeBlock.of(currentChar) == Character.UnicodeBlock.ARABIC){
				isArabic = true;
			}
		}
		return isArabic; 
	}

	private static Font getNormalFont() {
		Font font;
		try {
			if(null == baseNormalFont)
				baseNormalFont = BaseFont.createFont(PDFConstants.class.getClassLoader().getResource("FontFiles/arabtype.ttf").getPath() , BaseFont.IDENTITY_H, true);
			font = new Font(baseNormalFont);
			font.setSize(12.0f);
		} catch (DocumentException fontCreationexception) {
			fontCreationexception.printStackTrace();
			font = new Font();
		}catch (IOException fontCreationexception) {
			fontCreationexception.printStackTrace();
			font = new Font();
		}
		return font;
	}

	private static Font getArabicFont() {
		Font font;
		try {
			if(null == baseArabicFont)
				baseArabicFont = BaseFont.createFont(PDFConstants.class.getClassLoader().getResource("FontFiles/DroidKufiRegular.ttf").getPath() , BaseFont.IDENTITY_H, true);
			font = new Font(baseArabicFont);
			font.setSize(8.0f);
		} catch (DocumentException fontCreationexception) {
			fontCreationexception.printStackTrace();
			font = new Font();
		} catch (IOException fontCreationexception) {
			fontCreationexception.printStackTrace();
			font = new Font();
		}
		return font;
	}
	
}
