tree grammar FunctionComplier;

options{
	tokenVocab=Function;  
	ASTLabelType=CommonTree;
	output = template;	
}

@header {
	import java.util.Map;
	import java.util.HashMap;
}

@members{
	private int seqNum = 0;
	private int tempNum = 0;
	private Map<String, String> paramRenameMap ;
	
	private List<Instruction> insList;	
	private void addInstruction(Instruction inc){
		insList.add(inc);
	}
   
    private Map<String, List<Instruction>> functionMap = new HashMap<String, List<Instruction>>();
    public Map<String, List<Instruction>> getFunctionMap(){
    	return this.functionMap;
    }    
    
    private int level = 0;
}

prog: funDecl+; 

funDecl scope {String funcName;}
	:  {seqNum = 0; tempNum = 0; paramRenameMap = new HashMap<String, String>();}
	   ^(FUNCTION fn=ID {$funDecl::funcName=fn.getText(); List<Instruction> insList = new ArrayList<Instruction>(); functionMap.put(fn.getText(), insList); this.insList=insList;}
	   parmeters block) 
;

parmeters: 
   | ^(PARAMATERS paramDeclList)
;

paramDeclList
	:	paramDecl*
;

paramDecl
	:	^(PARAM p=ID)	{String temp="t"+ tempNum++; paramRenameMap.put(p.getText(),p.getText());}	
;

block	
	:	^(BLOCK ^(STMTS stmtList))
;

stmtList :	stmt*
;

stmt :  
          ^(RETURN expr) {if("main".equals($funDecl::funcName)){addInstruction(new Instruction(seqNum++, "halt"));}else {addInstruction(new Instruction(seqNum++, "ret"));}}
		| ^(ACTION ACT) { addInstruction(new Instruction(seqNum++, "act"));}
		| ifStmt
		| ifelseStmt
		| whileStmt
		| block
		| expr
; 


whileStmt
:
   {Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginWhile")); int whileTestLine = seqNum;} 
	^(WHILE expr {instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);} 
	  stmt) {instruct2 =new Instruction(seqNum++, "jmp", whileTestLine+"");insList.add(instruct2);}{int seq = seqNum;instruct1.oprand1=seq+"";} 
	  {insList.add(new Instruction(seqNum++, "rem", "endWhile"));} 
;
ifelseStmt	
:	
    {Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginIfelse"));} 
	  ^(IF expr {instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);} 
	    s1=stmt {instruct2 =new Instruction(seqNum++, "jmp");insList.add(instruct2);}{int seq = seqNum;instruct1.oprand1=seq+"";}  
	    s2=stmt){int seq2=seqNum;instruct2.oprand1=seq2+"";insList.add(new Instruction(seqNum++, "rem", "endIfelse"));}
	
; 

ifStmt	
:
	{Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginIf"));} 
	^(UNLESS expr {instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);} 
	stmt{instruct2 =new Instruction(seqNum++, "jmp");insList.add(instruct2);}{int seq = seqNum;instruct1.oprand1=seq+"";}  
	){int seq2=seqNum;instruct2.oprand1=seq2+"";insList.add(new Instruction(seqNum++, "rem", "endIf"));}
;
	
expr  
: 
      {level +=1;}
      ^(ASSIGN v=ID expr) {if(level > 1){addInstruction(new Instruction(seqNum++, "dup"));} String var =paramRenameMap.get(v.getText()); if(null==var){String temp="t"+ tempNum++; paramRenameMap.put(v.getText(),v.getText());var=v.getText();} addInstruction(new Instruction(seqNum++, "asn", var));} 	{level -=1;}				    
	| ^(t=('||') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "or"));}
    | ^(t=('&&') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "and"));}
    | ^(t=('==') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "eq"));}
    | ^(t=('!=') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "neq"));}
    | ^(t=('>') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "gt"));}
    | ^(t=('>=') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "ge"));}
    | ^(t=('<') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "lt"));} 
    | ^(t=('<=') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "le"));}     
    | ^(t=('+') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "add"));}     
    | ^(t=('-') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "sub"));}    
    | ^(t=('*') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "mul"));}     
    | ^(t=('/') e1=expr e2=expr) {addInstruction(new Instruction(seqNum++, "div"));}               
	| ^(NOT e=expr) {addInstruction(new Instruction(seqNum++, "not"));}
	| ^(NEGATE e=expr) {addInstruction(new Instruction(seqNum++, "ldc", "-1"));addInstruction(new Instruction(seqNum++, "mul"));}			
	| atom 
;

atom 
:
	   ^(NUM n=NUMBER)   {addInstruction(new Instruction(seqNum++, "ldc", n.getText())); } 
	|  ^(CALL v=ID el=exprList)    {addInstruction(new Instruction(seqNum++, "call", v.getText(), $el.paramNum.toString())); } 
	|  ^(CALLV v=ID )    {addInstruction(new Instruction(seqNum++, "callv", v.getText())); } 
	|  ^(VAR v=ID)	{String var =paramRenameMap.get(v.getText()); if(null==var){String temp="t"+ tempNum++; paramRenameMap.put(v.getText(),v.getText());var=v.getText();} addInstruction(new Instruction(seqNum++, "ldv", var));}
;

exprList returns[Integer paramNum]
@init {
$paramNum =0;
}
:	(vars+=expr)+ {$paramNum =$vars.size();}
;