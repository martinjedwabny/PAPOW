package main.java.io.writer;

import main.java.base.session.Session;

public class SessionStringTerminalWriter {
	public static void write(Session session) {
		System.out.println(SessionStringWriter.write(session));
	}
}
