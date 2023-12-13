package wordgenerator.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import wordgenerator.library.Libraries;
import wordgenerator.utils.InputResult;
import wordgenerator.utils.SeparatorConfiguration;
import wordgenerator.utils.Util;
import wordgenerator.utils.resultType;
import wordgenerator.words.Words.constructorType;
import wordgenerator.words.WordsLocal;

/**
 * The main class that holds the program and loop together.
 */
public class GibberishGenerator {

	private static final String EXIT = "exit";
	private static final String HELP = "help";
	private static final String DICTIONARY = "lib";
	private static final String SEPARATOR = "sep";
	private static final String WORD = "word";

	private static final String MESSAGE_INPUT = "Enter a number, help or exit to quit: ";
	private static final String MESSAGE_HELP[] = { "Here are the list of commands:",
			"x - Where x is any number. Uses the dictionary and generates that many words.",
			SEPARATOR + " - Enable/Disable the use of separators.",
			DICTIONARY + " - Brings up the menu to select a new dictionary to use.",
			HELP + " - Brings up this help menu.", WORD + " - Adds/Replaces the word in the dictionary.",
			EXIT + " - Exits the program" };
	private static final String MESSAGE_WELCOME = "Welcome to the gibberish generator.";

	private static final String DICTIONARY_PACKAGE = "wordgenerator.words.impl";

	public void init() {

		// Get the scanner
		try (Scanner scanner = getNewScanner()) {

			// Greet the user.
			System.out.println(MESSAGE_WELCOME);

			// Ask the user what words implementation to use.
			WordsLocal words = getDictionary();

			// Inform the user if the dictionary contains duplicates.
			System.out.println("Contains duplicate words: " + words.containsDuplicates());

			// Start the loop.
			programLoop(words);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Scanner getNewScanner() {
		return new Scanner(System.in);
	}

	/**
	 * Main program loop.
	 * 
	 * @throws Exception
	 */
	private void programLoop(WordsLocal startingWordsLibrary) throws Exception {

		WordsLocal words = startingWordsLibrary;
		boolean separator = true;

		// Start and loop the program.
		for (boolean continueProgram = true; continueProgram;) {

			// Ask user what to do.
			InputResult inputResult = readAndProcessInput(getNewScanner(), MESSAGE_INPUT);

			// Process the command.
			switch (inputResult.getType()) {
			case PRINT_WORDS:
				generateWords(words, inputResult.getNumberOfWords(), separator);
				break;
			case CHANGE_DICTIONARY:
				words = getDictionary();
				break;
			case CHANGE_SEPARATOR:
				separator = changeSeparator(separator);
				break;
			case ADD_WORD:
				words.addWord(getWord());
				break;
			case HELP:
				printHelpText();
				break;
			case EXIT:
				continueProgram = false;
				break;
			default:
				System.out.println("Not implemented: " + inputResult.getType());
				continueProgram = false;
				break;
			}
		}
	}

	private String getWord() {
		System.out.println("Enter the word to replace with.");
		String result = null;
		Scanner scanner = getNewScanner();
		if (scanner.hasNext()) {
			result = scanner.next();
			System.out.println("Word changed to: " + result);
		}
		return result;
	}

	private void printHelpText() {
		for (String helpText : MESSAGE_HELP) {
			System.out.println(Util.surroundWithGreen(helpText));
		}
	}

	private boolean changeSeparator(boolean separator) {
		String result = (!separator ? Util.surroundWithGreen("On") : Util.surroundWithRed("Off"));
		System.out.println("Switching the separator to be: " + result);
		return !separator;
	}

	private void generateWords(WordsLocal words, Integer numberOfWordsToPrint, boolean separator) {

		// Shuffle the words.
		words.shuffle();

		List<String> separators = getSeparators(separator);

		// 3 min words, 5 max words, 50 percent

		String result = Util.addSeparators(words.getWords(numberOfWordsToPrint - 1), numberOfWordsToPrint,
				new SeparatorConfiguration(4, 3, 20), separators);

		System.out.println(Character.toUpperCase(result.charAt(0)) + result.substring(1));
	}

	private List<String> getSeparators(boolean separator) {
		if (separator) {
			return Libraries.getLibrary(new ArrayList<>(), Libraries.SEPARATORS_V1);
		} else {
			return Libraries.getLibrary(new ArrayList<>(), Libraries.SPACESEPARATOR);
		}
	}

	private WordsLocal getDictionary() throws Exception {
		System.out.println("Select the dictionary:");

		List<String> classNames = Util.getClassNamesInPackage(DICTIONARY_PACKAGE);
		Map<String, String> classVersion = Util.getClassVersion(DICTIONARY_PACKAGE, classNames);

		Collections.sort(classNames);

		System.out.println("Class Names in Package " + DICTIONARY_PACKAGE + ":");

		for (int i = 0; i < classNames.size(); i++) {
			System.out.println(i + 1 + ": " + Util.surroundWithGreen(classNames.get(i)) + " "
					+ classVersion.get(classNames.get(i)));
		}

		InputResult result = readAndProcessInput(getNewScanner(), MESSAGE_INPUT);

		if (result.getNumberOfWords() - 1 < classNames.size() && result.getType() == resultType.PRINT_WORDS) {
			return Util.createClassesInPackage(DICTIONARY_PACKAGE, classNames.get(result.getNumberOfWords() - 1),
					constructorType.NORMAL);
		} else {
			System.out.println(Util.surroundWithRed("Invalid input, try again."));
			return getDictionary();
		}
	}

	public InputResult readAndProcessInput(Scanner scanner, String message) {
		System.out.println(message);
		if (scanner.hasNextInt()) {
			int number = scanner.nextInt();
			return new InputResult(resultType.PRINT_WORDS, number);
		} else if (scanner.hasNext(SEPARATOR)) {
			return continueScanner(new InputResult(resultType.CHANGE_SEPARATOR), scanner);
		} else if (scanner.hasNext(DICTIONARY)) {
			return continueScanner(new InputResult(resultType.CHANGE_DICTIONARY), scanner);
		} else if (scanner.hasNext(HELP)) {
			return continueScanner(new InputResult(resultType.HELP), scanner);
		} else if (scanner.hasNext(EXIT)) {
			return continueScanner(new InputResult(resultType.EXIT), scanner);
		} else if (scanner.hasNext(WORD)) {
			return continueScanner(new InputResult(resultType.ADD_WORD), scanner);
		} else {
			String input = scanner.nextLine();
			System.out.println("Invalid input: '" + input + "'. Expected a number or command. Type " + HELP
					+ " for help or " + EXIT + ".");
			return readAndProcessInput(scanner, message); // Continue looping for valid input.
		}
	}

	private InputResult continueScanner(InputResult result, Scanner scanner) {
		scanner.nextLine();
		return result;
	}

}