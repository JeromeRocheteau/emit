package fr.icam.emit.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.icam.emit.clients.EmitClient.Type;
import fr.icam.emit.entities.Callback;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TypeCallbackEmitClient extends SuperUserEmitClient {
	
	private static Long ID;
	
	@Test
	public void test_90_size() throws Exception {
		Integer size = api.getCallbackSize();
		Assert.assertEquals(0, size.intValue());
		System.out.println("[90] number of callbacks: " + size);
	}
	
	@Test
	public void test_91_list() throws Exception {
		List<Callback> list = api.getCallbackList();
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		System.out.println("[91] callback list size: " + list.size());
	}
	
	@Test
	public void test_92_page() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 5);
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.size());
		System.out.println("[92] callback page size: " + page.size());
	}
	
	@Test
	public void test_93_create() throws Exception {
		Long id = api.doTypeCallbackCreate("Test Integer Callback", Type.INTEGER);
		Assert.assertNotEquals(0, id.longValue());
		System.out.println("[93] created type callback: " + id);
		ID = id;
	}
	
	@Test
	public void test_93_size() throws Exception {
		Integer size = api.getCallbackSize();
		Assert.assertEquals(1, size.intValue());
		System.out.println("[93] number of callbacks: " + size);
	}
	
	@Test
	public void test_94_find() throws Exception {
		Callback callback = api.getCallback(ID);
		Assert.assertNotNull(callback);
		Assert.assertEquals(callback.getId(), ID);
		System.out.println("[94] retrieved callback: " + callback);
	}
	
	@Test
	public void test_95_update() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getId() == ID) {
				Integer count = api.doTypeCallbackUpdate(callback.getId(), "Test Float Callback", Type.FLOAT);
				Assert.assertNotEquals(0, count.intValue());
				Assert.assertEquals(1, count.intValue());
				System.out.println("[95] updated type callback '" + callback.getId() + "': " + count);	
			}
		}
	}
	
	@Test
	public void test_96_delete() throws Exception {
		List<Callback> callbacks = api.getCallbackList();
		for (Callback callback : callbacks) {
			if (callback.getId() == ID) {
				Boolean done = api.doCallbackDelete(callback.getId());
				Assert.assertNotEquals(false, done.booleanValue());
				Assert.assertEquals(true, done.booleanValue());
				System.out.println("[96] deleted type callback '" + callback.getId() + "': " + done);	
			}
		}
	}
	
	/*
	
	@Test
	public void test_14_create() throws Exception {
		Long id = api.doTopicCallbackCreate("Test Topic Callback", "test");
		Assert.assertNotEquals(0, id.longValue());
		// Assert.assertEquals(1, id.intValue());
		System.out.println("[14] created topic callback: " + id);
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
		Long id = api.doStorageCallbackCreate("Test Storage Callback", Collection.MESSAGES);
		Assert.assertNotEquals(0, id.longValue());
		// Assert.assertEquals(1, id.intValue());
		System.out.println("[24] created storage callback: " + id);
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
		Long id = api.doFeatureCallbackCreate("Test Feature Callback", Symbol.EQ, Integer.class, 5);
		Assert.assertNotEquals(0, id.longValue());
		// Assert.assertEquals(1, count.intValue());
		System.out.println("[34] created feature callback: " + id);
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
		Long id = api.doGuardCallbackCreate("Test IfThen Callback", page.get(0), page.get(1));
		Assert.assertNotEquals(0, id.longValue());
		// Assert.assertEquals(1, id.intValue());
		System.out.println("[44] created guard callback: " + id);
	}
	
	@Test
	public void test_45_create() throws Exception {
		List<Callback> page = api.getCallbackPage(0, 3);
		Assert.assertEquals(3, page.size());
		Long id = api.doGuardCallbackCreate("Test IfThenElse Callback", page.get(0), page.get(1), page.get(2));
		Assert.assertNotEquals(0, id.longValue());
		// Assert.assertEquals(1, id.intValue());
		System.out.println("[44] created guard callback: " + id);
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

	*/
	
}
