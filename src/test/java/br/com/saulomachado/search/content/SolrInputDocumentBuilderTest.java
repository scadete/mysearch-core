package br.com.saulomachado.search.content;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrInputDocumentBuilderTest {

	@Test
	public void docxSolrInputDocumentBuilderTest() {
		try {
			InputStream sampleStream01 = this.getClass().getClassLoader()
					.getResourceAsStream("docx/sample.docx");

			DocxSolrInputDocumentBuilder sidBuilder = new DocxSolrInputDocumentBuilder();
			SolrInputDocument sid = sidBuilder.build(sampleStream01);
			assertTrue(sid.getFieldNames().size() > 0);

		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
