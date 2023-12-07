package wordgenerator.words.impl;

import java.util.List;

import wordgenerator.library.Libraries;
import wordgenerator.words.Words;
import wordgenerator.words.WordsLocal;

/**
 * A basic implementation of the Words class and the WordsLocal interface.
 */
public class WordsImpl extends Words implements WordsLocal {

	public WordsImpl() {
		this(constructorType.NORMAL);
		addWords(words);
	}

	public WordsImpl(constructorType type) {
		super(type);
		library = Libraries.WORDS_V1;
		version = "V1";
	}

	private void addWords(List<String> words) {
		Libraries.getLibrary(words, library);
	}

}
