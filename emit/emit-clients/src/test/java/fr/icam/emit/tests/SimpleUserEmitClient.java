package fr.icam.emit.tests;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.icam.emit.clients.EmitClient;

public class SimpleUserEmitClient extends UserEmitClient {

	private Properties properties;
	
	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		ClassLoader loader = getClass().getClassLoader();
		InputStream stream = loader.getResourceAsStream("simple-user-tests.properties");
		properties.load(stream);
		String protocol = properties.getProperty("protocol");
		String hostname = properties.getProperty("hostname");
		int port = Integer.valueOf(properties.getProperty("port")).intValue();
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
        api = new EmitClient(protocol, hostname, port, username, password);
        api.setUp();
	}

	@After
	public void tearDown() throws Exception {
		api.tearDown();
	}
	
	@Test
	public void doOne() throws Exception {
		String expected = properties.getProperty("username");
		String username = api.getUserName();
		Assert.assertEquals(expected, username);
	}

	@Test
	public void doTwo() throws Exception {
		boolean expected = Boolean.valueOf(properties.getProperty("userrole")).booleanValue();
		Boolean role = api.isSuperUser();
		Assert.assertEquals(expected, role);
	}

}
