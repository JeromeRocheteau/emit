package fr.icam.emit.tests;

import java.net.URI;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.entities.Broker;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrokerEmitClient extends SuperUserEmitClient {

	private final String FIRST_BROKER_URI = "tcp://localhost:1883";
	
	private final String SECOND_BROKER_URI = "tcp://app.icam.fr:1883";
	
	@Test
	public void test_01_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size >= 0);
		System.out.println("[01] number of brokers: " + size);
	}
	
	@Test
	public void test_02_list() throws Exception {
		List<Broker> list = api.getBrokerList();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() >= 0);
		System.out.println("[02] broker list size: " + list.size());
	}
	
	@Test
	public void test_03_page() throws Exception {
		List<Broker> page = api.getBrokerPage(0, 5);
		Assert.assertNotNull(page);
		Assert.assertTrue(page.size() >= 0);
		System.out.println("[03] broker page size: " + page.size());
	}
	
	@Test
	public void test_04_create() throws Exception {
		Integer count = api.doBrokerCreate("first MQTT broker", URI.create(FIRST_BROKER_URI));
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[04] number of created brokers: " + count);
	}
	
	@Test
	public void test_04_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 1);
		System.out.println("[04] number of brokers: " + size);
	}
	
	@Test(expected = Exception.class)
	public void test_05_create() throws Exception {
		api.doBrokerCreate("first MQTT broker", URI.create(FIRST_BROKER_URI));
	}
	
	@Test
	public void test_05_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 1);
		System.out.println("[05] number of brokers: " + size);
	}
	
	@Test
	public void test_06_create() throws Exception {
		Integer count = api.doBrokerCreate("Another Test MQTT broker", URI.create(SECOND_BROKER_URI), "username", "password");
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[06] number of created brokers: " + count);
	}
	
	@Test
	public void test_06_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 2);
		System.out.println("[06] number of brokers: " + size);
	}
	
	@Test
	public void test_07_update() throws Exception {
		Integer count = api.doBrokerUpdate("modified first MQTT broker", URI.create(FIRST_BROKER_URI));
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[07] number of updated brokers: " + count);
	}
	
	@Test
	public void test_07_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 2);
		System.out.println("[07] number of brokers: " + size);
	}
	
	@Test
	public void test_08_update() throws Exception {
		Integer count = api.doBrokerUpdate("modified second MQTT broker", URI.create(SECOND_BROKER_URI));
		Assert.assertNotEquals(0, count.intValue());
		System.out.println("[08] number of updated brokers: " + count);
	}
	
	@Test
	public void test_08_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 2);
		System.out.println("[08] number of brokers: " + size);
	}
	
	@Test
	public void test_09_find() throws Exception {
		Broker broker = api.getBroker(URI.create(FIRST_BROKER_URI));
		Assert.assertNotNull(broker);
		System.out.println("[09] retrieved broker: " + broker);
	}
	
	@Test
	public void test_10_find() throws Exception {
		Broker broker = api.getBroker(URI.create(SECOND_BROKER_URI));
		Assert.assertNotNull(broker);
		System.out.println("[10] retrieved broker: " + broker);
	}
	
	@Test
	public void test_11_delete() throws Exception {
		Integer count = api.doBrokerDelete(URI.create(FIRST_BROKER_URI));
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[11] number of deleted brokers: " + count);
	}
	
	@Test
	public void test_12_find() throws Exception {
		Broker broker = api.getBroker(URI.create(FIRST_BROKER_URI));
		Assert.assertNull(broker);
		System.out.println("[12] retrieved broker: " + broker);
	}
	
	@Test
	public void test_13_find() throws Exception {
		Broker broker = api.getBroker(URI.create(SECOND_BROKER_URI));
		Assert.assertNotNull(broker);
		System.out.println("[13] retrieved broker: " + broker);
	}
	
	@Test
	public void test_14_delete() throws Exception {
		Integer count = api.doBrokerDelete(URI.create(SECOND_BROKER_URI));
		Assert.assertNotEquals(0, count.intValue());
		System.out.println("[14] number of deleted brokers: " + count);
	}
	
	@Test
	public void test_15_find() throws Exception {
		Broker broker = api.getBroker(URI.create(SECOND_BROKER_URI));
		Assert.assertNull(broker);
		System.out.println("[15] retrieved broker: " + broker);
	}
	
	@Test
	public void test_16_delete() throws Exception {
		Integer count = api.doBrokerDelete(URI.create(SECOND_BROKER_URI));
		Assert.assertEquals(0, count.intValue());
		System.out.println("[16] number of deleted brokers: " + count);
	}
	
	@Test
	public void test_17_size() throws Exception {
		Integer size = api.getBrokerSize();
		Assert.assertTrue(size == 0);
		System.out.println("[17] number of brokers: " + size);
	}
	
}
