# Mod Gibberish Generator

Mod Gibberish Generator is a java program created to generate gibberish.

## TODO's

*  PrimeSubGenerator
    * Refactor all Strings to constants.
    * Make sentence maker.
    * Add speaker mode.
    * Check error input when starting the program.
    * Possible memory leakage in readAndProcessInput?   
*  InputResult
    * All the constructors only set 1 value and default on the rest, make sure that the other values are not used?
*  Util
    * Refactor the whole class.
    * Could probably improve generateAndCombineWords() method.
    * Is addWordIfNotDuplicate() needed?
*  Impl-classes
    * Refactor PrioritySentenceWordsImpl and SentenceWordsImpl.
    
## Known Bugs

*  When generating words with separators, sometimes it can end with dual separators. Example: "Word!." or "Word,.".
*  Program crashes when writing something other than a number when starting.

## Usage

Run the java program as a console.

```bash
Welcome to the gibberish generator.
Select the dictionary:
Class Names in Package wordgenerator.words.impl:
1: PrioritySentenceWordsImpl V5 - WordsV2 & WordsV3 [gib]
2: SentenceWordsImpl V4 - WordsV2
3: WordsImpl V1 - WordsV1
Enter a number, help or exit to quit: 
3
Contains duplicate words: false
Enter a number, help or exit to quit: 
10
Doodle, hodgepodge hobnob zig zoodle snack zanywobble! Rigmarole quack.
Enter a number, help or exit to quit: 
exit
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

See the License file.
