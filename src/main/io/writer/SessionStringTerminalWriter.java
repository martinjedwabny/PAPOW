package main.io.writer;

import main.base.session.Session;

public class SessionStringTerminalWriter {
	public static void write(Session session) {
		System.out.println(SessionStringWriter.write(session));
	}
}
