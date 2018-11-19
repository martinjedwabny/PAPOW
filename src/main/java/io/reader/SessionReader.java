package main.java.io.reader;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import main.java.base.session.Session;
import main.java.base.session.SessionCommand;
import main.java.base.session.SessionInput;
import main.java.base.session.SessionResult;

public class SessionReader {
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
