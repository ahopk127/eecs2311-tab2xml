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
 -The converted MusicXML tablature should be error free.

## Performance
 - Must parse the text tab in a reasonable amount of time.

## Supportability
 - Should allow the user to configure their preferences for how they want the drums sheet music to be displayed (selecting the value and noteheads). Can have a default notation but should be able to be customized through preferences.
 - Must be testable via automated testing

# Non-Functional Requirements
 - Must have an API that can be used by other programs


