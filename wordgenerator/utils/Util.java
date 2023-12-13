package wordgenerator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wordgenerator.words.Words.constructorType;
import wordgenerator.words.WordsLocal;

public class Util {

	public static final List<String> getClassNamesInPackage(String packageName) {
		List<String> classNames = new ArrayList<>();
		String packagePath = packageName.replace('.', '/');

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<java.net.URL> resources = classLoader.getResources(packagePath);

			while (resources.hasMoreElements()) {
				java.net.URL resource = resources.nextElement();

				if (resource.getProtocol().equals("file")) {
					classNames.addAll(findClassNamesInDirectory(packagePath, resource.getFile()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classNames;
	}

	public static final List<String> findClassNamesInDirectory(String packagePath, String directoryPath) {
		List<String> classNames = new ArrayList<>();
		File directory = new File(directoryPath);

		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles(file -> file.isFile() && file.getName().endsWith(".class"));

			if (files != null) {
				for (File file : files) {
					String className = file.getName().replace(".class", "");
					classNames.add(className);
				}
			}
		}

		return classNames;
	}

	public static final WordsLocal createClassesInPackage(String packageName, String className, constructorType type)
			throws Exception {
		if (type == constructorType.VERSION) {
			return (WordsLocal) Class.forName(packageName + "." + className)
					.getDeclaredConstructor(constructorType.class).newInstance(type);
		} else {
			return (WordsLocal) Class.forName(packageName + "." + className).getDeclaredConstructor().newInstance();
		}
	}

	public static final String containsDuplicates(List<String> words) {
		List<String> duplicateWords = new ArrayList<>();

		Set<String> set = new HashSet<>();
		for (String word : words) {
			String lowerCaseWord = word.toLowerCase();
			if (set.contains(lowerCaseWord)) {
				duplicateWords.add(lowerCaseWord);
			}
			set.add(lowerCaseWord);
		}
		if (duplicateWords.isEmpty()) {
			return "false";
		} else {
			StringBuilder resultBuilder = new StringBuilder();
			resultBuilder.append("Nr: ");
			resultBuilder.append(duplicateWords.size());
			resultBuilder.append(" Words: [");
			for (int i = 0; i < duplicateWords.size(); i++) {
				resultBuilder.append(duplicateWords.get(i).toLowerCase());
				if (i < duplicateWords.size() - 1) {
					resultBuilder.append(", ");
				} else {
					resultBuilder.append("]");
				}
			}
			return resultBuilder.toString();
		}
	}

	private static final String COLOR_GREEN = "\u001B[32m"; // Green
	private static final String COLOR_RED = "\u001B[31m"; // Red
	private static final String COLOR_RESET = "\u001B[0m"; // Reset color to default

	public static final String surroundWithGreen(String text) {
		return COLOR_GREEN + text + COLOR_RESET;
	}

	public static final String surroundWithRed(String text) {
		return COLOR_RED + text + COLOR_RESET;
	}

	// Not in use.
	public static final String addRandomCommasToString(List<String> inputList, int nrOfWords, int minWords,
			int maxWords, int percentage) {
		String separator = ",";
		List<String> separators = new ArrayList<>();
		separators.add(separator);
		return addSeparators(inputList, nrOfWords, new SeparatorConfiguration(minWords, maxWords, percentage),
				separators);
	}

	public static final String addSeparators(List<String> inputList, int nrOfWords,
			SeparatorConfiguration separatorConfig, List<String> separators) {
		Random random = new Random();

		StringBuilder resultBuilder = new StringBuilder();
		int lastSeparator = 0;

		for (int i = 0; i < nrOfWords && i < inputList.size(); i++) {

			// Append the current word from the list
			resultBuilder.append(inputList.get(i).toLowerCase());

			// Check if we can add the separator
			if (i < nrOfWords - 1) {
				int numWordsBeforeSeparator = random.nextInt(separatorConfig.getMaxWords() + 1)
						+ separatorConfig.getMinWords();

				int randomValue = random.nextInt(100); // Random number between 0 and 99

				// Check the conditions for adding a separator
				if (randomValue <= separatorConfig.getPercentage()) {
					resultBuilder.append(getRandomSeparator(separators));
					lastSeparator = 0; // Reset the count of words since the last separator
				} else {
					resultBuilder.append(" ");
					lastSeparator++;

					// If we've reached the maximum words before a separator, reset the count
					if (lastSeparator >= numWordsBeforeSeparator) {
						lastSeparator = 0;
					}
				}
			}
		}

		// To capitalize the letters after a dot.
		Pattern pattern = Pattern.compile("([.\\!]+\\s*)([a-z])");
		Matcher matcher = pattern.matcher(resultBuilder.toString().trim());
		StringBuffer stringBuffer = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(stringBuffer, matcher.group().toUpperCase());
		}
		matcher.appendTail(stringBuffer);

		String result = checkAndRemoveLastSeparator(stringBuffer.toString(), separators);

		return result + ".";
	}

	private static final String checkAndRemoveLastSeparator(String result, List<String> separators) {
		for (String separator : separators) {
			if (result.endsWith(separator.trim())) {
				return result.substring(0, result.length() - separator.trim().length());
			}
		}
		return result;
	}

	private static final String getRandomSeparator(List<String> separators) {
		List<String> shuffledSeparators = new ArrayList<>(separators);
		Collections.shuffle(separators);
		return shuffledSeparators.get(0);
	}

	public static final Map<String, String> getClassVersion(String packageName, List<String> classNames)
			throws Exception {
		Map<String, String> result = new LinkedHashMap<>();
		for (String name : classNames) {
			result.put(name, createClassesInPackage(packageName, name, constructorType.VERSION).version());
		}
		return result;
	}

	// ---

	private static final int MAX_RUNS = 4;

	public static final List<String> generateAndCombineWords(List<String> words, int numberOfRepeat) {
		List<String> resultWords = new LinkedList<>();

		// Adds each word if not duplicate.
		for (String word : words) {
			addWordIfNotDuplicate(resultWords, word);
		}

		// Run the combine method "MAX_RUNS or numberOfRepeats"-times.
		for (int i = 0; i < Math.min(numberOfRepeat, MAX_RUNS); i++) {
			// Combine the result words and the original list of words.
			resultWords = generateAndCombineWords(resultWords, new LinkedList<String>(words));
		}

		// Return a new ArrayList with the words.
		return new ArrayList<String>(resultWords);
	}

	public static final List<String> generateAndCombineWords(List<String> combinedWords, List<String> words) {
		List<String> resultWords = new LinkedList<>(combinedWords);
		Set<String> addedWordsInRound = new LinkedHashSet<>();

		for (String word : words) {
			for (String resultWord : combinedWords) {

				// Combine the words
				String combinedWord = word + resultWord;

				if (addedWordsInRound.add(combinedWord)) {
					// Add the combined word.
					resultWords = addWordIfNotDuplicate(resultWords, combinedWord);
				}
				// Combine the word with the 2nd word first.
				combinedWord = resultWord + word;

				if (addedWordsInRound.add(combinedWord)) {
					// Add the new combined word.
					resultWords = addWordIfNotDuplicate(resultWords, combinedWord);
				}
			}

		}
		return resultWords;
	}

	public static final List<String> addWordIfNotDuplicate(List<String> words, String word) {
		if (words.contains(word)) {
			return words;
		} else {
			words.add(word);
			return words;
		}
	}

}
