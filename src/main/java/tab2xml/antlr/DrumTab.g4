grammar DrumTab;

@header{
	package tab2xml.antlr; 
	
}

sheet
	: (.*? staff .*?)* EOF			
	;
	
staff								
	: NEWLINE? string+ NEWLINE?
	;
	
string
	: tune stringItems SPACE* NEWLINE            // may be not right
	;
tune
	: NOTE? BAR           // may be not right
	;
	
	/* Tokens */
NOTE          
	: [x-o]?    // may be not right
	;
BAR
	: '|'
	;
	
HYPHEN 
	: '-' -> channel(HIDDEN)
	;
SPACE
	: [ \t]
	;

NEWLINE
	: SPACE* ('\r\n' | '\n')
	;
	
MULTI_COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
    ;