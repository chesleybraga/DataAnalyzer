package com.zallpy.challenge.factory;

import com.zallpy.challenge.builder.BuilderInterface;
import com.zallpy.challenge.builder.ClientBuilder;
import com.zallpy.challenge.builder.SaleBuilder;
import com.zallpy.challenge.builder.SalesmanBuilder;
import com.zallpy.challenge.util.Constants;

/**
 * @author Chesley Braga
 */
public class BuilderFactory implements BuilderFactoryInterface<BuilderInterface<?>> {

    private static BuilderFactory uniqueInstance;

    private BuilderFactory() {
    }

    public static synchronized BuilderFactory getInstance() {
	if (uniqueInstance == null) {
	    uniqueInstance = new BuilderFactory();
	}

	return uniqueInstance;
    }

    @Override
    public BuilderInterface<?> getBuilder(String line) throws Exception {
	BuilderInterface<?> builder = null;

	if (line.startsWith(Constants.CODE_SALESMAN)) {
	    builder = new SalesmanBuilder();
	} else if (line.startsWith(Constants.CODE_CLIENT)) {
	    builder = new ClientBuilder();
	} else if (line.startsWith(Constants.CODE_SALE)) {
	    builder = new SaleBuilder();
	}

	if (builder == null) {
	    throw new Exception("A linha '" + line + "' é vazia ou não válida");
	}

	return builder;
    }
}