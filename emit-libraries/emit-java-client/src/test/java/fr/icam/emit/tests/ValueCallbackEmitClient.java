package fr.icam.emit.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.clients.EmitClient.Type;
import fr.icam.emit.entities.Callback;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValueCallbackEmitClient extends SuperUserEmitClient {
	
	@Test
	public void test_100_create() throws Exception {
		System.out.println("[100] creating value callback");
		Long id = api.doValueCallbackCreate("Test Integer Value Callback", Type.INTEGER, "5");
		Callback callback = api.getCallback(id);
		Assert.assertNotNull(callback);
		Assert.assertNotEquals(0, id.longValue());
		System.out.println("[100] created value callback: " + id);
		System.out.println("[100] updating value callback: " + id);
		Integer count = api.doValueCallbackUpdate(callback.getId(), "Test Float Value Callback", Type.FLOAT, "5.0");
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[100] updated value callback: " + id);
		System.out.println("[100] deleting value callback: " + id);
		Boolean done = api.doCallbackDelete(callback.getId());
		Assert.assertNotEquals(false, done.booleanValue());
		Assert.assertEquals(true, done.booleanValue());
		System.out.println("[100] deleted value callback: " + id);
	}
	
}
