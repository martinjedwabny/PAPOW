package main.java.io.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.simple.parser.ParseException;

import main.java.base.session.Session;
import main.java.base.session.SessionCommand;
import main.java.base.session.SessionInput;
import main.java.base.session.SessionResult;

public class SessionReader {
	
	/**
	 * Loads a session from a file
	 * @param inputPath
	 * @return session
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Session read(String inputPath) throws FileNotFoundException, IOException, ParseException {
		if (isJsonInputFile(inputPath)) {
			SessionInput input = SessionInputReader.read(inputPath);
			return new Session(input, new SessionCommand(), new SessionResult());
		} else if (isObjectInputFile(inputPath)) {
			return readObjectFile(inputPath);
		}
		return new Session();
	}
	
	/**
	 * Load a session from object file
	 * @param fileName
	 * @return Session
	 */
	private static Session readObjectFile(String fileName) {
		Session session = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			session = (Session) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			session = new Session(new SessionInput(), new SessionCommand(), new SessionResult());
		}
		return session;
	}
	
	/**
	 * @return true iff the file path refers to a json file
	 */
	private static Boolean isJsonInputFile (String inputPath) {
		return inputPath != null && inputPath != "" && 
				inputPath.indexOf('.') != -1 && 
				inputPath.substring(inputPath.lastIndexOf('.')).equals(".json");
	}

	/**
	 * @return true iff the file path refers to an object file
	 */
	private static Boolean isObjectInputFile (String inputPath) {
		return inputPath != null && inputPath != "" && 
				inputPath.indexOf('.') != -1 && 
				inputPath.substring(inputPath.lastIndexOf('.')).equals(".ses");
	}
}
