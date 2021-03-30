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
	: drumType lineItems SPACE* NEWLINE?          
	;

lineItems
	:(HYPHEN 
	| cymbal
	| drum
	| BAR)+	BAR		
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
	| 'o' // only hi-hat can be o - open hi-hat
	| '#'
	| 'c'
	| 'b'	
	; 
	
DRUMS
	: 'o'
	| 'O'
	| 'g'
	| 'f'
	| 'd'
	;

	
/* Tokens */

TYPE         
	:('Bd' // base drum 1
	| 'BD' // base drum 2
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
	| 'Cc' // crash cymbal 1
	| 'HT' // high tom
	| 'Rd' // ride cymbal 1
	| 'Ch'   // chinese cymbal
	| 'RB' // ride bell
	| 'T'  // tambourine
	| 'SC' // splash cymbal
	| 'CB' // cowbell
	| 'CC' // crash cymbal 2
	| 'RD' // ride cymbal 2
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
    