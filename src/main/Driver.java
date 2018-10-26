package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import main.base.Session;
import main.base.result.SessionResult;
import main.base.result.SessionRunner;
import main.io.command.Command;
import main.io.parser.CommandParser;
import main.io.parser.SessionParser;
import main.io.result.SessionResultStringAdaptor;

/**
 * Driver
 */
public class Driver {

	/** Main entry point of the program.
	 * 
	 * 	Options :
	 * 		-f 		fileName	: json file name (required)
	 * 		-v 		array		: voting rule code array (required)
	 * 		-c		criterion	: criterion / specific format (not required)
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
	 * 		-f json-example.txt -v [pb,fb,k2] -c [[(Color,Red),(Country,Algeria)]]
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		
		// Command options parsing
		args = ("-f res/json-sample-1.txt -v [co] -c [[(Color,Green)],[(Color,Red),(Country,Algeria)]]").split(" ");
		
		try {
			Command command = CommandParser.parseCommand(args);
			Session session = SessionParser.parseSession(command.getFileName());
			SessionResult result = SessionRunner.generateResults(session, command.getRules(), command.getCriterion());
			String output = SessionResultStringAdaptor.buildOutput(result);
			System.out.println(output);
		} catch (FileNotFoundException ex) {
        	System.out.println("Error: File doesn't exists.");
        } catch (IOException e) {
        	System.out.println("Error: IOException.");
        } catch (ParseException e){
        	System.out.println("Error: ParseException.");
        }
    }
}
