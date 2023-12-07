package wordgenerator.words.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import wordgenerator.library.Libraries;
import wordgenerator.utils.Util;
import wordgenerator.words.Words;
import wordgenerator.words.WordsLocal;

/**
 * An implementations of the Words class and the WordsLocal interface that
 * generates words.
 * 
 * The result of the generated words is built in a form of sentences where some
 * words are used more common and others less common.
 */
public class SentenceWordsImpl extends Words implements WordsLocal {

	List<String> shortWords = new ArrayList<>();
	List<String> mediumWords = new ArrayList<>();
	List<String> longWords = new ArrayList<>();

	Random random = new Random();

	public SentenceWordsImpl() {
		this(constructorType.NORMAL);
		init();
	}

	public SentenceWordsImpl(constructorType type) {
		super(type);
		library = Libraries.WORDS_V2;
		version = "V4";
	}

	private void init() {
		addWords();

		shortWords = Util.generateAndCombineWords(words, 0);
		mediumWords = Util.generateAndCombineWords(shortWords, shortWords);
		List<String> randomMediumWords = new ArrayList<>(mediumWords);
		Collections.shuffle(randomMediumWords);
		longWords = Util.generateAndCombineWords(mediumWords.subList(0, shortWords.size()), shortWords);
	}

	@Override
	public List<String> getWords(int wordsToPrint) {
		return super.getWords(wordsToPrint);
	}

	private List<String> generateSentinceWords() {
		List<String> resultWords = new ArrayList<>();

		List<String> adjectiveWords = randomRarity(90, 95).subList(0, 3);
		List<String> commonWords = randomRarity(80, 95);
		List<String> normalWords = randomRarity(2, 60);
		normalWords.addAll(randomRarity(2, 80));
		normalWords.addAll(randomRarity(2, 80));
		normalWords.addAll(randomRarity(2, 80));
		normalWords.addAll(randomRarity(2, 80));
		normalWords.addAll(randomRarity(2, 80));
		List<String> rareWords = randomRarity(1, 10);
		rareWords.addAll(randomRarity(0, 0));
		rareWords.addAll(randomRarity(0, 0));
		rareWords.addAll(randomRarity(0, 0));
		rareWords.addAll(randomRarity(0, 0));
		rareWords.addAll(randomRarity(0, 0));
		rareWords.addAll(randomRarity(0, 0));

		int adjectiveWord = 10;
		int commonWord = 25;
		int normalWord = 50;

		for (int i = 0; i < mediumWords.size(); i++) {
			int randomValue = random.nextInt(100); // Random number between 0 and 99

			if (randomValue <= adjectiveWord) {
				int nextRandomWord = random.nextInt(adjectiveWords.size());
				resultWords.add(adjectiveWords.get(nextRandomWord));
			} else if (randomValue <= commonWord) {
				int nextRandomWord = random.nextInt(commonWords.size());
				resultWords.add(commonWords.get(nextRandomWord));
			} else if (randomValue <= normalWord) {
				int nextRandomWord = random.nextInt(normalWords.size());
				resultWords.add(normalWords.get(nextRandomWord));
			} else {
				int nextRandomWord = random.nextInt(rareWords.size());
				resultWords.add(rareWords.get(nextRandomWord));
			}
		}

		return resultWords;
	}

	private List<String> randomRarity(int shortWord, int mediumWord) {
		List<String> commonWords = new ArrayList<>();

		List<String> localShortWords = new ArrayList<>(shortWords);
		List<String> localMediumWords = new ArrayList<>(mediumWords);
		List<String> localLongWords = new ArrayList<>(longWords);

		Collections.shuffle(localShortWords);
		Collections.shuffle(localMediumWords);
		Collections.shuffle(localLongWords);

		for (int i = 0; i < shortWords.size(); i++) {
			int randomValue = random.nextInt(100); // Random number between 0 and 99

			if (randomValue <= shortWord) {
				commonWords.add(localShortWords.get(0));
				localShortWords.remove(0);
			} else if (randomValue <= mediumWord) {
				commonWords.add(localMediumWords.get(0));
				localMediumWords.remove(0);
			} else {
				commonWords.add(localLongWords.get(0));
				localLongWords.remove(0);
			}
		}
		return commonWords;
	}

	@Override
	public void shuffle() {
		words = generateSentinceWords();
	}

	protected void addWords() {
		Libraries.getLibrary(words, library);
	}

}
