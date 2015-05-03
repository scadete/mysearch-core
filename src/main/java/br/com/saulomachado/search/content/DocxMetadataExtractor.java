package br.com.saulomachado.search.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class DocxMetadataExtractor implements MetadataExtractorInterface {

	private static final Logger log = LoggerFactory.getLogger(DocxMetadataExtractor.class);
	
	public Map<String, Serializable> extractMetadata(InputStream is) {
		try {
			log.debug("Extracting metadata...");
			OOXMLParser parser = new OOXMLParser();
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			parser.parse(is, handler, metadata, context);
			log.debug("Finished extracting metadata.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
