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

tune
	: NOTE? BAR
	;

stringItems
	:(HYPHEN 
	| hampullchain
	| pulloff
	| hammeron
	| slide 
	| harmonic 
	| fret 
	| BAR
	| DOUBLEBAR)+	(BAR | DOUBLEBAR)		
	;	

hampullchain
	: 'g'? fret ('h' | 'p')  fret  (('h' | 'p') fret)+		# HammerPull
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

harmonic
	:  '[' fret ']'				
	;

fret
   : FRET_NUM			
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
	
HYPHEN 
	: '-' 
	;
	
FRET_NUM
	: [0-9]+
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
    