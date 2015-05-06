package br.com.saulomachado.search.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import br.com.saulomachado.search.content.DocxSolrInputDocumentBuilder;
import br.com.saulomachado.search.crawler.FileSystemCrawler;

public class StandaloneJarApplication {

	public static void main(String[] args) {
		if (args.length != 2)
		{
			System.err.println("Invalid argument count. Expected: <solrServerUrl> <rootDirectory>.");
			System.exit(-1);
		}
		
		List<File> fileList = FileSystemCrawler.fetchFilesFromDirectory(args[1], ".docx");

		DocxSolrInputDocumentBuilder sidBuilder = new DocxSolrInputDocumentBuilder();
		SolrClient solr = new HttpSolrClient(args[0]);
		try {
			for (File file : fileList) {
				try {
					solr.add(sidBuilder.build(file));
				} catch (FileNotFoundException e) {
					System.err.println("File not found: " + file.getAbsoluteFile());
				} catch (SolrServerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				solr.commit();
				solr.optimize();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} finally {
			try{solr.close();}catch(Exception e){}
		}
	}

}
