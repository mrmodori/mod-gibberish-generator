package wordgenerator.words;

import java.util.List;

public interface WordsLocal {

	List<String> getWords(int wordsToPrint);

	void shuffle();

	String version();

	List<String> getSeparators();

	boolean acceptsWords();

	void addWord(String word);

	String containsDuplicates();

}