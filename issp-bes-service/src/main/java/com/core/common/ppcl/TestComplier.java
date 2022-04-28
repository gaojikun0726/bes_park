package com.core.common.ppcl;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class TestComplier {
	
	public static void main(String[] args) throws Exception {

		String[] tests = { 		        
				  "E:\\PCP.txt"			 
				};
//		String inputStr = "PCP()" + 
//				"{  " + 
//				"     Count=0;" + 
//				"     SLEEP(0,0,5,0);" + 
//				"     WHILE(1)" + 
//				"	 {" + 
//				" 		 JNFGS_HJWD1 = GetRS485Data(JNFGS_HJWD1,3,1,2,1,0,0,0,0,0,0,2,1);  " + 
//				"		 JNFGS_HJSD1 = GetRS485Data(JNFGS_HJSD1,3,1,2,1,0,0,0,0,0,1,2,1);  " + 
//				"		 JNFGS_HJWD2 = GetRS485Data(JNFGS_HJWD2,3,2,2,2,0,0,0,0,0,0,2,1);  " + 
//				"		 JNFGS_HJSD2 = GetRS485Data(JNFGS_HJSD2,3,2,2,2,0,0,0,0,0,1,2,1); " + 
//				"		 JNFGS_LSZT1 = GetRS485Data(JNFGS_LSZT1,3,3,2,1,0,0,0,0,0,9,2,1);" + 
//				"		JNFGS_PDGSDZT1 = GetRS485Data(JNFGS_PDGSDZT1,3,4,2,1,0,0,0,0,0,0,2,1);" + 
//				"	JNFGS_COUNT =JNFGS_COUNT+1;" + 
//				"	}}	";
//		run(inputStr);
		for (String s : tests) {
		  run(s);
		}
	}

	public static void run(String inputStr) throws Exception {
		//以文件的形式输出
		File file=new File("src/main/java/com/core/common/ppcl/program.txt");
        if(!file.exists())
             file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        PrintStream p=new PrintStream(out);
            
        //文件
		ANTLRFileStream input = new ANTLRFileStream(inputStr);
		//字符串
//        InputStream is = new ByteArrayInputStream(inputStr.getBytes());  
//		ANTLRInputStream input = new ANTLRInputStream(is);
		//lexer 
		FunctionLexer lexer = new FunctionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
        //parser
		FunctionParser parser = new FunctionParser(tokens);
		FunctionParser.prog_return ret = parser.prog();
		
		//AST		
		CommonTree t = (CommonTree) ret.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
		nodes.setTokenStream(tokens);

		//compiler
		FunctionComplier c= new FunctionComplier(nodes);
		c.prog();
		
		//print compile result
		Map<String, List<Instruction>> functionMap = c.getFunctionMap();
		Set<Entry<String, List<Instruction>>> set = functionMap.entrySet();
		for (Entry<String, List<Instruction>> e : set) {
			for(Instruction ins: e.getValue()){
				p.println(ins.toString());
			}
		}	
        out.close();		
	}
}
