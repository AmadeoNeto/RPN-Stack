import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        double result = compile("Calc1.stk");
        System.out.println(result);
    }

    public static double compile(String filePath) throws FileNotFoundException, CharConversionException{
        
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        List<Token> tokens = null;
        
        try{
            tokens = lexer.scan(filePath);
        } catch (CharConversionException e){
            System.out.println("Error: " + e.getMessage());
            System.exit((1)); //Encerra o programa com o código de erro
        }

        RPN rpn = new RPN();
        double res = rpn.evaluate(tokens);

        return res;
    }

}
