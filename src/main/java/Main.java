package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import main.java.base.session.Session;
import main.java.base.session.SessionCommand;
import main.java.base.session.SessionInput;
import main.java.base.session.SessionResult;
import main.java.base.session.SessionRunner;
import main.java.io.reader.SessionCommandReader;
import main.java.io.reader.SessionInputReader;
import main.java.io.reader.SessionReader;
import main.java.io.writer.SessionObjectFileWriter;
import main.java.io.writer.SessionStringFileWriter;
import main.java.io.writer.SessionStringTerminalWriter;

/**
 * Main
 */
public class Main {

	/** Main entry point of the program.
	 * 
	 * 	Options :
	 * 		-f 		inputPath	: json file input name
	 * 		-o 		outputPath	: txt file output name
	 * 		-v 		array		: voting rule code array
	 * 		-c		criterion	: criterion / specific format
	 * 
	 * 	Note :
	 * 		There are two input file formats : json (to load only input data only) and object file
	 * 		(used internally by the program to load and save the input, rules, results and criterion).
	 * 		If the inputPath is not a json file, other options will be ignored.
	 * 
	 * 	Note :
	 * 		There are two output file formats : txt (to save as text) and object file
	 * 		(used internally by the program to save the input, rules, results and criterion).
	 * 		If the outputPath is not specified, the result is displayed by console.
	 * 
	 * 	Voting rule codes :
	 * 		pb 			: Pessimistic Borda
	 * 		fb 			: Fair Borda
	 * 		ob 			: Optimistic Borda
	 * 		co 			: Copeland
	 * 		k1,k2,kx 	: k-Approval with k=x
	 * 		ir			: Instant Runoff
	 * 
	 * 	Criterion format :
	 * 		[[(A,B),(C,D)],[(E,F)]] array of array of pairs, translates as (A=B and C=D) or (E=F)
	 * 
	 * 	Example :
	 * 		-f res/sample1.json -v [pb,fb,k2] -c [[(Color,Red),(Country,Algeria)]]
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		//args = ("-f res/sample1.json -o res/sample1-out.o -v [co] -c [[(Color,Green)],[(Color,Red),(Country,Algeria)]]").split(" ");
		//args = ("-o res/sample1-out.txt -f res/sample1-out.o -v [co] -c [[(Color,Green)],[(Color,Red),(Country,Algeria)]]").split(" ");
		args = ("-f res/sample1-out.o -v [co] -c [[(Color,Green)],[(Color,Red),(Country,Algeria)]]").split(" ");
		try {
			SessionCommand command = SessionCommandReader.read(args);
			Session session = loadSession(command);
			saveSession(session, command);
		} catch (FileNotFoundException ex) {
			System.out.println("Error: File doesn't exists.");
		} catch (IOException e) {
			System.out.println("Error: IOException.");
		} catch (ParseException e){
			System.out.println("Error: ParseException.");
		}
	}

	/**
	 * Loads a session from a command
	 * @param command
	 * @return session
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Session loadSession(SessionCommand command) throws FileNotFoundException, IOException, ParseException {
		Session session = null;
		// Load session
		if (command.hasJsonInputFile()) {
			SessionInput input = SessionInputReader.read(command.getInputPath());
			SessionResult result = SessionRunner.generateResults(input, command.getRules(), command.getCriterion());
			session = new Session(input, command, result);
		} else if (command.hasObjectInputFile()) {
			session = SessionReader.read(command.getInputPath());
		}
		return session;
	}
	
	/**
	 * Saves the session and/or results
	 * @param session
	 * @param command
	 */
	private static void saveSession(Session session, SessionCommand command) {
		if (command.hasTxtOutputPath())
			SessionStringFileWriter.write(session, command.getOutputPath());
		else if (command.hasOutputPath())
			SessionObjectFileWriter.write(session, command.getOutputPath());
		else
			SessionStringTerminalWriter.write(session);
	}
	
}
