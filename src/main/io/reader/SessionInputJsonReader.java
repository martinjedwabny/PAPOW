package main.io.reader;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.base.*;
import main.base.ordering.Ballot;
import main.base.session.SessionInput;

public class SessionInputJsonReader {
	
	/**
	 * Given the name of a json file, read the json and create a voting session that represents the input in it.
	 * 
	 * The json file contains different objects that are modeled in the schema 'json-schema.txt' provided.
	 * 
	 * To parse this input, the ids of objects have to be considered as to maintain the references between
	 * different object types. This is done via the hashmaps: 
	 * questionHash, alternativeHash, voterHash, familyHash, categoryHash,
	 * which are then discarded, as the ids are not maintained in memory by the program.
	 * 
	 * @param jsonName the name of the json file.
	 * @return Session the voting session with the data speceified by the json file.
	 * @throws IOException if there is an error reading the json file.
	 * @throws ParseException if the json file has an invalid format.
	 * @throws FileNotFoundException if the json file does not exists.
	 */
	public static SessionInput read(String jsonName) 
			throws IOException, ParseException, FileNotFoundException {
		// Objects to fill from file
		Vector<Question> questions = new Vector<Question>();
		Vector<Vote> votes = new Vector<Vote>();
		Vector<Voter> voters = new Vector<Voter>();
		Vector<CategoryFamily> families = new Vector<CategoryFamily>();

        // Parsing file "JSONExample.json"
		JSONObject json = getJsonFromFile(jsonName);

		// JSONArray for each member of the file
		JSONArray questionsJ = (JSONArray) json.get("questions");
		JSONArray votesJ = (JSONArray) json.get("votes");
		JSONArray votersJ = (JSONArray) json.get("voters");
		JSONArray categoryFamiliesJ = (JSONArray) json.get("categoryFamilies");
		
		// Create global Hashmaps to save the ids for cross references in the json file by ids
		Map<Integer, Question> questionHash = new HashMap<Integer, Question>();
		Map<Integer, Alternative> alternativeHash = new HashMap<Integer, Alternative>();
		Map<Integer, Voter> voterHash = new HashMap<Integer, Voter>();
		Map<Integer, CategoryFamily> familyHash = new HashMap<Integer, CategoryFamily>();
		Map<Integer, Category> categoryHash = new HashMap<Integer, Category>();

		// Parse each array of items
		parseJSonObjectsAndFillResults(questions, votes, voters, families, questionHash, alternativeHash, voterHash,
				familyHash, categoryHash, questionsJ, votesJ, votersJ, categoryFamiliesJ);
		
		return new SessionInput(questions, votes, voters, families);
	}
	
