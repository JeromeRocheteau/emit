package fr.icam.emit.tests;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Message;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageEmitClient extends SimpleUserEmitClient {
	
	private final String ARDGETTI_ID = "bf51e717-c6fd-4a7a-b10e-e645b673c023";
	
	private final String PEAKTECH_ID = "8161519d-d875-4f24-8a4c-e930a6d108af";
	
	private static Long timestamp;
	
	@Test
	public void test_01_timestamp() throws Exception {
		timestamp = api.getTimestamp();
		Assert.assertNotNull(timestamp);
		Assert.assertTrue(timestamp.longValue() >= 0);
		Date date = new Date(timestamp.longValue());
		System.out.println("[01] timestamp: " + date);
	}
	
	@Test
	public void test_02_messages_ardgetti() throws Exception {
		Assert.assertNotNull(timestamp);
		Assert.assertTrue(timestamp.longValue() >= 0);
		UUID uuid = UUID.fromString(ARDGETTI_ID);
		Client client = api.getClient(uuid);
		Assert.assertNotNull(client);
		List<Message> messages = api.getMessagePage(client, timestamp - 10000);
		Assert.assertNotNull(messages);
		Assert.assertTrue(messages.size() >= 0);
		System.out.println("[02] messages size: " + messages.size());
		for (Message message : messages) {
			String topic = message.getTopic();
			Long issued = message.getIssued();
			String payload = new String(message.getPayload());
			System.out.println("[03] " + topic + ": " + payload + " at " + issued);	
		}
	}
	
	@Test
	public void test_03_messages_peakteck() throws Exception {
		Assert.assertNotNull(timestamp);
		Assert.assertTrue(timestamp.longValue() >= 0);
		UUID uuid = UUID.fromString(PEAKTECH_ID);
		Client client = api.getClient(uuid);
		Assert.assertNotNull(client);
		List<Message> messages = api.getMessagePage(client, timestamp - 10000);
		Assert.assertNotNull(messages);
		Assert.assertTrue(messages.size() >= 0);
		System.out.println("[04] messages size: " + messages.size());
		for (Message message : messages) {
			String topic = message.getTopic();
			Long issued = message.getIssued();
			String payload = new String(message.getPayload());
			System.out.println("[05] " + topic + ": " + payload + " at " + issued);	
		}
	}
		
}
