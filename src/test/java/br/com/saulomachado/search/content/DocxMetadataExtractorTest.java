package br.com.saulomachado.search.content;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.junit.Test;

import br.com.saulomachado.search.content.DocxMetadataExtractor;

public class DocxMetadataExtractorTest {

	@Test
	public void docxMetadataExtractorTest() {
		try {
			InputStream sampleStream01 = this.getClass().getClassLoader().getResourceAsStream("docx/01.docx");
			InputStream sampleStream02 = this.getClass().getClassLoader().getResourceAsStream("docx/80.docx");
			
			DocxMetadataExtractor metaExtractor = new DocxMetadataExtractor();
			metaExtractor.extractMetadata(sampleStream01);
			
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
