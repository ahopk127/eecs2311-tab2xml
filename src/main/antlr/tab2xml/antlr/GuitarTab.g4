grammar GuitarTab;

@header {
	package tab2xml.antlr;
}

sheet
	: staff* EOF	
	;
	
staff								
	: NEWLINE* string+ NEWLINE*
	;

string
	: SPACE* tune SPACE* stringItems SPACE* NEWLINE?
	;

stringItems
	:(fret
	| harmonic 
	| pulloff
	| hammeron
	| slide 
	| hampullchain
	| BAR
	| DOUBLEBAR 
	| HYPHEN)*	(BAR | DOUBLEBAR)		
	;	

fret
   : FRET_NUM			
   ;

harmonic
	:  '[' fret ']'				
	;

pulloff
	: 'g'? fret 'p' fret				
	;

hammeron 
	: 'g'? fret 'h' fret				
	;

slide
	: 'g'? fret 's' fret				
	;
		
hampullchain
	: 'g'? fret ('h' | 'p')  fret  (('h' | 'p') fret)+		# HammerPull
	;

tune
	: NOTE? SPACE? SPACE? SPACE? BAR
	;

/* Tokens */

NOTE
	: [a-gA-G]'#'?
	;
	
FRET_NUM
	: [0-9]+
	;

DOUBLEBAR
	: '*'? (BAR | FRET_NUM) '|''|'?'*'?
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
	: SPACE* '\r'? '\n'
	;

MULTI_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;
    