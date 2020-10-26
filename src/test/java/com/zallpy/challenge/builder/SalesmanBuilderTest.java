package com.zallpy.challenge.builder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.zallpy.challenge.vo.Salesman;

/**
 * @author Chesley Braga
 */
public class SalesmanBuilderTest {

    private SalesmanBuilder builder = new SalesmanBuilder();

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buildTest() throws Exception {
	String expectedCode = "001";
	String expectedCpf = "1234567891234";
	String expectedName = "Pedro";
	double expectedSalary = 50000;
	String expectedToString = "Salesman [code=001, cpf=1234567891234, name=Pedro, salary=50000.0]";
	String line = "001ç1234567891234çPedroç50000";

	Salesman actual = builder.build(line);
	Assert.assertEquals(expectedCode, actual.getCode());
	Assert.assertEquals(expectedCpf, actual.getCpf());
	Assert.assertEquals(expectedName, actual.getName());
	Assert.assertEquals(expectedSalary, actual.getSalary(), 0);
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

	String line = "001ç1234567891234çPedro";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }
}