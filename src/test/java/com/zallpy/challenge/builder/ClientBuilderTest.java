package com.zallpy.challenge.builder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.zallpy.challenge.vo.Client;

/**
 * @author Chesley Braga
 */
public class ClientBuilderTest {

    private ClientBuilder builder = new ClientBuilder();

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buildTest() throws Exception {
	String expectedCode = "002";
	String expectedCnpj = "2345675434544345";
	String expectedName = "Jose da Silva";
	String expectedBusinessArea = "Rural";
	String expectedToString = "Client [code=002, cnpj=2345675434544345, name=Jose da Silva, businessArea=Rural]";
	String line = "002ç2345675434544345çJose da SilvaçRural";

	Client actual = builder.build(line);
	Assert.assertEquals(expectedCode, actual.getCode());
	Assert.assertEquals(expectedCnpj, actual.getCnpj());
	Assert.assertEquals(expectedName, actual.getName());
	Assert.assertEquals(expectedBusinessArea, actual.getBusinessArea());
	Assert.assertEquals(expectedToString, actual.toString());
    }

    @Test
    public void buildEmptyTest() throws Exception {
	thrown.expect(Exception.class);

	String line = "";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }

    @Test
    public void buildMissingFieldsTest() throws Exception {
	thrown.expect(Exception.class);

	String line = "002ç2345675434544345çJose da Silva";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }
}