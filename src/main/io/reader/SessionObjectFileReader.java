package main.io.reader;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import main.base.session.Session;
import main.base.session.SessionCommand;
import main.base.session.SessionInput;
import main.base.session.SessionResult;

public class SessionObjectFileReader {
	public static Session read(String fileName) {
		Session session = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			session = (Session) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			session = new Session(new SessionInput(), new SessionCommand(), new SessionResult());
		}
		return session;
	}
}
