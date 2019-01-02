package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import main.java.base.session.Session;
import main.java.base.session.SessionCommand;
import main.java.base.session.SessionRunner;
import main.java.io.reader.SessionCommandReader;
import main.java.io.reader.SessionReader;
import main.java.io.writer.SessionWriter;

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
	 * 		-f src/main/resources/sample.json -v [pb,fb,k2] -c [[(Color,Red),(Country,France)]]
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		try {
			SessionCommand command = SessionCommandReader.read(args);
			Session session = SessionReader.read(command.getInputPath());
			session.setCommand(command);
			session.setResult(SessionRunner.generateResults(session.getInput(), command.getRules(), command.getCriteria()));
			SessionWriter.write(session, command.getOutputPath());
		} catch (FileNotFoundException ex) {
			System.out.println("Error: File doesn't exists.");
		} catch (IOException e) {
			System.out.println("Error: IOException.");
		} catch (ParseException e){
			System.out.println("Error: ParseException.");
		}
	}

}
