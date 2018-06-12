package fr.icam.emit.tests;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Client;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientCallabackEmitClient extends SuperUserEmitClient {
	
	@Test
	public void test_00_attached() throws Exception {
		attached("[00]");
	}
	
	@Test
	public void test_01_attach() throws Exception {
		Client client = client(true);
		Callback callback = api.isAttached(client);
		if (callback == null) {
			callback = this.callback(true);
			if (callback == null) {
				throw new Exception("exception after creating callback");
			} else {
				Integer count = api.doAttach(client, callback);
				Assert.assertNotNull(count);
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[01] client " + client.getUuid() + " attached to callback " + callback.getId());
			}
		}
	}

	@Test
	public void test_02_attached() throws Exception {
		attached("[02]");
	}
	
	@Test
	public void test_03_detach() throws Exception {
		Integer size = api.getClientSize();
		Assert.assertNotNull(size);
		Assert.assertNotEquals(0, size.intValue());
		List<Client> clients = api.getClientPage(0, size);
		for (Client client : clients) {
			if (client.getName().equals("Test Client")) {
				Callback callback = api.isAttached(client);
				if (callback != null) {
					Integer count = api.doDetach(client);
					Assert.assertNotNull(count);
					Assert.assertNotEquals(0, count.intValue());
					System.out.println("[03] client " + client.getUuid() + " detached from callback " + callback.getId());
				}
			}
		}
	}
	
	public void attached(String label) throws Exception {
		Client client = client(true);
		Callback callback = api.isAttached(client);
		if (callback == null) {
			System.out.println(label + " client " + client.getUuid() + " is not attached");
		} else {
			System.out.println(label + " client " + client.getUuid() + " is attached to callback " + callback.getId());
		}
	}

	private Broker broker(boolean first) throws Exception {
		List<Broker> brokers = api.getBrokerList();
		Assert.assertNotNull(brokers);
		for (Broker broker : brokers) {
			return broker;
		}
		Integer count = api.doBrokerCreate("Test Broker", URI.create("http://localhost:1883"));
		Assert.assertNotNull(count);
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		if (first) {
			return broker(false);
		} else {
			throw new Exception("error while creating broker");
		}
	}
	
	private Client client(boolean first) throws Exception {
		Integer size = api.getClientSize();
		Assert.assertNotNull(size);
		Assert.assertNotEquals(0, size.intValue());
		List<Client> clients = api.getClientPage(0, size);
		for (Client client : clients) {
			if (client.getName().equals("Test Client")) {
				return client;
			}
		}
		Broker broker = broker(true);
		UUID uuid = api.doClientCreate("Test Client", broker, Boolean.FALSE);
		Assert.assertNotNull(uuid);
		if (first) {
			return client(false);
		} else {
			throw new Exception("error while creating client");
		}
	}
	
	private Callback callback(boolean first) throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Callback")) {
				return callback;
			}
		}
		Integer count = api.doTopicCallbackCreate("Test Callback", "test");
		Assert.assertNotNull(count);
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		if (first) {
			return callback(false);
		} else {
			throw new Exception("error while creating callback");
		}
	}
	
}
