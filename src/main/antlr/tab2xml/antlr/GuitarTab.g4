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
	: tune stringItems SPACE* NEWLINE?
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
	| HYPHEN)+	(BAR | DOUBLEBAR)		
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
	
BAR
	: '|'
	;
	
DOUBLEBAR
	: '*'? (BAR | FRET_NUM) '|''*'?
	;
	
FRET_NUM
	: [0-9]+
	;
	
HYPHEN 
	: '-' 
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
    