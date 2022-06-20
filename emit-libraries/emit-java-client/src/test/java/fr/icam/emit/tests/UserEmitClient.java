package fr.icam.emit.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.icam.emit.clients.EmitClient;

public abstract class UserEmitClient {

    protected EmitClient api;
    
	private Properties properties;
	
	protected String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		InputStream stream = this.getPropertyStream();
		properties.load(stream);
		String protocol = properties.getProperty("protocol");
		String hostname = properties.getProperty("hostname");
		int port = Integer.valueOf(properties.getProperty("port")).intValue();
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
        api = new EmitClient(protocol, hostname, port, username, password);
        api.setUp();
	}

	protected abstract InputStream getPropertyStream() throws IOException;

	@After
	public void tearDown() throws Exception {
		api.tearDown();
	}

	@Test
	public void username() throws Exception {
		String expected = properties.getProperty("username");
		String username = api.getUserName();
		Assert.assertEquals(expected, username);
		System.out.println("user name: " + username);
	}
    
    /*
    
    @Test
	public void doMonitoringClients() throws Exception {
		Integer size = api.getClientSize(Mode.MONITORING);
		Assert.assertTrue(size.intValue() >= 0);
		List<Client> items = api.getClientPage(Mode.MONITORING, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("Monitoring clients: " + size + " == " + items.size());
	}
    
	@Test
	public void doReportingClients() throws Exception {
		Integer size = api.getClientSize(Mode.REPORTING);
		Assert.assertTrue(size.intValue() >= 0);
		List<Client> items = api.getClientPage(Mode.REPORTING, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("Reporting clients: " + size + " == " + items.size());
	}
	
	@Test
	public void doFollowingClients() throws Exception {
		Integer size = api.getClientSize(Mode.FOLLOWING);
		Assert.assertTrue(size.intValue() >= 0);
		List<Client> items = api.getClientPage(Mode.FOLLOWING, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("Following clients: " + size + " == " + items.size());
	}
	
	@Test
	public void doManagingClients() throws Exception {
		Integer size = api.getClientSize(Mode.MANAGING);
		Assert.assertTrue(size.intValue() >= 0);
		List<Client> items = api.getClientPage(Mode.MANAGING, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("Managing clients: " + size + " == " + items.size());
	}
	
	@Test
	public void doHandlingClients() throws Exception {
		Integer size = api.getClientSize(Mode.HANDLING);
		Assert.assertTrue(size.intValue() >= 0);
		List<Client> items = api.getClientPage(Mode.HANDLING, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("Handling clients: " + size + " == " + items.size());
	}
	
	@Test
	public void doClientActions() throws Exception {
		Integer size = api.getClientSize(Mode.MONITORING);
		List<Client> items = api.getClientPage(Mode.MONITORING, 0, size);
		for (Client item : items) {
			this.doActions(item);
		}
	}
	
	private void doActions(Client client) throws Exception {
		Integer size = api.getActionSize(client);
		Assert.assertTrue(size.intValue() >= 0);
		List<Action> items = api.getActionPage(client, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("client " + client.getUuid() + " actions: " + size + " == " + items.size());
	}
	
	@Test
	public void doClientMessages() throws Exception {
		Integer size = api.getClientSize(Mode.MONITORING);
		List<Client> items = api.getClientPage(Mode.MONITORING, 0, size);
		for (Client item : items) {
			this.doMessages(item);
		}
	}
	
	private void doMessages(Client client) throws Exception {
		Integer size = api.getMessageSize(client);
		Assert.assertTrue(size.intValue() >= 0);
		List<Message> items = api.getMessagePage(client, 0, size);
		Assert.assertEquals(size.intValue(), items.size());
		System.out.println("client " + client.getUuid() + " messages: " + size + " == " + items.size());
	}

	@Test
	public void doClientConnects() throws Exception {
		Integer size = api.getClientSize(Mode.MONITORING);
		List<Client> items = api.getClientPage(Mode.MONITORING, 0, size);
		System.out.println("Connected clients: " + size + " == " + items.size());
		for (Client item : items) {
			try {
				Connect connect = this.getConnect(item);
				System.out.println("client " + item.getUuid() + " connected: " + (connect == null ? "false" : "true"));
				if (connect == null) {
					Boolean connected = api.doConnect(item);
					Assert.assertTrue(connected == true);
					connect = this.getConnect(item);
					Assert.assertNotNull(connect);
					System.out.println("client " + item.getUuid() + " connected: " + (connect == null ? "false" : "true"));
					Boolean disconnected = api.doDisconnect(item, connect);
					Assert.assertTrue(disconnected == true);
					connect = this.getConnect(item);
					Assert.assertNull(connect);
				} else {
					Boolean disconnected = api.doDisconnect(item, connect);
					Assert.assertTrue(disconnected == true);
					connect = this.getConnect(item);
					Assert.assertNull(connect);
					System.out.println("client " + item.getUuid() + " connected: " + (connect == null ? "false" : "true"));
					Boolean connected = api.doConnect(item);
					Assert.assertTrue(connected == true);
					connect = this.getConnect(item);
					Assert.assertNotNull(connect);
				}
				System.out.println("client " + item.getUuid() + " connected: " + (connect == null ? "false" : "true"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connect getConnect(Client client) throws Exception {
		return api.getConnect(client);
	}

	*/
	
}
