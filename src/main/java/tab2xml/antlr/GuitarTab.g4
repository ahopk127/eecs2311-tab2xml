grammar GuitarTab;

@header {
	package tab2xml.antlr; 
}

sheet
	: (.*? staff .*?)* EOF			
	;
	
staff								
	: NEWLINE? string+ NEWLINE?
	;

string
	: tune stringItems SPACE* NEWLINE
	;

tune
	: NOTE? BAR
	;

stringItems
	:(HYPHEN+ 
	|(fret
	| harmonic
	| hampullchain
	| hammeron
	| pulloff
	| slide)* BAR)+				
	;	

slide
	: fret ('s' fret)+				
	;

pulloff
	: fret ('p' fret)+ 				
	;

hammeron 
	: fret ('h' fret)+				
	;

hampullchain
	: fret ('h' | 'p')  (fret  ('h' | 'p') fret)+		# HammerPull
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
	: '-' -> channel(HIDDEN)
	;
	
FRET_NUM
	: [1-2]?[0-9]
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
