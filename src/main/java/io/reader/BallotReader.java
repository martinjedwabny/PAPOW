package main.java.io.reader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.base.Alternative;
import main.java.base.ordering.Ballot;

public class BallotReader {

	/**
	 * Generate a ballot from a string, return null if the ballot is invalid.
	 * @param ballotString
	 * @param alternatives
	 * @return ballot
	 */
	public static Ballot fromString(String ballotString, Map<String, Alternative> alternatives) {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		Collection<Alternative> alternativesLeft = alternatives.values();
		try {
			Matcher m = Pattern.compile("[0-9]+=[^0-9\\}]*").matcher(ballotString.replaceAll("\\s+",""));
			while (m.find()) {
				String occur = m.group();
				String[] rankAndArray = occur.split("=");
				Integer rank = Integer.parseInt(rankAndArray[0]);
				String elementArrayString = rankAndArray[1];
				String[] elementArray = elementArrayString.substring(
						elementArrayString.indexOf("[")+1, 
						elementArrayString.indexOf("]")).split(",");
				for (String elementString : elementArray) {
					Alternative chosen = alternatives.get(elementString);
					if (chosen == null)
						return null;
					rankForElement.put(chosen, rank);
					alternativesLeft.remove(chosen);
				}
			}
		} catch (Exception e) {
			return null;
		}
		if (!alternativesLeft.isEmpty())
			return null;
		return new Ballot(rankForElement);
	}

}
