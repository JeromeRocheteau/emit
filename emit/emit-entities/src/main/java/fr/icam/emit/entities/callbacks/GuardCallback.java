package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;

public class GuardCallback extends Callback {

	private Callback test;
	
	private Callback success;
	
	private Callback failure;
	
	public Callback getTest() {
		return test;
	}

	public Callback getSuccess() {
		return success;
	}

	public Callback getFailure() {
		return failure;
	}

	public GuardCallback(Long id, String name, Long issued, String user, Boolean atomic, String category, Callback test, Callback success, Callback failure) {
		super(id, name, issued, user, atomic, category);
		this.test = test;
		this.success = success;
		this.failure = failure;
	}

	
}
