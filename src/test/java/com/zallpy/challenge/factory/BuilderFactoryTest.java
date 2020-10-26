package com.zallpy.challenge.factory;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.zallpy.challenge.builder.BuilderInterface;
import com.zallpy.challenge.builder.ClientBuilder;
import com.zallpy.challenge.builder.SaleBuilder;
import com.zallpy.challenge.builder.SalesmanBuilder;

/**
 * @author Chesley Braga
 */
public class BuilderFactoryTest {

    private BuilderFactory factory = BuilderFactory.getInstance();

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getBuilderSalesmanTest() throws Exception {
	String line = "001ç1234567891234çPedroç50000";
	BuilderInterface<?> builder = factory.getBuilder(line);

	Assert.assertTrue(builder instanceof SalesmanBuilder);
    }

    @Test
    public void getBuilderClientTest() throws Exception {
	String line = "002ç2345675434544345çJose da SilvaçRural";
	BuilderInterface<?> builder = factory.getBuilder(line);

	Assert.assertTrue(builder instanceof ClientBuilder);
    }

    @Test
    public void getBuilderSaleTest() throws Exception {
	String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
	BuilderInterface<?> builder = factory.getBuilder(line);

	Assert.assertTrue(builder instanceof SaleBuilder);
    }

    @Test
    public void getBuilderExceptionTest() throws Exception {
	thrown.expect(Exception.class);

	String line = "004ç02çtesteçChesley";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	factory.getBuilder(line);
    }
}