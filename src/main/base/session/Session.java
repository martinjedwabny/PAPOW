package main.base.session;

import java.io.Serializable;

public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	
	SessionInput input;
	SessionCommand command;
	SessionResult result;
	
	/**
	 * @param input
	 * @param command
	 * @param result
	 */
	public Session(SessionInput input, SessionCommand command, SessionResult result) {
		super();
		this.input = input;
		this.command = command;
		this.result = result;
	}

	/**
	 * @return the input
	 */
	public SessionInput getInput() {
		return input;
	}

	/**
	 * @return the command
	 */
	public SessionCommand getCommand() {
		return command;
	}

	/**
	 * @return the result
	 */
	public SessionResult getResult() {
		return result;
	}
}
