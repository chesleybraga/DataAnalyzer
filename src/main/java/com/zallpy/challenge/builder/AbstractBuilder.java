package com.zallpy.challenge.builder;

import com.zallpy.challenge.util.Constants;
import com.zallpy.challenge.util.Util;

/**
 * @author Chesley Braga
 */
public abstract class AbstractBuilder<T> implements BuilderInterface<T> {

    protected String[] getFields(String line) {
	String[] fields = null;

	if (!Util.isEmpty(line)) {
	    fields = line.split(Constants.SEPARATOR_FIELDS);
	}

	return fields;
    }

    protected void validate(String line) throws Exception {
	String[] fields = getFields(line);

	if (Util.isEmpty(fields) || (fields.length != 4)) {
	    throw new Exception("A linha '" + line + "' é vazia ou não válida");
	}
    }
}