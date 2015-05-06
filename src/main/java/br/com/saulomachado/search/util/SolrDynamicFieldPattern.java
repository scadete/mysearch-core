package br.com.saulomachado.search.util;

public interface SolrDynamicFieldPattern {
	public String SUFFIX_INT = "_i";
	public String SUFFIX_INT_MULTIVALUED = "_is";
	public String SUFFIX_STRING = "_s";
	public String SUFFIX_STRING_MULTIVALUED = "_ss";
	public String SUFFIX_LONG = "_l";
	public String SUFFIX_LONG_MULTIVALUED = "_ls";
	public String SUFFIX_TEXT_GENERAL = "_t";
	public String SUFFIX_TEXT_GENERAL_MULTIVALUED = "_txt";
	public String SUFFIX_TEXT_EN = "_en";
	public String SUFFIX_BOOLEAN = "_b";
	public String SUFFIX_BOOLEAN_MULTIVALUED = "_bs";
	public String SUFFIX_FLOAT = "_f";
	public String SUFFIX_FLOAT_MULTIVALUED = "_fs";
	public String SUFFIX_DOUBLE = "_d";
	public String SUFFIX_DOUBLE_MULTIVALUED = "_ds";
	public String SUFFIX_DATE = "_dt";
	public String SUFFIX_DATE_MULTIVALUED = "_dts";
}
