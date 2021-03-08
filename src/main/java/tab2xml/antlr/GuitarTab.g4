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
	| BAR)+	BAR		
	;	

hampullchain
	: fret ('h' | 'p')  fret  (('h' | 'p') fret)+		# HammerPull
	;


pulloff
	: fret 'p' fret				
	;

hammeron 
	: fret 'h' fret				
	;

slide
	: fret 's' fret				
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
	
HYPHEN 
	: '-' 
	;
	
FRET_NUM
	: [1-2]?[0-9]
	;
	
H: 'h';
P: 'p';
S: 's';

LSB
	: '['
	;
RSB
	: ']'
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
    