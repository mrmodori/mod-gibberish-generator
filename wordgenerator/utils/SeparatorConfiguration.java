package wordgenerator.utils;

/**
 * Container class for the configuration of the separators.
 */
public class SeparatorConfiguration {
	private int minWords;
	private int maxWords;
	private int percentage;

	public SeparatorConfiguration(int minWords, int maxWords, int percentage) {
		this.minWords = minWords;
		this.maxWords = maxWords;
		this.percentage = percentage;
	}

	public int getMinWords() {
		return minWords;
	}

	public int getMaxWords() {
		return maxWords;
	}

	public int getPercentage() {
		return percentage;
	}
}