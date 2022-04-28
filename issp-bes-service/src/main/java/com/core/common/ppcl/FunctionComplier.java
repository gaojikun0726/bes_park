package com.core.common.ppcl;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

import java.util.*;
public class FunctionComplier extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACT", "ACTION", "ASSIGN", "BLOCK", "CALL", "CALLV", "COMMENT", "EXPR", "FUNCTION", "ID", "IF", "LINE_COMMENT", "NEGATE", "NEWLINE", "NOP", "NOT", "NUM", "NUMBER", "PARAM", "PARAMATERS", "RETURN", "STMTS", "UNLESS", "VAR", "VOIDNULL", "WHILE", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "';'", "'<'", "'<='", "'='", "'=='", "'>'", "'>='", "'DO'", "'ELSE'", "'for'", "'{'", "'||'", "'}'"
    };
    public static final int T__50=50;
    public static final int NEGATE=16;
    public static final int VAR=27;
    public static final int ID=13;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int IF=14;
    public static final int T__53=53;
    public static final int NUMBER=21;
    public static final int NEWLINE=17;
    public static final int BLOCK=7;
    public static final int FUNCTION=12;
    public static final int NOP=18;
    public static final int NOT=19;
    public static final int LINE_COMMENT=15;
    public static final int STMTS=25;
    public static final int VOIDNULL=28;
    public static final int CALL=8;
    public static final int UNLESS=26;
    public static final int ASSIGN=6;
    public static final int COMMENT=10;
    public static final int RETURN=24;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int EXPR=11;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int WS=30;
    public static final int EOF=-1;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int NUM=20;
    public static final int PARAM=22;
    public static final int PARAMATERS=23;
    public static final int ACT=4;
    public static final int T__48=48;
    public static final int ACTION=5;
    public static final int T__49=49;
    public static final int CALLV=9;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int WHILE=29;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public FunctionComplier(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public FunctionComplier(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected StringTemplateGroup templateLib =
      new StringTemplateGroup("FunctionComplierTemplates", AngleBracketTemplateLexer.class);

    public void setTemplateLib(StringTemplateGroup templateLib) {
      this.templateLib = templateLib;
    }
    public StringTemplateGroup getTemplateLib() {
      return templateLib;
    }
    /** allows convenient multi-value initialization:
     *  "new STAttrMap().put(...).put(...)"
     */
    public static class STAttrMap extends HashMap {
      public STAttrMap put(String attrName, Object value) {
        super.put(attrName, value);
        return this;
      }
      public STAttrMap put(String attrName, int value) {
        super.put(attrName, new Integer(value));
        return this;
      }
    }

    public String[] getTokenNames() { return FunctionComplier.tokenNames; }
    public String getGrammarFileName() { return "FunctionComplier.g"; }


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


    public static class prog_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "prog"
    // FunctionComplier.g:32:1: prog : ( funDecl )+ ;
    public final FunctionComplier.prog_return prog() throws RecognitionException {
        FunctionComplier.prog_return retval = new FunctionComplier.prog_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:32:5: ( ( funDecl )+ )
            // FunctionComplier.g:32:7: ( funDecl )+
            {
            // FunctionComplier.g:32:7: ( funDecl )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==FUNCTION) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // FunctionComplier.g:32:7: funDecl
            	    {
            	    pushFollow(FOLLOW_funDecl_in_prog50);
            	    funDecl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "prog"

    protected static class funDecl_scope {
        String funcName;
    }
    protected Stack funDecl_stack = new Stack();

    public static class funDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "funDecl"
    // FunctionComplier.g:34:1: funDecl : ^( FUNCTION fn= ID parmeters block ) ;
    public final FunctionComplier.funDecl_return funDecl() throws RecognitionException {
        funDecl_stack.push(new funDecl_scope());
        FunctionComplier.funDecl_return retval = new FunctionComplier.funDecl_return();
        retval.start = input.LT(1);

        CommonTree fn=null;

        try {
            // FunctionComplier.g:35:2: ( ^( FUNCTION fn= ID parmeters block ) )
            // FunctionComplier.g:35:5: ^( FUNCTION fn= ID parmeters block )
            {
            seqNum = 0; tempNum = 0; paramRenameMap = new HashMap<String, String>();
            match(input,FUNCTION,FOLLOW_FUNCTION_in_funDecl73); 

            match(input, Token.DOWN, null); 
            fn=(CommonTree)match(input,ID,FOLLOW_ID_in_funDecl77); 
            ((funDecl_scope)funDecl_stack.peek()).funcName =fn.getText(); List<Instruction> insList = new ArrayList<Instruction>(); functionMap.put(fn.getText(), insList); this.insList=insList;
            pushFollow(FOLLOW_parmeters_in_funDecl85);
            parmeters();

            state._fsp--;

            pushFollow(FOLLOW_block_in_funDecl87);
            block();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            funDecl_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "funDecl"

    public static class parmeters_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "parmeters"
    // FunctionComplier.g:40:1: parmeters : ( | ^( PARAMATERS paramDeclList ) );
    public final FunctionComplier.parmeters_return parmeters() throws RecognitionException {
        FunctionComplier.parmeters_return retval = new FunctionComplier.parmeters_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:40:10: ( | ^( PARAMATERS paramDeclList ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==BLOCK) ) {
                alt2=1;
            }
            else if ( (LA2_0==PARAMATERS) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // FunctionComplier.g:41:4: 
                    {
                    }
                    break;
                case 2 :
                    // FunctionComplier.g:41:6: ^( PARAMATERS paramDeclList )
                    {
                    match(input,PARAMATERS,FOLLOW_PARAMATERS_in_parmeters104); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        pushFollow(FOLLOW_paramDeclList_in_parmeters106);
                        paramDeclList();

                        state._fsp--;


                        match(input, Token.UP, null); 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parmeters"

    public static class paramDeclList_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "paramDeclList"
    // FunctionComplier.g:44:1: paramDeclList : ( paramDecl )* ;
    public final FunctionComplier.paramDeclList_return paramDeclList() throws RecognitionException {
        FunctionComplier.paramDeclList_return retval = new FunctionComplier.paramDeclList_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:45:2: ( ( paramDecl )* )
            // FunctionComplier.g:45:4: ( paramDecl )*
            {
            // FunctionComplier.g:45:4: ( paramDecl )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==PARAM) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // FunctionComplier.g:45:4: paramDecl
            	    {
            	    pushFollow(FOLLOW_paramDecl_in_paramDeclList117);
            	    paramDecl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "paramDeclList"

    public static class paramDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "paramDecl"
    // FunctionComplier.g:48:1: paramDecl : ^( PARAM p= ID ) ;
    public final FunctionComplier.paramDecl_return paramDecl() throws RecognitionException {
        FunctionComplier.paramDecl_return retval = new FunctionComplier.paramDecl_return();
        retval.start = input.LT(1);

        CommonTree p=null;

        try {
            // FunctionComplier.g:49:2: ( ^( PARAM p= ID ) )
            // FunctionComplier.g:49:4: ^( PARAM p= ID )
            {
            match(input,PARAM,FOLLOW_PARAM_in_paramDecl129); 

            match(input, Token.DOWN, null); 
            p=(CommonTree)match(input,ID,FOLLOW_ID_in_paramDecl133); 

            match(input, Token.UP, null); 
            String temp="t"+ tempNum++; paramRenameMap.put(p.getText(),p.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "paramDecl"

    public static class block_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "block"
    // FunctionComplier.g:52:1: block : ^( BLOCK ^( STMTS stmtList ) ) ;
    public final FunctionComplier.block_return block() throws RecognitionException {
        FunctionComplier.block_return retval = new FunctionComplier.block_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:53:2: ( ^( BLOCK ^( STMTS stmtList ) ) )
            // FunctionComplier.g:53:4: ^( BLOCK ^( STMTS stmtList ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block149); 

            match(input, Token.DOWN, null); 
            match(input,STMTS,FOLLOW_STMTS_in_block152); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_stmtList_in_block154);
                stmtList();

                state._fsp--;


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class stmtList_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "stmtList"
    // FunctionComplier.g:56:1: stmtList : ( stmt )* ;
    public final FunctionComplier.stmtList_return stmtList() throws RecognitionException {
        FunctionComplier.stmtList_return retval = new FunctionComplier.stmtList_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:56:10: ( ( stmt )* )
            // FunctionComplier.g:56:12: ( stmt )*
            {
            // FunctionComplier.g:56:12: ( stmt )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=ACTION && LA4_0<=CALLV)||LA4_0==IF||LA4_0==NEGATE||(LA4_0>=NOT && LA4_0<=NUM)||LA4_0==RETURN||(LA4_0>=UNLESS && LA4_0<=VAR)||LA4_0==WHILE||(LA4_0>=32 && LA4_0<=33)||(LA4_0>=36 && LA4_0<=37)||(LA4_0>=39 && LA4_0<=40)||(LA4_0>=42 && LA4_0<=43)||(LA4_0>=45 && LA4_0<=47)||LA4_0==52) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // FunctionComplier.g:56:12: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_stmtList165);
            	    stmt();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stmtList"

    public static class stmt_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "stmt"
    // FunctionComplier.g:59:1: stmt : ( ^( RETURN expr ) | ^( ACTION ACT ) | ifStmt | ifelseStmt | whileStmt | block | expr );
    public final FunctionComplier.stmt_return stmt() throws RecognitionException {
        FunctionComplier.stmt_return retval = new FunctionComplier.stmt_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:59:6: ( ^( RETURN expr ) | ^( ACTION ACT ) | ifStmt | ifelseStmt | whileStmt | block | expr )
            int alt5=7;
            switch ( input.LA(1) ) {
            case RETURN:
                {
                alt5=1;
                }
                break;
            case ACTION:
                {
                alt5=2;
                }
                break;
            case UNLESS:
                {
                alt5=3;
                }
                break;
            case IF:
                {
                alt5=4;
                }
                break;
            case WHILE:
                {
                alt5=5;
                }
                break;
            case BLOCK:
                {
                alt5=6;
                }
                break;
            case ASSIGN:
            case CALL:
            case CALLV:
            case NEGATE:
            case NOT:
            case NUM:
            case VAR:
            case 32:
            case 33:
            case 36:
            case 37:
            case 39:
            case 40:
            case 42:
            case 43:
            case 45:
            case 46:
            case 47:
            case 52:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // FunctionComplier.g:60:11: ^( RETURN expr )
                    {
                    match(input,RETURN,FOLLOW_RETURN_in_stmt188); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stmt190);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    if("main".equals(((funDecl_scope)funDecl_stack.peek()).funcName)){addInstruction(new Instruction(seqNum++, "halt"));}else {addInstruction(new Instruction(seqNum++, "ret"));}

                    }
                    break;
                case 2 :
                    // FunctionComplier.g:61:5: ^( ACTION ACT )
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_stmt200); 

                    match(input, Token.DOWN, null); 
                    match(input,ACT,FOLLOW_ACT_in_stmt202); 

                    match(input, Token.UP, null); 
                     addInstruction(new Instruction(seqNum++, "act"));

                    }
                    break;
                case 3 :
                    // FunctionComplier.g:62:5: ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_stmt211);
                    ifStmt();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // FunctionComplier.g:63:5: ifelseStmt
                    {
                    pushFollow(FOLLOW_ifelseStmt_in_stmt217);
                    ifelseStmt();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // FunctionComplier.g:64:5: whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_stmt223);
                    whileStmt();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // FunctionComplier.g:65:5: block
                    {
                    pushFollow(FOLLOW_block_in_stmt229);
                    block();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // FunctionComplier.g:66:5: expr
                    {
                    pushFollow(FOLLOW_expr_in_stmt235);
                    expr();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class whileStmt_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "whileStmt"
    // FunctionComplier.g:70:1: whileStmt : ^( WHILE expr stmt ) ;
    public final FunctionComplier.whileStmt_return whileStmt() throws RecognitionException {
        FunctionComplier.whileStmt_return retval = new FunctionComplier.whileStmt_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:71:1: ( ^( WHILE expr stmt ) )
            // FunctionComplier.g:72:4: ^( WHILE expr stmt )
            {
            Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginWhile")); int whileTestLine = seqNum;
            match(input,WHILE,FOLLOW_WHILE_in_whileStmt254); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_whileStmt256);
            expr();

            state._fsp--;

            instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);
            pushFollow(FOLLOW_stmt_in_whileStmt264);
            stmt();

            state._fsp--;


            match(input, Token.UP, null); 
            instruct2 =new Instruction(seqNum++, "jmp", whileTestLine+"");insList.add(instruct2);
            int seq = seqNum;instruct1.oprand1=seq+"";
            insList.add(new Instruction(seqNum++, "rem", "endWhile"));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "whileStmt"

    public static class ifelseStmt_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "ifelseStmt"
    // FunctionComplier.g:77:1: ifelseStmt : ^( IF expr s1= stmt s2= stmt ) ;
    public final FunctionComplier.ifelseStmt_return ifelseStmt() throws RecognitionException {
        FunctionComplier.ifelseStmt_return retval = new FunctionComplier.ifelseStmt_return();
        retval.start = input.LT(1);

        FunctionComplier.stmt_return s1 = null;

        FunctionComplier.stmt_return s2 = null;


        try {
            // FunctionComplier.g:78:1: ( ^( IF expr s1= stmt s2= stmt ) )
            // FunctionComplier.g:79:5: ^( IF expr s1= stmt s2= stmt )
            {
            Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginIfelse"));
            match(input,IF,FOLLOW_IF_in_ifelseStmt296); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_ifelseStmt298);
            expr();

            state._fsp--;

            instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);
            pushFollow(FOLLOW_stmt_in_ifelseStmt310);
            s1=stmt();

            state._fsp--;

            instruct2 =new Instruction(seqNum++, "jmp");insList.add(instruct2);
            int seq = seqNum;instruct1.oprand1=seq+"";
            pushFollow(FOLLOW_stmt_in_ifelseStmt324);
            s2=stmt();

            state._fsp--;


            match(input, Token.UP, null); 
            int seq2=seqNum;instruct2.oprand1=seq2+"";insList.add(new Instruction(seqNum++, "rem", "endIfelse"));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ifelseStmt"

    public static class ifStmt_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "ifStmt"
    // FunctionComplier.g:86:1: ifStmt : ^( UNLESS expr stmt ) ;
    public final FunctionComplier.ifStmt_return ifStmt() throws RecognitionException {
        FunctionComplier.ifStmt_return retval = new FunctionComplier.ifStmt_return();
        retval.start = input.LT(1);

        try {
            // FunctionComplier.g:87:1: ( ^( UNLESS expr stmt ) )
            // FunctionComplier.g:88:2: ^( UNLESS expr stmt )
            {
            Instruction instruct1 = null, instruct2 = null;insList.add(new Instruction(seqNum++, "rem",  "beginIf"));
            match(input,UNLESS,FOLLOW_UNLESS_in_ifStmt345); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_ifStmt347);
            expr();

            state._fsp--;

            instruct1 =new Instruction(seqNum++, "jz"); insList.add(instruct1);
            pushFollow(FOLLOW_stmt_in_ifStmt353);
            stmt();

            state._fsp--;

            instruct2 =new Instruction(seqNum++, "jmp");insList.add(instruct2);
            int seq = seqNum;instruct1.oprand1=seq+"";

            match(input, Token.UP, null); 
            int seq2=seqNum;instruct2.oprand1=seq2+"";insList.add(new Instruction(seqNum++, "rem", "endIf"));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ifStmt"

    public static class expr_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "expr"
    // FunctionComplier.g:94:1: expr : ( ^( ASSIGN v= ID expr ) | ^(t= ( '||' ) e1= expr e2= expr ) | ^(t= ( '&&' ) e1= expr e2= expr ) | ^(t= ( '==' ) e1= expr e2= expr ) | ^(t= ( '!=' ) e1= expr e2= expr ) | ^(t= ( '>' ) e1= expr e2= expr ) | ^(t= ( '>=' ) e1= expr e2= expr ) | ^(t= ( '<' ) e1= expr e2= expr ) | ^(t= ( '<=' ) e1= expr e2= expr ) | ^(t= ( '+' ) e1= expr e2= expr ) | ^(t= ( '-' ) e1= expr e2= expr ) | ^(t= ( '*' ) e1= expr e2= expr ) | ^(t= ( '/' ) e1= expr e2= expr ) | ^( NOT e= expr ) | ^( NEGATE e= expr ) | atom );
    public final FunctionComplier.expr_return expr() throws RecognitionException {
        FunctionComplier.expr_return retval = new FunctionComplier.expr_return();
        retval.start = input.LT(1);

        CommonTree v=null;
        CommonTree t=null;
        FunctionComplier.expr_return e1 = null;

        FunctionComplier.expr_return e2 = null;

        FunctionComplier.expr_return e = null;


        try {
            // FunctionComplier.g:95:1: ( ^( ASSIGN v= ID expr ) | ^(t= ( '||' ) e1= expr e2= expr ) | ^(t= ( '&&' ) e1= expr e2= expr ) | ^(t= ( '==' ) e1= expr e2= expr ) | ^(t= ( '!=' ) e1= expr e2= expr ) | ^(t= ( '>' ) e1= expr e2= expr ) | ^(t= ( '>=' ) e1= expr e2= expr ) | ^(t= ( '<' ) e1= expr e2= expr ) | ^(t= ( '<=' ) e1= expr e2= expr ) | ^(t= ( '+' ) e1= expr e2= expr ) | ^(t= ( '-' ) e1= expr e2= expr ) | ^(t= ( '*' ) e1= expr e2= expr ) | ^(t= ( '/' ) e1= expr e2= expr ) | ^( NOT e= expr ) | ^( NEGATE e= expr ) | atom )
            int alt6=16;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt6=1;
                }
                break;
            case 52:
                {
                alt6=2;
                }
                break;
            case 33:
                {
                alt6=3;
                }
                break;
            case 45:
                {
                alt6=4;
                }
                break;
            case 32:
                {
                alt6=5;
                }
                break;
            case 46:
                {
                alt6=6;
                }
                break;
            case 47:
                {
                alt6=7;
                }
                break;
            case 42:
                {
                alt6=8;
                }
                break;
            case 43:
                {
                alt6=9;
                }
                break;
            case 37:
                {
                alt6=10;
                }
                break;
            case 39:
                {
                alt6=11;
                }
                break;
            case 36:
                {
                alt6=12;
                }
                break;
            case 40:
                {
                alt6=13;
                }
                break;
            case NOT:
                {
                alt6=14;
                }
                break;
            case NEGATE:
                {
                alt6=15;
                }
                break;
            case CALL:
            case CALLV:
            case NUM:
            case VAR:
                {
                alt6=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // FunctionComplier.g:96:7: ^( ASSIGN v= ID expr )
                    {
                    level +=1;
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_expr389); 

                    match(input, Token.DOWN, null); 
                    v=(CommonTree)match(input,ID,FOLLOW_ID_in_expr393); 
                    pushFollow(FOLLOW_expr_in_expr395);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    if(level > 1){addInstruction(new Instruction(seqNum++, "dup"));} String var =paramRenameMap.get(v.getText()); if(null==var){String temp="t"+ tempNum++; paramRenameMap.put(v.getText(),v.getText());var=v.getText();} addInstruction(new Instruction(seqNum++, "asn", var));
                    level -=1;

                    }
                    break;
                case 2 :
                    // FunctionComplier.g:98:4: ^(t= ( '||' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:98:8: ( '||' )
                    // FunctionComplier.g:98:9: '||'
                    {
                    match(input,52,FOLLOW_52_in_expr418); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr423);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr427);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "or"));

                    }
                    break;
                case 3 :
                    // FunctionComplier.g:99:7: ^(t= ( '&&' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:99:11: ( '&&' )
                    // FunctionComplier.g:99:12: '&&'
                    {
                    match(input,33,FOLLOW_33_in_expr442); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr447);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr451);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "and"));

                    }
                    break;
                case 4 :
                    // FunctionComplier.g:100:7: ^(t= ( '==' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:100:11: ( '==' )
                    // FunctionComplier.g:100:12: '=='
                    {
                    match(input,45,FOLLOW_45_in_expr466); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr471);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr475);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "eq"));

                    }
                    break;
                case 5 :
                    // FunctionComplier.g:101:7: ^(t= ( '!=' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:101:11: ( '!=' )
                    // FunctionComplier.g:101:12: '!='
                    {
                    match(input,32,FOLLOW_32_in_expr490); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr495);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr499);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "neq"));

                    }
                    break;
                case 6 :
                    // FunctionComplier.g:102:7: ^(t= ( '>' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:102:11: ( '>' )
                    // FunctionComplier.g:102:12: '>'
                    {
                    match(input,46,FOLLOW_46_in_expr514); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr519);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr523);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "gt"));

                    }
                    break;
                case 7 :
                    // FunctionComplier.g:103:7: ^(t= ( '>=' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:103:11: ( '>=' )
                    // FunctionComplier.g:103:12: '>='
                    {
                    match(input,47,FOLLOW_47_in_expr538); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr543);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr547);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "ge"));

                    }
                    break;
                case 8 :
                    // FunctionComplier.g:104:7: ^(t= ( '<' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:104:11: ( '<' )
                    // FunctionComplier.g:104:12: '<'
                    {
                    match(input,42,FOLLOW_42_in_expr562); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr567);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr571);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "lt"));

                    }
                    break;
                case 9 :
                    // FunctionComplier.g:105:7: ^(t= ( '<=' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:105:11: ( '<=' )
                    // FunctionComplier.g:105:12: '<='
                    {
                    match(input,43,FOLLOW_43_in_expr587); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr592);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr596);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "le"));

                    }
                    break;
                case 10 :
                    // FunctionComplier.g:106:7: ^(t= ( '+' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:106:11: ( '+' )
                    // FunctionComplier.g:106:12: '+'
                    {
                    match(input,37,FOLLOW_37_in_expr616); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr621);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr625);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "add"));

                    }
                    break;
                case 11 :
                    // FunctionComplier.g:107:7: ^(t= ( '-' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:107:11: ( '-' )
                    // FunctionComplier.g:107:12: '-'
                    {
                    match(input,39,FOLLOW_39_in_expr645); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr650);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr654);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "sub"));

                    }
                    break;
                case 12 :
                    // FunctionComplier.g:108:7: ^(t= ( '*' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:108:11: ( '*' )
                    // FunctionComplier.g:108:12: '*'
                    {
                    match(input,36,FOLLOW_36_in_expr673); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr678);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr682);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "mul"));

                    }
                    break;
                case 13 :
                    // FunctionComplier.g:109:7: ^(t= ( '/' ) e1= expr e2= expr )
                    {
                    // FunctionComplier.g:109:11: ( '/' )
                    // FunctionComplier.g:109:12: '/'
                    {
                    match(input,40,FOLLOW_40_in_expr702); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr707);
                    e1=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr711);
                    e2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "div"));

                    }
                    break;
                case 14 :
                    // FunctionComplier.g:110:4: ^( NOT e= expr )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expr735); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr739);
                    e=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "not"));

                    }
                    break;
                case 15 :
                    // FunctionComplier.g:111:4: ^( NEGATE e= expr )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expr748); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr752);
                    e=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "ldc", "-1"));addInstruction(new Instruction(seqNum++, "mul"));

                    }
                    break;
                case 16 :
                    // FunctionComplier.g:112:4: atom
                    {
                    pushFollow(FOLLOW_atom_in_expr763);
                    atom();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class atom_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "atom"
    // FunctionComplier.g:115:1: atom : ( ^( NUM n= NUMBER ) | ^( CALL v= ID el= exprList ) | ^( CALLV v= ID ) | ^( VAR v= ID ) );
    public final FunctionComplier.atom_return atom() throws RecognitionException {
        FunctionComplier.atom_return retval = new FunctionComplier.atom_return();
        retval.start = input.LT(1);

        CommonTree n=null;
        CommonTree v=null;
        FunctionComplier.exprList_return el = null;


        try {
            // FunctionComplier.g:116:1: ( ^( NUM n= NUMBER ) | ^( CALL v= ID el= exprList ) | ^( CALLV v= ID ) | ^( VAR v= ID ) )
            int alt7=4;
            switch ( input.LA(1) ) {
            case NUM:
                {
                alt7=1;
                }
                break;
            case CALL:
                {
                alt7=2;
                }
                break;
            case CALLV:
                {
                alt7=3;
                }
                break;
            case VAR:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // FunctionComplier.g:117:5: ^( NUM n= NUMBER )
                    {
                    match(input,NUM,FOLLOW_NUM_in_atom779); 

                    match(input, Token.DOWN, null); 
                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_atom783); 

                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "ldc", n.getText())); 

                    }
                    break;
                case 2 :
                    // FunctionComplier.g:118:5: ^( CALL v= ID el= exprList )
                    {
                    match(input,CALL,FOLLOW_CALL_in_atom796); 

                    match(input, Token.DOWN, null); 
                    v=(CommonTree)match(input,ID,FOLLOW_ID_in_atom800); 
                    pushFollow(FOLLOW_exprList_in_atom804);
                    el=exprList();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "call", v.getText(), (el!=null?el.paramNum:null).toString())); 

                    }
                    break;
                case 3 :
                    // FunctionComplier.g:119:5: ^( CALLV v= ID )
                    {
                    match(input,CALLV,FOLLOW_CALLV_in_atom818); 

                    match(input, Token.DOWN, null); 
                    v=(CommonTree)match(input,ID,FOLLOW_ID_in_atom822); 

                    match(input, Token.UP, null); 
                    addInstruction(new Instruction(seqNum++, "callv", v.getText())); 

                    }
                    break;
                case 4 :
                    // FunctionComplier.g:120:5: ^( VAR v= ID )
                    {
                    match(input,VAR,FOLLOW_VAR_in_atom837); 

                    match(input, Token.DOWN, null); 
                    v=(CommonTree)match(input,ID,FOLLOW_ID_in_atom841); 

                    match(input, Token.UP, null); 
                    String var =paramRenameMap.get(v.getText()); if(null==var){String temp="t"+ tempNum++; paramRenameMap.put(v.getText(),v.getText());var=v.getText();} addInstruction(new Instruction(seqNum++, "ldv", var));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class exprList_return extends TreeRuleReturnScope {
        public Integer paramNum;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "exprList"
    // FunctionComplier.g:123:1: exprList returns [Integer paramNum] : (vars+= expr )+ ;
    public final FunctionComplier.exprList_return exprList() throws RecognitionException {
        FunctionComplier.exprList_return retval = new FunctionComplier.exprList_return();
        retval.start = input.LT(1);

        List list_vars=null;
        RuleReturnScope vars = null;

        retval.paramNum =0;

        try {
            // FunctionComplier.g:127:1: ( (vars+= expr )+ )
            // FunctionComplier.g:127:3: (vars+= expr )+
            {
            // FunctionComplier.g:127:3: (vars+= expr )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ASSIGN||(LA8_0>=CALL && LA8_0<=CALLV)||LA8_0==NEGATE||(LA8_0>=NOT && LA8_0<=NUM)||LA8_0==VAR||(LA8_0>=32 && LA8_0<=33)||(LA8_0>=36 && LA8_0<=37)||(LA8_0>=39 && LA8_0<=40)||(LA8_0>=42 && LA8_0<=43)||(LA8_0>=45 && LA8_0<=47)||LA8_0==52) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // FunctionComplier.g:127:4: vars+= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_exprList864);
            	    vars=expr();

            	    state._fsp--;

            	    if (list_vars==null) list_vars=new ArrayList();
            	    list_vars.add(vars.getTemplate());


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            retval.paramNum =list_vars.size();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprList"

    // Delegated rules


 

    public static final BitSet FOLLOW_funDecl_in_prog50 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_FUNCTION_in_funDecl73 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_funDecl77 = new BitSet(new long[]{0x0000000000800080L});
    public static final BitSet FOLLOW_parmeters_in_funDecl85 = new BitSet(new long[]{0x0000000000800080L});
    public static final BitSet FOLLOW_block_in_funDecl87 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARAMATERS_in_parmeters104 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_paramDeclList_in_parmeters106 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_paramDecl_in_paramDeclList117 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_PARAM_in_paramDecl129 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_paramDecl133 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block149 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STMTS_in_block152 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stmtList_in_block154 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_stmt_in_stmtList165 = new BitSet(new long[]{0x0010EDB32D9943E2L});
    public static final BitSet FOLLOW_RETURN_in_stmt188 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stmt190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ACTION_in_stmt200 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACT_in_stmt202 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ifStmt_in_stmt211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifelseStmt_in_stmt217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_stmt223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_stmt229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_stmt235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_whileStmt254 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_whileStmt256 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_stmt_in_whileStmt264 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifelseStmt296 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_ifelseStmt298 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_stmt_in_ifelseStmt310 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_stmt_in_ifelseStmt324 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNLESS_in_ifStmt345 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_ifStmt347 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_stmt_in_ifStmt353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_expr389 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expr393 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr395 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_52_in_expr418 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr423 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr427 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_33_in_expr442 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr447 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr451 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_45_in_expr466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr471 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr475 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expr490 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr495 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr499 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_46_in_expr514 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr519 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr523 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_47_in_expr538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr543 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr547 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_42_in_expr562 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr567 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr571 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_expr587 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr592 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr596 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_37_in_expr616 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr621 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr625 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_expr645 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr650 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr654 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_expr673 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr678 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr682 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_40_in_expr702 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr707 = new BitSet(new long[]{0x0010EDB32D9943E8L});
    public static final BitSet FOLLOW_expr_in_expr711 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expr735 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr739 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expr748 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr752 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_atom_in_expr763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_atom779 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_atom783 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_atom796 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_atom800 = new BitSet(new long[]{0x0010EDB32D9943E0L});
    public static final BitSet FOLLOW_exprList_in_atom804 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALLV_in_atom818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_atom822 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VAR_in_atom837 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_atom841 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_in_exprList864 = new BitSet(new long[]{0x0010EDB32D9943E2L});

}