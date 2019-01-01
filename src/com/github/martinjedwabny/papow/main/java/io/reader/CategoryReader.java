package com.github.martinjedwabny.papow.main.java.io.reader;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.martinjedwabny.papow.main.java.base.Category;
import com.github.martinjedwabny.papow.main.java.base.CategoryFamily;

public class CategoryReader {

	/**
	 * Generate a category map from string to string representing the families and categories.
	 * @param categoriesString
	 * @return categoryMap
	 */
	public static Map<String, String> asMap(String categoriesString) {
		Map<String, String> ans = new HashMap<String, String>();
		try {
			Matcher m = Pattern.compile("[^\\{,=]*=[^=,\\}]*").matcher(categoriesString.replaceAll("\\s+"," "));
			while (m.find()) {
				String occur = m.group();
				String[] familyAndCategory = occur.split("=");
				String familyString = familyAndCategory[0].trim();
				String categoryString = familyAndCategory[1].trim();
				if (!familyString.isEmpty() && !categoryString.isEmpty())
					ans.put(familyString, categoryString);
			}
		} catch (Exception e) {
			return null;
		}
		return ans;
	}
	
	/**
	 * Given a collection of CategoryFamily and a map from string to string representing a category per family,
	 * update the collection creating any new or updating any existing families.
	 * Finally return the categories mapped as an object.
	 * @param categories
	 * @param categoryMap
	 * @return updateMap
	 */
	public static Map<CategoryFamily, Category> updateAndGet(Collection<CategoryFamily> categories, Map<String, String> categoryMap) {
		if (categoryMap == null)
			return null;
		Map<CategoryFamily, Category> updateMap = new LinkedHashMap<CategoryFamily, Category>();
		categoryMap.forEach((familyString, categoryString) -> {
			CategoryFamily family = null;
			Optional<CategoryFamily> anyFamily = categories.stream().filter(f -> f.getDescription().equals(familyString)).findAny();
			if (anyFamily.isPresent()) {
				family = anyFamily.get();
			} else {
				family = new CategoryFamily(familyString, new HashSet<Category>());
				categories.add(family);
			}
			Category category = null;
			Optional<Category> anyCategory = family.getPossibilities().stream().filter(c -> c.getDescription().equals(categoryString)).findAny();
			if (anyCategory.isPresent()) {
				category = anyCategory.get();
			} else {
				category = new Category(categoryString);
				family.getPossibilities().add(category);
			}
			updateMap.put(family, category);
		});
		return updateMap;
	}
	
	/**
	 * Given a collection of CategoryFamily and a string representing a category per family,
	 * update the collection creating any new or updating any existing families.
	 * Finally return the categories mapped as an object.
	 * @param categories
	 * @param categoryMap
	 * @return updateMap
	 */
	public static Map<CategoryFamily, Category> updateAndGetFromString(Collection<CategoryFamily> categories, String categoriesString) {
		return updateAndGet(categories, asMap(categoriesString));
	}
	
}
