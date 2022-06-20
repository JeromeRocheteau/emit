package fr.icam.emit.tests;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientEmitClient extends SuperUserEmitClient {
	
	private final String PREFIX = "[emit-test-client]";
	
	@Test
	public void test_01_size() throws Exception {
		Integer size = api.getClientSize();
		Assert.assertTrue(size >= 0);
		System.out.println("[01] number of clients: " + size);
	}
	
	@Test
	public void test_02_page() throws Exception {
		List<Client> page = api.getClientPage(0, 5);
		Assert.assertNotNull(page);
		Assert.assertTrue(page.size() >= 0);
		System.out.println("[02] client page size: " + page.size());
	}
	
	@Test
	public void test_03_create() throws Exception {
		Broker broker = api.getBrokerPage(0, 1).get(0);
		Assert.assertNotNull(broker);
		UUID uuid = api.doClientCreate(PREFIX + " MQTT Client", broker, Boolean.TRUE);
		Assert.assertNotNull(uuid);
		System.out.println("[03] created client: " + uuid);
	}
	
	@Test
	public void test_04_update() throws Exception {
		Integer size = api.getClientSize();
		List<Client> clients = api.getClientPage(0, size);
		for (Client client : clients) {
			if (client.getName().startsWith(PREFIX)) {
				UUID uuid = UUID.fromString(client.getUuid());
				Integer count = api.doClientUpdate(uuid, PREFIX + " Modified MQTT Client", client.getBroker(), Boolean.FALSE);
				Assert.assertNotEquals(0, count.intValue());
				System.out.println("[04] updated client '" + client.getUuid() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_05_delete() throws Exception {
		Integer size = api.getClientSize();
		List<Client> clients = api.getClientPage(0, size);
		for (Client client : clients) {
			if (client.getName().startsWith(PREFIX)) {
				UUID uuid = UUID.fromString(client.getUuid());
				Integer count = api.doClientDelete(uuid);
				Assert.assertNotEquals(0, count.intValue());
				System.out.println("[05] deleted client '" + uuid + "': " + count);				
			}
		}
	}
	
}
