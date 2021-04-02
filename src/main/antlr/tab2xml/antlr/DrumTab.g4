grammar DrumTab;

@header{
	package tab2xml.antlr; 
}

sheet
	: staff* EOF			
	;
	
staff								
	: NEWLINE* line+ NEWLINE*
	;
	
line
	: SPACE* drumType SPACE* lineItems SPACE* NEWLINE?          
	;

lineItems
	:(cymbal
	| drum
	| BAR
	| HYPHEN )+	BAR		
	;	
	
drumType
	: TYPE? BAR          
	;

cymbal: CYMBALS;

drum: DRUMS;

/* Tokens */
	
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

	
/* Tokens */

TYPE         
	:('BD' // base drum 1
	| 'Bd' // base drum 2
	| 'SS' // side stick
	| 'SD' // snare drum 
	| 'ES' // electric  snare
	| 'T1' // low floor tom
	| 'CH' // closed  hi-hat
	| 'T2' // high floor tom
	| 'PH' // pedal hi-hat
	| 'LT' // low tom
	| 'HH' // open hi-hat 
	| 'LM' // low-mid tom
	| 'MT' // hi-mid tom
	| 'CC' // crash cymbal 1
	| 'HT' // high tom
	| 'RD' // ride cymbal 1
	| 'Ch'   // chinese cymbal
	| 'RB' // ride bell
	| 'TA'  // tambourine
	| 'SC' // splash cymbal
	| 'CB' // cowbell
	| 'Cc' // crash cymbal 2
	| 'Rd' // ride cymbal 2
	| 'HC' // open high conga
	| 'LC' // low conga
	)
	;
	
BAR
	: '|'
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
    