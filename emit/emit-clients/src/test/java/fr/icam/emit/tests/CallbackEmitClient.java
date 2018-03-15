package fr.icam.emit.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.clients.EmitClient.Collection;
import fr.icam.emit.clients.EmitClient.Symbol;
import fr.icam.emit.clients.EmitClient.Type;
import fr.icam.emit.entities.Callback;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CallbackEmitClient extends SuperUserEmitClient {
	
	@Test
	public void test_91_size() throws Exception {
		Integer size = api.getCallbackSize();
		Assert.assertNotEquals(0, size.intValue());
		System.out.println("[91] number of callbacks: " + size);
	}
	
	@Test
	public void test_92_list() throws Exception {
		List<Callback> list = api.getCallbackList();
		Assert.assertNotNull(list);
		Assert.assertNotEquals(0, list.size());
		System.out.println("[92] callback list size: " + list.size());
	}
	
	@Test
	public void test_93_page() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 5);
		Assert.assertNotNull(page);
		Assert.assertNotEquals(0, page.size());
		System.out.println("[93] callback page size: " + page.size());
	}
	
	@Test
	public void test_04_create() throws Exception {
		Integer count = api.doTypeCallbackCreate("Test Integer Callback", Type.INTEGER);
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[04] created type callback: " + count);
	}
	
	@Test
	public void test_05_update() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Integer Callback")) {
				Integer count = api.doTypeCallbackUpdate(callback.getId(), "Test Float Callback", Type.FLOAT);
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[05] updated type callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_06_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Float Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[06] deleted type callback '" + callback.getId() + "': " + done);	
			}
		}
	}
	
	@Test
	public void test_14_create() throws Exception {
		Integer count = api.doTopicCallbackCreate("Test Topic Callback", "test");
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[14] created topic callback: " + count);
	}
	
	@Test
	public void test_15_update() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Topic Callback")) {
				Integer count = api.doTopicCallbackUpdate(callback.getId(), "Test Topic Callback", "test/subtest");
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[15] updated topic callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_16_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Topic Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[16] deleted topic callback '" + callback.getId() + "': " + done);
			}
		}
	}
	
	@Test
	public void test_24_create() throws Exception {
		Integer count = api.doStorageCallbackCreate("Test Storage Callback", Collection.MESSAGES);
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[24] created storage callback: " + count);
	}
	
	@Test
	public void test_25_update() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Storage Callback")) {
				Integer count = api.doStorageCallbackUpdate(callback.getId(), "Test Storage Callback", Collection.FAILURES);
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[25] updated storage callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_26_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Storage Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[26] deleted storage callback '" + callback.getId() + "': " + done);
			}
		}
	}
	
	@Test
	public void test_34_create() throws Exception {
		Integer count = api.doFeatureCallbackCreate("Test Feature Callback", Symbol.EQ, Integer.class, 5);
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[34] created feature callback: " + count);
	}
	
	@Test
	public void test_35_update() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Feature Callback")) {
				Integer count = api.doFeatureCallbackUpdate(callback.getId(), "Test Feature Callback", Symbol.GEQ, Double.class, 5.0);
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[35] updated feature callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_36_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test Feature Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[36] deleted feature callback '" + callback.getId() + "': " + done);
			}
		}
	}
	
	@Test
	public void test_44_create() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 2);
		Assert.assertEquals(2, page.size());
		Integer count = api.doGuardCallbackCreate("Test IfThen Callback", page.get(0), page.get(1));
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[44] created guard callback: " + count);
	}
	
	@Test
	public void test_45_create() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 3);
		Assert.assertEquals(3, page.size());
		Integer count = api.doGuardCallbackCreate("Test IfThenElse Callback", page.get(0), page.get(1), page.get(2));
		Assert.assertNotEquals(0, count.intValue());
		Assert.assertEquals(1, count.intValue());
		System.out.println("[44] created guard callback: " + count);
	}
	
	@Test
	public void test_46_update() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 3);
		Assert.assertEquals(3, page.size());
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test IfThen Callback")) {
				Integer count = api.doGuardCallbackUpdate(callback.getId(), "Test IfThen Callback", page.get(0), page.get(1), page.get(2));
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[45] updated guard callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_47_update() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 3);
		Assert.assertEquals(3, page.size());
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test IfThenElse Callback")) {
				Integer count = api.doGuardCallbackUpdate(callback.getId(), "Test IfThenElse Callback", page.get(1), page.get(2), page.get(0));
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[45] updated guard callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_48_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test IfThen Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[48] deleted guard callback '" + callback.getId() + "': " + done);
			}
		}
	}
	
	@Test
	public void test_49_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getName().equals("Test IfThenElse Callback")) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[48] deleted guard callback '" + callback.getId() + "': " + done);
			}
		}
	}
	
}
