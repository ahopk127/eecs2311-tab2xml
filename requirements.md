# Functional Requirements
## Functionality
 - Must convert text tablature to MusicXML tablature
 - Must be able to read guitar, drum and bass text tabs
 - Must be able to access and edit the original text tab after conversion
 - Must be able to account for drop tunings
 - Must be able to account for an unusual amount of strings
 - Must be compatible with music in any time signature or key
 - Should be able to notate techniques such as bends, slides, hammer-ons, pull-offs, etc.

## Usability
 - Must be a GUI program which is intuitive for users.
 - Must be able to accept copy-pasted text or text read from a file
 - Must automatically detect which instrument the tab is for
 - Must allow the user to override this instrument detection

## Reliability
 - Must be able to work with lots of variation in the format of the text tab
 - The converted MusicXML tablature must be error free.

## Performance
 - Must parse the text tab in a reasonable amount of time.

## Supportability
 - Should allow the user to configure their preferences for how they want the drums sheet music to be displayed (selecting the value and noteheads). Can have a default notation but should be able to be customized through preferences.
 - Must be testable via automated testing

# Non-Functional Requirements
 - Should have an API that can be used by other programs

# Use Cases
## Convert Text Tab
Primary Actor: Musician
Goal: The musician has a text tab, and they want a MusicXML file
Success Scenario:
1. Musician starts program
2. Musician inputs the text tab
3. System identifies what instrument it is for
4. Musician tells system to convert text tab
5. System converts text tab to MusicXML
6. Musician saves output
7. Musician closes program

### Extensions
3a. If system cannot identify instrument, user can choose instrument manually.
5a. If text tab is unparseable, notify user and restart at step 2

## Change Settings
Primary Actor: Musician
Success Scenario:
1. Musician starts program (if it isn't already started)
2. Musician changes settings
3. System updates settings internally
4. Musician stops program or continues using program for something else