	/**
	 * Reads a json file
	 * @param jsonName the name of the json file.
	 * @return JSONObject representing the input of the file
	 * @throws IOException
	 * @throws ParseException
	 */
	private static JSONObject getJsonFromFile(String jsonName) {
		JSONObject json = null;
		try (FileReader fr = new FileReader(jsonName)) {
			Object input = new JSONParser().parse(fr);
			json = (JSONObject) input;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Takes the object vectors to be filled, the json objects parsed from the json file 
	 * and the hashmaps representing the references to objects and fills
	 * the object vectors according to the input in the json objects. 
	 * 
	 * @param questions to be filled.
	 * @param votes to be filled.
	 * @param voters to be filled.
	 * @param families to be filled.
	 * 
	 * @param questionHash id references.
	 * @param alternativeHash id references.
	 * @param voterHash id references.
	 * @param familyHash id references.
	 * @param categoryHash id references.
	 * 
	 * @param questionsJ JSONObject.
	 * @param votesJ JSONObject.
	 * @param votersJ JSONObject.
	 * @param categoryFamiliesJ JSONObject.
	 */
	private static void parseJSonObjectsAndFillResults(Vector<Question> questions, Vector<Vote> votes, Vector<Voter> voters,
			Vector<CategoryFamily> families, Map<Integer, Question> questionHash,
			Map<Integer, Alternative> alternativeHash, Map<Integer, Voter> voterHash,
			Map<Integer, CategoryFamily> familyHash, Map<Integer, Category> categoryHash, JSONArray questionsJ,
			JSONArray votesJ, JSONArray votersJ, JSONArray categoryFamiliesJ) {
		for(Object o: questionsJ)
			questions.add(readQuestion((JSONObject)o, alternativeHash, questionHash));
		for(Object o: votersJ)
			voters.add(readVoter((JSONObject)o, voterHash));
		for(Object o: categoryFamiliesJ)
			families.add(readFamily((JSONObject)o, familyHash, categoryHash));
		for(Object o: votesJ)
			votes.add(readVote((JSONObject)o, questionHash, alternativeHash, familyHash, categoryHash, voterHash));
	}

	/**
	 * Take a JSONObject representing a vote and parse it.
	 * 
	 * @param o JSONObject.
	 * @param questionHash id references.
	 * @param alternativeHash id references.
	 * @param voterHash id references.
	 * @param familyHash id references.
	 * @param categoryHash id references.
	 * @return the vote as an object.
	 */
	private static Vote readVote(JSONObject o, Map<Integer, Question> questionHash,
			Map<Integer, Alternative> alternativeHash, Map<Integer, CategoryFamily> familyHash,
			Map<Integer, Category> categoryHash, Map<Integer, Voter> voterHash) {
		Integer idQuestion = ((Long) o.get("idQuestion")).intValue();
		Integer idVoter = ((Long) o.get("voter")).intValue();
		Voter v = voterHash.get(idVoter);
		HashMap<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		JSONArray rankJ = (JSONArray) o.get("ranking");
		for (int i=0; i < rankJ.size(); i++) {
			Integer rank = ((Long)rankJ.get(i)).intValue();
			Alternative a = alternativeHash.get(i+1);
			rankForElement.put(a, rank);
		}
		HashMap<CategoryFamily, Category> categories = new HashMap<CategoryFamily, Category>();
		JSONArray catJ = (JSONArray) o.get("categories");
		for (int i=0; i < catJ.size(); i++) {
			JSONObject co = (JSONObject) catJ.get(i);
			Integer idFamily = ((Long)co.get("idFamily")).intValue();
			Integer idCategory = ((Long)co.get("idCategory")).intValue();
			categories.put(familyHash.get(idFamily), categoryHash.get(idCategory));
		}
		Ballot ranking = new Ballot(rankForElement);
		Vote ans = new Vote(v, ranking, categories);
		questionHash.get(idQuestion).addVote(ans);
		return ans;
	}

	/**
	 * Take a JSONObject representing a category family and parse it.
	 * 
	 * @param o JSONObject.
	 * @param familyHash id references.
	 * @param categoryHash id references.
	 * @return the family as an object.
	 */
	private static CategoryFamily readFamily(JSONObject o, Map<Integer, CategoryFamily> familyHash,
			Map<Integer, Category> categoryHash) {
		Integer id = ((Long) o.get("id")).intValue();
		String desc = o.get("description").toString();
		JSONArray catJ = (JSONArray) o.get("categories");
		Set<Category> possibilities = new HashSet<Category>();
		for(Object c: catJ)
			possibilities.add(readCategory((JSONObject)c, categoryHash));
		CategoryFamily ans = new CategoryFamily(desc, possibilities);
		familyHash.put(id, ans);
		return ans;
	}

	/**
	 * Take a JSONObject representing a category and parse it.
	 * 
	 * @param o JSONObject.
	 * @param categoryHash id references.
	 * @return the category as an object.
	 */
	private static Category readCategory(JSONObject o, Map<Integer, Category> categoryHash) {
		Integer id = ((Long) o.get("idCategory")).intValue();
		String desc = o.get("description").toString();
		Category ans = new Category(desc);
		categoryHash.put(id, ans);
		return ans;
	}

	/**
	 * Take a JSONObject representing a voter and parse it.
	 * 
	 * @param o JSONObject.
	 * @param voterHash id references.
	 * @return the voter as an object.
	 */
	private static Voter readVoter(JSONObject o, Map<Integer, Voter> voterHash) {
		Integer id = ((Long) o.get("id")).intValue();
		String name = (o.get("name")).toString();
		Voter ans = new Voter(name);
		voterHash.put(id, ans);
		return ans;
	}

	/**
	 * Take a JSONObject representing a question and parse it.
	 * 
	 * @param o JSONObject.
	 * @param questionHash id references.
	 * @param alternativeHash id references.
	 * @return the question as an object.
	 */
	private static Question readQuestion (JSONObject o, Map<Integer, Alternative> alternativeHash, 
			Map<Integer, Question> questionHash) {
		Integer id = ((Long) o.get("id")).intValue();
		String desc = o.get("description").toString();
		JSONArray altJ = (JSONArray)o.get("alternatives");
		Vector<Alternative> alternatives = new Vector<Alternative>(); 
		Set<Vote> votes = new HashSet<Vote>();
		for(Object a: altJ) {
			Alternative alt = readAlternative((JSONObject)a, alternativeHash);
			alternatives.add(alt);
		}
		Question ans = new Question(desc, alternatives, votes);
		questionHash.put(id, ans);
		return ans;
	}

	/**
	 * Take a JSONObject representing an alternative and parse it.
	 * 
	 * @param o JSONObject.
	 * @param alternativeHash id references.
	 * @return the alternative as an object.
	 */
	private static Alternative readAlternative(JSONObject o, Map<Integer, Alternative> alternativeHash) {
		Integer idA = ((Long)(o).get("idAlternative")).intValue();
		String name = o.get("name").toString();
		Alternative alt = new Alternative(name);
		alternativeHash.put(idA, alt);
		return alt;
	}
	
}
