package br.com.saulomachado.search.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Property;
import org.apache.tika.metadata.Property.PropertyType;
import org.apache.tika.metadata.Property.ValueType;

import br.com.saulomachado.search.exception.NotImplementPropertyConversionException;
import br.com.saulomachado.search.util.SolrDynamicFieldPattern;

public abstract class AbstractSolrInputDocumentBuilder {

	private static final String TEXT_CONTENT_FIELD_NAME = "content:plain-text";
	private static final String FILE_FIELD_NAME = "meta:file-name";

	public SolrInputDocument build(File f) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(f);
		SolrInputDocument sid = build(fis);
		sid.addField(FILE_FIELD_NAME + SolrDynamicFieldPattern.SUFFIX_STRING,
				f.getName());
		return sid;
	}

	public abstract SolrInputDocument build(InputStream is);

	protected SolrInputDocument buildSolrInputDocument(Metadata metadata,
			String textContent, Class<?> propCollectionClass)
			throws IllegalArgumentException, IllegalAccessException,
			NotImplementPropertyConversionException {

		SolrInputDocument solrInputDocument = new SolrInputDocument();

		solrInputDocument.addField("id", UUID.randomUUID().toString());
		solrInputDocument.addField(TEXT_CONTENT_FIELD_NAME
				+ SolrDynamicFieldPattern.SUFFIX_TEXT_GENERAL, textContent);

		Field[] fields = propCollectionClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().equals(Property.class)) {
				Property property = (Property) field.get(null);
				String value = metadata.get(property);
				if (value != null) {
					solrInputDocument.addField(getSolrFieldName(property),
							value);
				}
			}
		}

		return solrInputDocument;
	}

	private String getSolrFieldName(Property property)
			throws IllegalAccessException,
			NotImplementPropertyConversionException {

		ValueType valueType = property.getValueType();
		String propertyName = property.getName();
		switch (valueType) {
		case DATE:
			return propertyName + SolrDynamicFieldPattern.SUFFIX_DATE;
		case INTEGER:
			return propertyName + SolrDynamicFieldPattern.SUFFIX_INT;
		case TEXT:
			if (property.getPropertyType().equals(PropertyType.BAG)) {
				return propertyName
						+ SolrDynamicFieldPattern.SUFFIX_TEXT_GENERAL_MULTIVALUED;
			} else {
				return propertyName
						+ SolrDynamicFieldPattern.SUFFIX_TEXT_GENERAL;
			}
		default:
			throw new NotImplementPropertyConversionException(
					valueType.toString());
		}
	}
}
