package fr.icam.emit.tests;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class SuperUserEmitClient extends UserEmitClient {

	@Override
	protected InputStream getPropertyStream() throws IOException {
		ClassLoader loader = getClass().getClassLoader();
		return loader.getResourceAsStream("super-user-tests.properties");
	}

	@Test
	public void role() throws Exception {
		boolean expected = Boolean.valueOf(this.getProperty("userrole")).booleanValue();
		Boolean role = api.isSuperUser();
		Assert.assertEquals(expected, role);
		System.out.println("super user: " + role);
	}

}
