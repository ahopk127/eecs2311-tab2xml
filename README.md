# About TAB2XML Version 0.1.0
This is an application that converts text tablature to MusicXML, created as a project for EECS 2311 in 2021 Winter.

# How to Use
1. Run the application using Gradle (`gradlew run`)
2. Input your text tab into the application.  There are multiple ways of doing this:
   - Type or copy-and-paste your text tab into the text box.  
   - Press the "Load from File" button then choose a file to load your text tab from a file.
3. Press the "Convert" button.  The text tab will be replaced with the corresponding MusicXML.
4. You can now copy-and-paste the MusicXML, or save it to a file using the "Save to File" button.
5. To go back to the original text tab, press the "Undo Conversion" button.
   *Tip: Press Ctrl-A then Delete to clear the text box*

# API
To convert tab to MusicXML in a program, use the following code:  
```
tab2xml.parser.Parser parser = new tab2xml.parser.Parser(INPUT, INSTRUMENT);  
String output = parser.parse();  
```

Notes:
 - INPUT is the text tab.  It should be a `String`, not a file.
 - INSTRUMENT is the instrument the tab is for.  It should be an instance of `tab2xml.parser.Instrument`.
 - You will need to handle the checked exceptions thrown by `Parser.parse()`
