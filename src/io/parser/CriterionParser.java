package io.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.criterion.Criterion;
import io.criterion.CriterionAnd;
import io.criterion.CriterionEmpty;
import io.criterion.CriterionEquals;
import io.criterion.CriterionOr;

public class CriterionParser {

	/**
	 * Given a criterion for filtering votes represented as a String,
	 * construct the object representing that criterion.
	 * @param scriteria String representing the criterion
	 * @return criterion specified
	 */
	public static Criterion parseCriterion(String scriteria) {
		if (scriteria == null)
			return new CriterionEmpty();
		CriterionOr criterion = new CriterionOr();
		List<String> criterias = stringArrayToArrayOfStrings(scriteria,'[',']');
		for (String ssubcriteria : criterias) {
			// Subcriteria
			CriterionAnd subcriterion = new CriterionAnd();
			if (ssubcriteria.length() > 1) {
				List<String> subcriterias = stringArrayToArrayOfStrings(ssubcriteria,'(',')');
				for (String c : subcriterias) {
					// Subsubcriteria
					if (c.length() > 1) {
						String[] fc = c.substring(1, c.length()-1).split(",");
						String family = fc[0], category = fc[1];
						Criterion subsubcriterion = new CriterionEquals(family, category);
						subcriterion.addCriterion(subsubcriterion);
					} else {
						subcriterion.addCriterion(new CriterionEmpty());
					}
				}
			}
			criterion.addCriterion(subcriterion);
		}
		return criterion;
	}
	
	/**
	 * Given start and end characters, return the strings contained between start and end characters.
	 * @param stringArray
	 * @param start
	 * @param end
	 * @return strings separated between start and end characters
	 */
	private static List<String> stringArrayToArrayOfStrings(String stringArray, Character start, Character end) {
		 List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile("\\"+start+"[^\\"+start+"]*\\"+end).matcher(stringArray);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }
		return allMatches;
	}
}
