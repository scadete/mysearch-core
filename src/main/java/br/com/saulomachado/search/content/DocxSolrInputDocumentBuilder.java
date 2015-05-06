package br.com.saulomachado.search.content;

import java.io.IOException;
import java.io.InputStream;

import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Office;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import br.com.saulomachado.search.exception.NotImplementPropertyConversionException;

public class DocxSolrInputDocumentBuilder extends
		AbstractSolrInputDocumentBuilder {

	private static final Logger log = LoggerFactory
			.getLogger(DocxSolrInputDocumentBuilder.class);

	public SolrInputDocument build(InputStream is) {
		try {
			log.debug("Parsing content...");
			OOXMLParser parser = new OOXMLParser();
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			parser.parse(is, handler, metadata, context);

			log.debug("Building input document...");
			return buildSolrInputDocument(metadata, handler.toString(),
					Office.class);
		} catch (IOException | IllegalArgumentException
				| IllegalAccessException | SAXException | TikaException
				| NotImplementPropertyConversionException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
