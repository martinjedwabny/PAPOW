package main.java.io.writer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import main.java.base.session.Session;

public class SessionWriter {

	/**
	 * Saves the session and/or results if output path specified,
	 * otherwise print the results
	 * @param session
	 * @param outputPath
	 */
	public static void write(Session session, String outputPath) {
		if (isTxtOutputPath(outputPath))
			writeTxtFile(session, outputPath);
		else if (hasOutputPath(outputPath))
			writeObjectFile(session, outputPath);
		else
			write(session);
	}
	
	/**
	 * Print the session and/or results
	 * @param session
	 */
	public static void write(Session session) {
		System.out.println(SessionOutputBuilder.buildOutput(session));
	}

	/**
	 * Saves the session and/or results as a .ses file
	 * @param session
	 * @param outputPath
	 */
	private static void writeObjectFile(Session session, String fileName) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));) {
			out.writeObject(session);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}

	/**
	 * Saves the session and/or results as a Txt file
	 * @param session
	 * @param outputPath
	 */
	private static void writeTxtFile(Session session, String outputPath) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))){
			bw.write(SessionOutputBuilder.buildOutput(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return true iff there is an output path specified
	 */
	public static Boolean isTxtOutputPath (String outputPath) {
		return outputPath != null && outputPath != "" && 
				outputPath.indexOf('.') != -1 && 
						outputPath.substring(outputPath.lastIndexOf('.')).equals(".txt");
	}
	/**
	 * @return true iff there is an output path specified
	 */
	public static Boolean hasOutputPath (String outputPath) {
		return outputPath != null && outputPath != "" && !isTxtOutputPath(outputPath);
	}
}
