package wordgenerator.words.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import wordgenerator.library.Libraries;
import wordgenerator.utils.Util;
import wordgenerator.words.Words;
import wordgenerator.words.WordsLocal;

public class PrioritySentenceWordsImpl extends Words implements WordsLocal {

	protected List<String> shortWords = new ArrayList<>();
	protected List<String> mediumWords = new ArrayList<>();
	protected List<String> longWords = new ArrayList<>();

	protected List<String> wordRelatedWords = new ArrayList<>();
	protected List<String> specialWordWords = new ArrayList<>();

	protected List<String> mediumSpecialWords = new ArrayList<>();
	protected List<String> mediumSpecialRelatedWords = new ArrayList<>();

	private static final String DEFAULT_WORD = "gib";

	Random random = new Random();

	public PrioritySentenceWordsImpl() {
		this(constructorType.NORMAL);
		init();
	}

	public PrioritySentenceWordsImpl(constructorType type) {
		super(type);
		library = Libraries.WORDS_V2;
		version = "V5";
		word = DEFAULT_WORD;
	}

	private void init() {

		addWords();

		createSpecialWordList(words.size());

		shortWords = Util.generateAndCombineWords(words, 0);

		shortWords.add(word);

		mediumWords = Util.generateAndCombineWords(shortWords, shortWords);

		mediumSpecialWords = Util.generateAndCombineWords(shortWords, wordRelatedWords);
		mediumSpecialRelatedWords = Util.generateAndCombineWords(shortWords, specialWordWords);

		mediumWords.addAll(mediumSpecialRelatedWords);
		mediumWords.addAll(mediumSpecialWords);

		List<String> randomMediumWords = new ArrayList<>(mediumWords);
		Collections.shuffle(randomMediumWords);

		longWords = Util.generateAndCombineWords(mediumWords.subList(0, shortWords.size()), specialWordWords);

	}

	private void emptyAllWordLists() {
		shortWords = new ArrayList<>();
		mediumWords = new ArrayList<>();
		longWords = new ArrayList<>();
		wordRelatedWords = new ArrayList<>();
		specialWordWords = new ArrayList<>();
		mediumSpecialWords = new ArrayList<>();
		mediumSpecialRelatedWords = new ArrayList<>();
		words = new ArrayList<>();
	}

	@Override
	public void addWord(String word) {
		super.addWord(word);
		emptyAllWordLists();
		init();
	}

	private void createSpecialWordList(int size) {
		for (int i = 0; i < size; i++) {
			this.specialWordWords.add(word);
		}
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
		List<String> rarityWords = new ArrayList<>();

		List<String> localShortWords = new ArrayList<>(shortWords);
		List<String> localMediumWords = new ArrayList<>(mediumWords);
		List<String> localLongWords = new ArrayList<>(longWords);

		localShortWords.addAll(specialWordWords);

		Collections.shuffle(localShortWords);
		Collections.shuffle(localMediumWords);
		Collections.shuffle(localLongWords);

		for (int i = 0; i < shortWords.size(); i++) {
			int randomValue = random.nextInt(100); // Random number between 0 and 99

			if (randomValue <= shortWord) {
				rarityWords.add(localShortWords.get(0));
				localShortWords.remove(0);
			} else if (randomValue <= mediumWord) {
				rarityWords.add(localMediumWords.get(0));
				localMediumWords.remove(0);
			} else {
				rarityWords.add(localLongWords.get(0));
				localLongWords.remove(0);
			}
		}
		return rarityWords;
	}

	@Override
	public void shuffle() {
		words = generateSentinceWords();
	}

	@Override
	public boolean acceptsWords() {
		return true;
	}

	@Override
	public String version() {
		return super.version() + " & " + Libraries.WORDS_V3 + " [" + word + "]";
	}

	protected void addWords() {
		int nrOfWords = 10;

		super.addWord(word);

		for (int i = 0; i < nrOfWords; i++) {
			words.add(word);
		}

		Libraries.getLibrary(words, Libraries.WORDS_V2);
		Libraries.getLibrary(wordRelatedWords, Libraries.WORDS_V3);
	}

}
