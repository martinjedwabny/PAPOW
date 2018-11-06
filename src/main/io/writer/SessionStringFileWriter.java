package main.io.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;

import main.base.session.Session;

public class SessionStringFileWriter {
	public static void write(Session session, String filePath) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
			bw.write(SessionStringWriter.write(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
