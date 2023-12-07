package wordgenerator.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wordgenerator.library.Libraries;
import wordgenerator.utils.Util;

/**
 * An abstract words class that holds the basic functionality.
 * 
 * Use this to create implementations of the WordsLocal class.
 */
public abstract class Words implements WordsLocal {

	protected List<String> words;
	protected String word;
	protected List<String> separators;
	protected String library;
	protected String version;

	private static final String DEFAULT_VERSION = "V0";
	private static final String DEFAULT_LIBRARY = "N/A";

	/**
	 * The types of constructors to be used.
	 * 
	 * {@link #NORMAL} Is used for a normal constructor.
	 * 
	 * {@link #VERSION} Is used if you only want to get the version.
	 */
	public enum constructorType {
		NORMAL, VERSION
	}

	public Words() {
		this(constructorType.NORMAL);
	}

	/**
	 * @param type What type of constructor used.
	 */
	public Words(constructorType type) {
		switch (type) {
		case NORMAL:
			words = new ArrayList<String>();
			separators = Libraries.getLibrary(new ArrayList<>(), Libraries.SPACESEPARATOR);
		case VERSION:
			version = DEFAULT_VERSION;
			library = DEFAULT_LIBRARY;
		}
	}

	@Override
	public List<String> getWords(int wordsToPrint) {
		int minNumberOfWords = Math.min(words.size(), wordsToPrint);
		return words.subList(0, minNumberOfWords);
	}

	@Override
	public void shuffle() {
		Collections.shuffle(words);
	}

	@Override
	public String version() {
		return version + " - " + library;
	}

	@Override
	public List<String> getSeparators() {
		return separators;
	}

	@Override
	public boolean acceptsWords() {
		return false;
	}

	@Override
	public void addWord(String word) {
		this.word = word;
	}

	@Override
	public String containsDuplicates() {
		return Util.containsDuplicates(words);
	}

}
