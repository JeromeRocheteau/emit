package fr.icam.emit.spy;

import java.io.IOException;

public class SpyLauncher {

	private String command;
	private String[] arguments;
	private Process process;
	private long timeout;
	private int status;
	private long timestamp;
	private long duration;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public long getDuration() {
		return duration;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void doLaunch() throws IOException {
		status = -1;
    	String cmd = this.getExec();
		timestamp = System.currentTimeMillis();
		process = Runtime.getRuntime().exec(cmd);
		process.getErrorStream().close();
		process.getInputStream().close();
		if (timeout > 0L) {
			Thread thread = this.getThread(process);
			thread.start();
			try {
				thread.join(timeout);
				try {
					status = process.exitValue();
				} catch (IllegalThreadStateException e) {
					process.destroy();
					status = process.exitValue();
				}
			} catch (InterruptedException e) { }
		} else if (timeout == 0L) {
			try {
				status = process.waitFor();
			} catch (InterruptedException e) { }
		}
		duration = System.currentTimeMillis() - timestamp;
	}

	private String getExec() {
		StringBuilder builder = new StringBuilder();
    	builder.append(command);
    	if (arguments != null) {
    		for (String argument : arguments) {
    			builder.append(' ');
    			builder.append(argument);
    		}
    	}
		return builder.toString();
	}

	private Thread getThread(final Process process) {
		return new Thread() {
			public void run() {
				try {
					process.waitFor();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
	}

}