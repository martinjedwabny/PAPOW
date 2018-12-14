package main.java.io.reader;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
	public static Ballot fromString(String ballotString, Collection<Alternative> alternatives, Map<String, Alternative> nameToAlternative) {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		LinkedHashSet<Alternative> alternativesLeft = new LinkedHashSet<Alternative>();
		alternativesLeft.addAll(alternatives);
		try {
			Matcher m = Pattern.compile("[0-9]+=[^0-9\\}]*").matcher(ballotString.trim());
			Integer rank = 1;
			while (m.find()) {
				String occur = m.group();
				String[] rankAndArray = occur.split("=");
				String elementArrayString = rankAndArray[1];
				elementArrayString = elementArrayString.substring(
						elementArrayString.indexOf("[")+1, 
						elementArrayString.indexOf("]")).trim();
				System.out.println(elementArrayString);
				if (elementArrayString.isEmpty())
					continue;
				String[] elementArray = elementArrayString.split(",");
				for (String elementString : elementArray) {
					Alternative chosen = nameToAlternative.get(elementString.trim());
					if (chosen == null)
						return null;
					rankForElement.put(chosen, rank);
					alternativesLeft.remove(chosen);
				}
				rank += elementArray.length;
			}
		} catch (Exception e) {
			return null;
		}
		if (!alternativesLeft.isEmpty())
			return null;
		return new Ballot(rankForElement);
	}

}
