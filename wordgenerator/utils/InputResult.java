package wordgenerator.utils;

/**
 * Container class to handle the user input.
 */
public class InputResult {

	resultType type;
	int intResult = 0;
	String stringResult = "";
	boolean booleanResult = true;

	public InputResult(resultType type) {
		this(type, 0, "", true);
	}

	public InputResult(resultType type, String stringResult) {
		this(type, 0, stringResult, true);
	}

	public InputResult(resultType type, boolean booleanResult) {
		this(type, 0, "", booleanResult);
	}

	public InputResult(resultType type, int intResult) {
		this(type, intResult, "", true);
	}

	private InputResult(resultType type, int intResult, String stringResult, boolean booleanResult) {
		this.type = type;
		this.intResult = intResult;
		this.stringResult = stringResult;
		this.booleanResult = booleanResult;
	}

	public resultType getType() {
		return type;
	}

	public int getNumberOfWords() {
		return intResult;
	}

	public String getString() {
		return stringResult;
	}

	public boolean useSeparator() {
		return booleanResult;
	}

}
