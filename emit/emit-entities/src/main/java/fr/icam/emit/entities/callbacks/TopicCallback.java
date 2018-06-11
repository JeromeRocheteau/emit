package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;

public class TopicCallback extends Callback {

	private String topic;

	public String getTopic() {
		return topic;
	}

	public TopicCallback(Long id, String name, Long issued, String user, Boolean atomic, String category, String topic) {
		super(id, name, issued, user, atomic, category);
		this.topic = topic;
	}

	
}
