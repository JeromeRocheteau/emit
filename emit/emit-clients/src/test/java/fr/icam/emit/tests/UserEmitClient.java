package fr.icam.emit.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.icam.emit.clients.EmitClient;
import fr.icam.emit.clients.EmitClient.Mode;
import fr.icam.emit.entities.Action;
import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Message;

public abstract class UserEmitClient {

    protected EmitClient api;
    
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

}
