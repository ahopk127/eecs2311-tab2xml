grammar DrumTab;

@header{
	package tab2xml.antlr; 
	
}

sheet
	: (.*? staff .*?)* EOF			
	;
	
staff								
	: NEWLINE? line+ NEWLINE?
	;
	
line
	: drumType lineItems SPACE* NEWLINE          
	;

lineItems
	:(HYPHEN 
	| strike
	| accent
	| ghost
	| roll
	| choke
	| flam
	| BAR)+	BAR		
	;	
drumType
	: TYPE? BAR           //Question for amir. what are the question marks?
	;
	
	
strike
	: STRIKES		
	;

accent 
	: ACCENTS			
	;

ghost
	: 'g'			
	;

roll
	: 'd'				
	;
	
choke
	: '#'
	;
flam
	: 'f'
	;
/* Tokens */

TYPE         
	: ('CC' | 'SD' | 'HH' | 'HT' | 'MT' | 'BD' | 't' | 'T' | 'FT' | 'F' | 'B' | 'C' | 'R' | 'H')?    // question for amir - is this right? LMAO
	;
	
STRIKES
	: ('x' | 'o')?
	;
	
ACCENTS
	: ('X' | 'O')?
	;
	
BAR
	: '|'
	;
	
HYPHEN 
	: '-' -> channel(HIDDEN)
	;
	
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
	: SPACE* ('\r\n' | '\n')
	;
	
MULTI_COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
    ;