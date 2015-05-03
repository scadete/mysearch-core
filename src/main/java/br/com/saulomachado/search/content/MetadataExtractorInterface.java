package br.com.saulomachado.search.content;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

public interface MetadataExtractorInterface {

	public Map<String, Serializable> extractMetadata(InputStream is);
}
