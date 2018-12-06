package main.java.base.session;

import java.io.Serializable;

public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SessionInput input;
	private SessionCommand command;
	private SessionResult result;
	
	/**
	 * Default constructor
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
	 * Empty constructor
	 */
	public Session() {
		super();
		this.input = new SessionInput();
		this.command = new SessionCommand();
		this.result = new SessionResult();
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

	/**
	 * @param input the input to set
	 */
	public void setInput(SessionInput input) {
		this.input = input;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(SessionCommand command) {
		this.command = command;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(SessionResult result) {
		this.result = result;
	}
}
