package wordgenerator.library;

import java.util.List;

/**
 * Libraries of words or letter combinations to be used by the words generators.
 */
public class Libraries {

	public static final String SEPARATORS_V1 = "SeparatorsV1";
	public static final String COMMASPEARATOR = "CommaSpearator";
	public static final String SPACESEPARATOR = "SpaceSeparator";
	public static final String WORDS_V3 = "WordsV3";
	public static final String WORDS_V2 = "WordsV2";
	public static final String WORDS_V1 = "WordsV1";

	public static final List<String> getLibrary(List<String> words, String library) {
		switch (library) {
		case WORDS_V1:
			return getWordsV1(words);
		case WORDS_V2:
			return getWordsV2(words);
		case WORDS_V3:
			return getWordsV3(words);
		case SPACESEPARATOR:
			return getSpaceSeparator(words);
		case COMMASPEARATOR:
			return getCommaSeparator(words);
		case SEPARATORS_V1:
			return getSeparatorsV1(words);
		default:
			return words;
		}
	}

	private static final List<String> getWordsV1(List<String> words) {
		words.add("Flibber");
		words.add("jibbet");
		words.add("Quibber");
		words.add("quab");
		words.add("Zig");
		words.add("zaggle");
		words.add("Wobble");
		words.add("flop");
		words.add("gook");
		words.add("blab");
		words.add("snack");
		words.add("Higgledy");
		words.add("piggledy");
		words.add("Malar");
		words.add("Gibberbam");
		words.add("Hobnob");
		words.add("Rigmarole");
		words.add("Jibber");
		words.add("jabber");
		words.add("Doozy");
		words.add("Fiddle");
		words.add("faddle");
		words.add("Dingy");
		words.add("Gobbledy");
		words.add("goo");
		words.add("Hoopla");
		words.add("Brouhaha");
		words.add("fuffle");
		words.add("Wigwam");
		words.add("Flummox");
		words.add("Flibberti");
		words.add("gibbet");
		words.add("Razzle");
		words.add("dazzle");
		words.add("Rumpus");
		words.add("Hodgepodge");
		words.add("zoop");
		words.add("fluffer");
		words.add("noodle");
		words.add("blibber");
		words.add("boop");
		words.add("zigglet");
		words.add("wirl");
		words.add("snicker");
		words.add("doodle");
		words.add("zippity");
		words.add("zorp");
		words.add("squibblesnack");
		words.add("gigglesnort");
		words.add("flump");
		words.add("whiz");
		words.add("bumble");
		words.add("fizz");
		words.add("fizzletastic");
		words.add("quirkle");
		words.add("quack");
		words.add("wobbly");
		words.add("wump");
		words.add("dizzlet");
		words.add("wizzle");
		words.add("zany");
		words.add("zoodle");
		words.add("crumple");
		words.add("giggle");
		words.add("twist");
		words.add("snazzle");
		words.add("frazz");
		words.add("quibble");
		words.add("froogle");
		words.add("flibberjib");
		words.add("zippityzap");
		words.add("whimsy");
		words.add("gobbledygook");
		words.add("wobblejumble");
		words.add("doodlebug");
		words.add("snickerdoodle");
		words.add("frazzlefizz");
		words.add("quirkyplop");
		words.add("zanywobble");
		words.add("noodlebop");
		words.add("gibberish");
		words.add("fizzlepop");
		words.add("wobblywump");
		words.add("gigglefest");
		words.add("babblesnack");
		words.add("dingleberry");
		words.add("flibberflap");
		words.add("zigzag");
		words.add("snarfblat");
		words.add("rhubarb");
		words.add("whatchamacallit");
		words.add("dinglehopper");
		words.add("wuzzle");
		words.add("gibberwobble");
		words.add("squishy");
		words.add("noodlewhiz");
		words.add("flibberwibble");
		words.add("gobbledegook");
		words.add("zippityzorp");
		words.add("jibberjab");
		words.add("blibberblob");
		words.add("fuzzletastic");
		words.add("wibblywobble");
		words.add("bopbop");
		words.add("dizzletwizzle");
		words.add("crumpleflop");
		words.add("noodlezap");
		words.add("fluffermuffin");
		words.add("dingleflap");
		words.add("zigglywiggly");
		words.add("mumbojumbo");
		words.add("hocuspocus");
		words.add("shenanigans");
		words.add("wigglywaggly");
		words.add("wobblegobble");
		return words;
	}

