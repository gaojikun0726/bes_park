package com.core.common.ppcl;

import org.antlr.runtime.*;

@SuppressWarnings("all")
public class FunctionLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int ACT=4;
	public static final int ACTION=5;
	public static final int ASSIGN=6;
	public static final int BLOCK=7;
	public static final int CALL=8;
	public static final int CALLV=9;
	public static final int COMMENT=10;
	public static final int EXPR=11;
	public static final int FUNCTION=12;
	public static final int ID=13;
	public static final int IF=14;
	public static final int LINE_COMMENT=15;
	public static final int NEGATE=16;
	public static final int NEWLINE=17;
	public static final int NOP=18;
	public static final int NOT=19;
	public static final int NUM=20;
	public static final int NUMBER=21;
	public static final int PARAM=22;
	public static final int PARAMATERS=23;
	public static final int RETURN=24;
	public static final int STMTS=25;
	public static final int UNLESS=26;
	public static final int VAR=27;
	public static final int VOIDNULL=28;
	public static final int WHILE=29;
	public static final int WS=30;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public FunctionLexer() {} 
	public FunctionLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public FunctionLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "Function.g"; }

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:2:7: ( '!' )
			// Function.g:2:9: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:3:7: ( '!=' )
			// Function.g:3:9: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:4:7: ( '&&' )
			// Function.g:4:9: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:5:7: ( '(' )
			// Function.g:5:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:6:7: ( ')' )
			// Function.g:6:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:7:7: ( '*' )
			// Function.g:7:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:8:7: ( '+' )
			// Function.g:8:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:9:7: ( ',' )
			// Function.g:9:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:10:7: ( '-' )
			// Function.g:10:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:11:7: ( '/' )
			// Function.g:11:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:12:7: ( ';' )
			// Function.g:12:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:13:7: ( '<' )
			// Function.g:13:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:14:7: ( '<=' )
			// Function.g:14:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:15:7: ( '=' )
			// Function.g:15:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:16:7: ( '==' )
			// Function.g:16:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:17:7: ( '>' )
			// Function.g:17:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:18:7: ( '>=' )
			// Function.g:18:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:19:7: ( 'DO' )
			// Function.g:19:9: 'DO'
			{
			match("DO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:20:7: ( 'ELSE' )
			// Function.g:20:9: 'ELSE'
			{
			match("ELSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:21:7: ( 'for' )
			// Function.g:21:9: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:22:7: ( '{' )
			// Function.g:22:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:23:7: ( '||' )
			// Function.g:23:9: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:24:7: ( '}' )
			// Function.g:24:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__53"

	// $ANTLR start "VOIDNULL"
	public final void mVOIDNULL() throws RecognitionException {
		try {
			int _type = VOIDNULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:129:10: ( 'void' )
			// Function.g:129:12: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOIDNULL"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:130:4: ( 'IFE' )
			// Function.g:130:6: 'IFE'
			{
			match("IFE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "UNLESS"
	public final void mUNLESS() throws RecognitionException {
		try {
			int _type = UNLESS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:131:7: ( 'IF' )
			// Function.g:131:9: 'IF'
			{
			match("IF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNLESS"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:132:7: ( 'WHILE' )
			// Function.g:132:9: 'WHILE'
			{
			match("WHILE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:133:8: ( 'return' )
			// Function.g:133:10: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "ACT"
	public final void mACT() throws RecognitionException {
		try {
			int _type = ACT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:134:4: ( 'DONE' )
			// Function.g:134:5: 'DONE'
			{
			match("DONE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ACT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:135:4: ( ( '_' | 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// Function.g:135:6: ( '_' | 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// Function.g:135:30: ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// Function.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:136:8: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// Function.g:136:10: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// Function.g:136:10: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// Function.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			// Function.g:136:22: ( '.' ( '0' .. '9' )+ )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='.') ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// Function.g:136:23: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// Function.g:136:27: ( '0' .. '9' )+
					int cnt3=0;
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// Function.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt3 >= 1 ) break loop3;
							EarlyExitException eee = new EarlyExitException(3, input);
							throw eee;
						}
						cnt3++;
					}

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			int _type = NEWLINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:137:8: ( ( '\\r' )? '\\n' )
			// Function.g:137:9: ( '\\r' )? '\\n'
			{
			// Function.g:137:9: ( '\\r' )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='\r') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// Function.g:137:9: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:138:4: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// Function.g:138:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// Function.g:138:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '\t' && LA6_0 <= '\n')||LA6_0=='\r'||LA6_0==' ') ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// Function.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:139:9: ( '/*' ( . )* '*/' )
			// Function.g:139:12: '/*' ( . )* '*/'
			{
			match("/*"); 

			// Function.g:139:18: ( . )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0=='*') ) {
					int LA7_1 = input.LA(2);
					if ( (LA7_1=='/') ) {
						alt7=2;
					}
					else if ( ((LA7_1 >= '\u0000' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '\uFFFF')) ) {
						alt7=1;
					}

				}
				else if ( ((LA7_0 >= '\u0000' && LA7_0 <= ')')||(LA7_0 >= '+' && LA7_0 <= '\uFFFF')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// Function.g:139:18: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop7;
				}
			}

			match("*/"); 

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Function.g:140:14: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? ( '\\n' )? )
			// Function.g:140:16: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? ( '\\n' )?
			{
			match("//"); 

			// Function.g:140:22: (~ ( '\\n' | '\\r' ) )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '\uFFFF')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// Function.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop8;
				}
			}

			// Function.g:140:41: ( '\\r' )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='\r') ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// Function.g:140:41: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			// Function.g:140:47: ( '\\n' )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='\n') ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// Function.g:140:47: '\\n'
					{
					match('\n'); 
					}
					break;

			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINE_COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// Function.g:1:8: ( T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | VOIDNULL | IF | UNLESS | WHILE | RETURN | ACT | ID | NUMBER | NEWLINE | WS | COMMENT | LINE_COMMENT )
		int alt11=35;
		alt11 = dfa11.predict(input);
		switch (alt11) {
			case 1 :
				// Function.g:1:10: T__31
				{
				mT__31(); 

				}
				break;
			case 2 :
				// Function.g:1:16: T__32
				{
				mT__32(); 

				}
				break;
			case 3 :
				// Function.g:1:22: T__33
				{
				mT__33(); 

				}
				break;
			case 4 :
				// Function.g:1:28: T__34
				{
				mT__34(); 

				}
				break;
			case 5 :
				// Function.g:1:34: T__35
				{
				mT__35(); 

				}
				break;
			case 6 :
				// Function.g:1:40: T__36
				{
				mT__36(); 

				}
				break;
			case 7 :
				// Function.g:1:46: T__37
				{
				mT__37(); 

				}
				break;
			case 8 :
				// Function.g:1:52: T__38
				{
				mT__38(); 

				}
				break;
			case 9 :
				// Function.g:1:58: T__39
				{
				mT__39(); 

				}
				break;
			case 10 :
				// Function.g:1:64: T__40
				{
				mT__40(); 

				}
				break;
			case 11 :
				// Function.g:1:70: T__41
				{
				mT__41(); 

				}
				break;
			case 12 :
				// Function.g:1:76: T__42
				{
				mT__42(); 

				}
				break;
			case 13 :
				// Function.g:1:82: T__43
				{
				mT__43(); 

				}
				break;
			case 14 :
				// Function.g:1:88: T__44
				{
				mT__44(); 

				}
				break;
			case 15 :
				// Function.g:1:94: T__45
				{
				mT__45(); 

				}
				break;
			case 16 :
				// Function.g:1:100: T__46
				{
				mT__46(); 

				}
				break;
			case 17 :
				// Function.g:1:106: T__47
				{
				mT__47(); 

				}
				break;
			case 18 :
				// Function.g:1:112: T__48
				{
				mT__48(); 

				}
				break;
			case 19 :
				// Function.g:1:118: T__49
				{
				mT__49(); 

				}
				break;
			case 20 :
				// Function.g:1:124: T__50
				{
				mT__50(); 

				}
				break;
			case 21 :
				// Function.g:1:130: T__51
				{
				mT__51(); 

				}
				break;
			case 22 :
				// Function.g:1:136: T__52
				{
				mT__52(); 

				}
				break;
			case 23 :
				// Function.g:1:142: T__53
				{
				mT__53(); 

				}
				break;
			case 24 :
				// Function.g:1:148: VOIDNULL
				{
				mVOIDNULL(); 

				}
				break;
			case 25 :
				// Function.g:1:157: IF
				{
				mIF(); 

				}
				break;
			case 26 :
				// Function.g:1:160: UNLESS
				{
				mUNLESS(); 

				}
				break;
			case 27 :
				// Function.g:1:167: WHILE
				{
				mWHILE(); 

				}
				break;
			case 28 :
				// Function.g:1:173: RETURN
				{
				mRETURN(); 

				}
				break;
			case 29 :
				// Function.g:1:180: ACT
				{
				mACT(); 

				}
				break;
			case 30 :
				// Function.g:1:184: ID
				{
				mID(); 

				}
				break;
			case 31 :
				// Function.g:1:187: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 32 :
				// Function.g:1:194: NEWLINE
				{
				mNEWLINE(); 

				}
				break;
			case 33 :
				// Function.g:1:202: WS
				{
				mWS(); 

				}
				break;
			case 34 :
				// Function.g:1:205: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 35 :
				// Function.g:1:213: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA11 dfa11 = new DFA11(this);
	static final String DFA11_eotS =
		"\1\uffff\1\36\7\uffff\1\41\1\uffff\1\43\1\45\1\47\3\30\3\uffff\4\30\2"+
		"\uffff\1\34\1\57\14\uffff\1\61\3\30\1\66\2\30\1\uffff\1\30\1\uffff\1\30"+
		"\1\73\1\30\1\75\1\uffff\2\30\1\100\1\101\1\uffff\1\102\1\uffff\2\30\3"+
		"\uffff\1\105\1\30\1\uffff\1\107\1\uffff";
	static final String DFA11_eofS =
		"\110\uffff";
	static final String DFA11_minS =
		"\1\11\1\75\7\uffff\1\52\1\uffff\3\75\1\117\1\114\1\157\3\uffff\1\157\1"+
		"\106\1\110\1\145\2\uffff\1\12\1\11\14\uffff\1\60\1\123\1\162\1\151\1\60"+
		"\1\111\1\164\1\uffff\1\105\1\uffff\1\105\1\60\1\144\1\60\1\uffff\1\114"+
		"\1\165\2\60\1\uffff\1\60\1\uffff\1\105\1\162\3\uffff\1\60\1\156\1\uffff"+
		"\1\60\1\uffff";
	static final String DFA11_maxS =
		"\1\175\1\75\7\uffff\1\57\1\uffff\3\75\1\117\1\114\1\157\3\uffff\1\157"+
		"\1\106\1\110\1\145\2\uffff\1\12\1\40\14\uffff\1\172\1\123\1\162\1\151"+
		"\1\172\1\111\1\164\1\uffff\1\105\1\uffff\1\105\1\172\1\144\1\172\1\uffff"+
		"\1\114\1\165\2\172\1\uffff\1\172\1\uffff\1\105\1\162\3\uffff\1\172\1\156"+
		"\1\uffff\1\172\1\uffff";
	static final String DFA11_acceptS =
		"\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\13\6\uffff\1\25\1\26"+
		"\1\27\4\uffff\1\36\1\37\2\uffff\1\41\1\2\1\1\1\42\1\43\1\12\1\15\1\14"+
		"\1\17\1\16\1\21\1\20\7\uffff\1\40\1\uffff\1\22\4\uffff\1\32\4\uffff\1"+
		"\24\1\uffff\1\31\2\uffff\1\35\1\23\1\30\2\uffff\1\33\1\uffff\1\34";
	static final String DFA11_specialS =
		"\110\uffff}>";
	static final String[] DFA11_transitionS = {
			"\1\34\1\33\2\uffff\1\32\22\uffff\1\34\1\1\4\uffff\1\2\1\uffff\1\3\1\4"+
			"\1\5\1\6\1\7\1\10\1\uffff\1\11\12\31\1\uffff\1\12\1\13\1\14\1\15\2\uffff"+
			"\3\30\1\16\1\17\3\30\1\25\15\30\1\26\3\30\4\uffff\1\30\1\uffff\5\30\1"+
			"\20\13\30\1\27\3\30\1\24\4\30\1\21\1\22\1\23",
			"\1\35",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\37\4\uffff\1\40",
			"",
			"\1\42",
			"\1\44",
			"\1\46",
			"\1\50",
			"\1\51",
			"\1\52",
			"",
			"",
			"",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"",
			"",
			"\1\33",
			"\2\34\2\uffff\1\34\22\uffff\1\34",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\30\7\uffff\15\30\1\60\14\30\4\uffff\1\30\1\uffff\32\30",
			"\1\62",
			"\1\63",
			"\1\64",
			"\12\30\7\uffff\4\30\1\65\25\30\4\uffff\1\30\1\uffff\32\30",
			"\1\67",
			"\1\70",
			"",
			"\1\71",
			"",
			"\1\72",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\74",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"\1\76",
			"\1\77",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"\1\103",
			"\1\104",
			"",
			"",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\106",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			""
	};

	static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
	static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
	static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
	static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
	static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
	static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
	static final short[][] DFA11_transition;

	static {
		int numStates = DFA11_transitionS.length;
		DFA11_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
		}
	}

	protected class DFA11 extends DFA {

		public DFA11(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 11;
			this.eot = DFA11_eot;
			this.eof = DFA11_eof;
			this.min = DFA11_min;
			this.max = DFA11_max;
			this.accept = DFA11_accept;
			this.special = DFA11_special;
			this.transition = DFA11_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | VOIDNULL | IF | UNLESS | WHILE | RETURN | ACT | ID | NUMBER | NEWLINE | WS | COMMENT | LINE_COMMENT );";
		}
	}

}
