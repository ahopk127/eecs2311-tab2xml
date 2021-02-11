# Front-End Tests
## PresenterTest
 - `testLoadFromFile()`: Tests that the "Load from File" command works properly.
 - `testSaveToFile()`: Tests that the "Save to File" command works properly.
 
## PromptingTextAreaTest
 - `testPrompt1()`: Tests that the text box reacts properly to focus changes.
 - `testPrompt2()`: Tests that the text box reacts properly to typing and when methods are run on it.
 - `testPromptColourFont()`: Tests that the text box's colour and font is set properly.
 
## ViewTest
Any test in this section that requires use of an unimplemented optional method is skipped.
 - `testInputText()`: Tests that all of the standard views can correctly get and set their input text
 - `testOutputText()`: Tests that all of the standard views can correctly get and set their output text
 - `testInstrumentSelection()`: Tests that all of the standard views can correctly get and set their instrument selection
 
 # Back-End Tests
## ParserTest
 - `testTokenizeGuitar()`: Tests that Guitar tablature is split into proper toknens.
 - `testInvalidTuneException()`: Tests if a the Guitar tablature tune is invalid.
 - `testTokenizeDrum()`: [To-Do]
 
## NoteTest
 - `testNoteType()`: Tests that the text box reacts properly to focus changes.
 - `testNotevalue1()` 
        .
        .
   `testNoteValue12()`: Tests that the each of the 12 notes are converted properly.
 - `testNoteIndex1()` 
        .
        .
   `testNoteIndex12()`: Tests that the each of the 12 note indices are correct.
 - `testToNoteInvalid()`: Tests that trying to convert an invalid string to a note is invalid. 
