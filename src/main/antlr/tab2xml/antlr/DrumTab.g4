grammar DrumTab;

@header{
	package tab2xml.antlr; 
}

sheet
	: staff* EOF			
	;
	
staff								
	: NEWLINE* (cymbalLine | drumLine)+ NEWLINE*
	;
	
drumLine
	: SPACE* drumType SPACE* drumActions SPACE* NEWLINE?
	;

cymbalLine
	: SPACE* cymbalType SPACE* cymbalActions SPACE* NEWLINE?
	;
	
drumActions
	:(drum
	| BAR
	| HYPHEN )*	BAR		
	;	

cymbalActions
	:(cymbal
	| BAR
	| HYPHEN )*	BAR		
	;	

drumType: DRUMTYPE SPACE? SPACE? SPACE? BAR;
cymbalType: CYMBALTYPE SPACE? SPACE? SPACE? BAR;

drum: DRUMS;
cymbal: CYMBALS;

/* Tokens */
DRUMS
	: 'o'
	| 'O'
	| 'g'
	| 'f'
	| 'd'
	| 'b'
	| 'B'
	| '@'
	;
	
CYMBALS
	: 'x'	
	| 'X'
	| 'o'
	| '#'
	| 's'
	| 'c'
	| 'b'
	| 'p'	
	; 
	
/* Tokens */
DRUMTYPE
	:('BD' // base drum 1
	| 'Bd' // base drum 2
	| 'SS' // side stick
	| 'SD' // snare drum 
	| 'ES' // electric  snare
	| 'FT' // low floor tom
	| 'Ft' // high floor tom
	| 'LT' // low tom
	| 'LM' // low-mid tom
	| 'MT' // hi-mid tom
	| 'HT' // high tom
	| 'TA'  // tambourine
	| 'CB' // cowbell
	)
	;
CYMBALTYPE         
	:('HH' // closed hi-hat *
	| 'PH' // pedal hi-hat *
	| 'OH' // open hi-hat *
	| 'CC' // crash cymbal 1 *
	| 'RD' // ride cymbal 1 *
	| 'Ch'   // chinese cymbal *
	| 'RB' // ride bell *
	| 'SC' // splash cymbal *
	| 'Cc' // crash cymbal 2 *
	| 'Rd' // ride cymbal 2 *
	)
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
    