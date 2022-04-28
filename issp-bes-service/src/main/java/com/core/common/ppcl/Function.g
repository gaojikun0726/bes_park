grammar Function;

options{
	output = AST;
	ASTLabelType = CommonTree;
}
tokens{
	FUNCTION; PARAMATERS; PARAM; BLOCK; STMTS; ASSIGN; NOP; EXPR; NUM; VAR; NOT; NEGATE; CALL; ACTION;CALLV;
}

 

prog: funDecl+ 
; 

funDecl
	:	name=ID '(' paramDeclList ')' act=block -> ^(FUNCTION $name ^(PARAMATERS paramDeclList)? $act)
;

paramDeclList
	:	(paramDecl (',' paramDecl)*)? -> paramDecl*
;

paramDecl
	:	ID -> ^(PARAM ID)	
;

block	
	:	'{' stmts=stmtList '}' -> ^(BLOCK ^(STMTS $stmts))
;

stmtList:	stmt*
;

stmt
    :  
	  RETURN expr ';' -> ^(RETURN expr)
	| ACT ';' -> ^(ACTION ACT) 
	| ifStmt 		//if  语句
	| ifelseStmt
	| WHILE '(' expr ')' stmt -> ^(WHILE expr stmt)
	| 'DO' stmt WHILE '(' expr ')'';'  -> stmt ^(WHILE expr stmt)
	| 'for' '(' ini=expr ';' tes=expr ';' inc=expr ')' stmt -> $ini ^(WHILE $tes ^(BLOCK ^(STMTS stmt $inc)))
	| block
    | expr ';' -> expr
	
;


ifelseStmt	
options{
	backtrack=true;
}
:	
	  IF '(' expr ')' c1=stmt 'ELSE' c2=stmt -> ^(IF expr $c1 $c2)	
	 

; 


ifStmt	
options{
	backtrack=true;
}
:	
	 UNLESS '(' expr ')' stmt  -> ^(UNLESS expr stmt)
	 

; 

expr
options{
	backtrack=true;
}
: 
      ID '=' expr -> ^(ASSIGN ID expr)				
	| orExpr
;

orExpr	:	andExpr ('||'^ andExpr)*
;

andExpr	:	equalityExpr ('&&'^ equalityExpr)*
;

equalityExpr
	:	comparisonExpr (('=='|'!=')^ comparisonExpr)*
;

comparisonExpr 
	:	additiveExpr (('>'|'<'|'<='|'>=')^ additiveExpr)*  
;

additiveExpr 
	:	multExpr (('+' |'-' )^ multExpr )* 
;	

multExpr 
	:   notExpr (('*' |'/' )^ notExpr )*  
;

notExpr
	:	(op='!')? negationExpr 	-> {$op != null}? ^(NOT negationExpr)
						        -> negationExpr
;

negationExpr
	:	(op='-')? primary 	-> {$op != null}? ^(NEGATE primary)
					-> primary
;
primary 
	:   atom  			
	|  '(' expr ')' -> expr  
;

atom 
options{
	backtrack=true;
}	:
	  NUMBER  -> ^(NUM NUMBER)    
	| ID '(' exprList ')' -> ^(CALL ID exprList)   
    | ID '(' ')' -> ^(CALLV ID )	
	| ID -> ^(VAR ID)
	
;

exprList:	(expr (',' expr)*)  ->  expr+
;
VOIDNULL : 'void';
IF : 'IFE';
UNLESS: 'IF';
WHILE : 'WHILE' ;
RETURN : 'return' ;
ACT:'DONE';
ID : ('_'|'a'..'z' |'A'..'Z')('_'|'a'..'z' |'A'..'Z'|'0'..'9')*  ;
NUMBER : ('0'..'9')+ ('.' ('0'..'9')+)? ;
NEWLINE:'\r' ? '\n' {skip();} ;
WS : (' ' |'\t' |'\n' |'\r' )+ {skip();} ;
COMMENT :  '/*'  . *  '*/'  {skip();} ;
LINE_COMMENT : '//'  ~ ('\n' | '\r') *  '\r'? '\n'? {skip();} ;

