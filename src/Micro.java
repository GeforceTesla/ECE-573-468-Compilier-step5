import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Micro {

	public static void main(String[] args) throws Exception{
		ANTLRFileStream file = new ANTLRFileStream(args[0]);
		Lexer lexer = new MicroLexer(( CharStream)file);
		CommonTokenStream token_stream = new CommonTokenStream(lexer);

		MicroParser parser = new MicroParser(token_stream);
		//ANTLRErrorStrategy es = new Error_handler();
		//parser.setErrorHandler(es);
		GenerateCode codeGenerator = new GenerateCode(parser.symbolTree);		

		//parse and print the entire symbol table tree
        	Boolean check = true;
        	try {
          		ParseTree tree = parser.program();
			ParseTreeWalker walker = new ParseTreeWalker();

			MicroIRListener listener = new MicroIRListener(parser.symbolTree, codeGenerator);

			walker.walk(listener, tree);
        	} catch (Exception e) {
         	   	check = false;
        	}



       	 	if (check) {
			codeGenerator.print_all_irNode();
			codeGenerator.convertCode();
			codeGenerator.print_all_tinyNode();			
          		//parser.symbolTree.print_entire_tree();
       		}
	}

	public static class Error_handler extends DefaultErrorStrategy {

		@Override
		public void recover(Parser recognizer, RecognitionException e){
		}

		@Override
		public Token recoverInline(Parser recognizer){
			InputMismatchException e = null;
			throw new ParseCancellationException(e);
	 
		}
		
		@Override
		public void reportError(Parser recognizer, RecognitionException e){
			
		}
	}
}