	private static final List<String> getWordsV2(List<String> words) {
		words.add("flop");
		words.add("wirl");
		words.add("crump");
		words.add("mumbo");
		words.add("wig");
		words.add("wam");
		words.add("nood");
		words.add("boop");
		words.add("bop");
		words.add("flib");
		words.add("jab");
		words.add("let");
		words.add("zip");
		words.add("erty");
		words.add("wiz");
		words.add("zug");
		words.add("zig");
		words.add("snick");
		words.add("snack");
		words.add("zorp");
		words.add("snaz");
		words.add("daz");
		words.add("zle");
		words.add("gib");
		words.add("gig");
		words.add("le");
		words.add("wob");
		words.add("gans");
		words.add("jumb");
		words.add("whim");
		words.add("quib");
		words.add("hob");
		words.add("raz");
		words.add("twist");
		words.add("blab");
		words.add("wump");
		words.add("zoop");
		words.add("snarf");
		words.add("blat");
		words.add("goo");
		words.add("zan");
		words.add("froo");
		words.add("dood");
		words.add("bug");
		return words;
	}

	private static final List<String> getWordsV3(List<String> words) {
		words.add("flop");
		words.add("wirl");
		words.add("crump");
		words.add("mum");
		words.add("boo");
		words.add("wig");
		words.add("wam");
		words.add("nood");
		words.add("boop");
		words.add("bop");
		words.add("flib");
		words.add("jab");
		words.add("let");
		words.add("zip");
		words.add("err");
		words.add("tyt");
		words.add("wiz");
		words.add("zug");
		words.add("zig");
		words.add("snick");
		words.add("snack");
		words.add("zorp");
		words.add("snaz");
		words.add("daz");
		words.add("zle");
		words.add("gib");
		words.add("gig");
		words.add("le");
		words.add("wob");
		words.add("gans");
		words.add("jumb");
		words.add("whim");
		words.add("quib");
		words.add("hob");
		words.add("raz");
		words.add("twist");
		words.add("blab");
		words.add("wump");
		words.add("zoop");
		words.add("snarf");
		words.add("blat");
		words.add("goo");
		words.add("zan");
		words.add("froo");
		words.add("dood");
		words.add("bug");
		words.add("Slab");
		words.add("Drab");
		words.add("Plip");
		words.add("Snip");
		words.add("Crux");
		words.add("Zop");
		words.add("Flit");
		words.add("Grub");
		words.add("Splat");
		words.add("Trig");
		words.add("Jib");
		words.add("Drip");
		words.add("Tug");
		words.add("Mop");
		words.add("Quip");
		words.add("Frob");
		words.add("Glip");
		words.add("Griz");
		words.add("Yip");
		words.add("Fob");
		words.add("Clap");
		words.add("Grap");
		words.add("Slit");
		words.add("Squib");
		words.add("Jig");
		words.add("Skid");
		words.add("Twix");
		words.add("Zab");
		words.add("Nix");
		words.add("Plub");
		words.add("Flub");
		words.add("Snub");
		words.add("Kip");
		words.add("Frip");
		words.add("Stix");
		words.add("Plop");
		words.add("Swab");
		words.add("Crib");
		words.add("Flip");
		words.add("Thud");
		words.add("Tix");
		words.add("Snog");
		words.add("Plump");
		words.add("Blip");
		words.add("Slig");
		words.add("Twip");
		words.add("Crub");
		words.add("Jip");
		words.add("Blop");
		words.add("Squig");
		words.add("Prig");
		words.add("Pug");
		words.add("Glub");
		words.add("Frib");
		words.add("Slub");
		words.add("Blib");
		words.add("Flug");
		words.add("Quig");
		words.add("Zib");
		words.add("Nib");
		words.add("Grib");
		words.add("Chig");
		words.add("Flig");
		words.add("Vix");
		words.add("Plig");
		words.add("Snag");
		words.add("Blub");
		words.add("Skib");
		words.add("Stig");
		words.add("Spig");
		words.add("Wrig");
		words.add("Drob");
		words.add("Brug");
		words.add("Flik");
		words.add("Swig");
		words.add("Chub");
		words.add("Trub");
		words.add("Zlig");
		words.add("Glug");
		words.add("Crig");
		words.add("Blug");
		words.add("Smig");
		words.add("Quog");
		words.add("Snig");
		words.add("Clig");
		words.add("Krib");
		return words;
	}

	private static final List<String> getSpaceSeparator(List<String> separators) {
		separators.add(" ");
		return separators;
	}

	private static final List<String> getCommaSeparator(List<String> separators) {
		separators.add(", ");
		return separators;
	}

	private static final List<String> getSeparatorsV1(List<String> separators) {
		separators.add(", ");
		separators.add(". ");
		separators.add("-");
		separators.add("! ");
		return separators;
	}

}
