import java.io.CharConversionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {

    static final String[] OPERATORS = new String[]{"+","-","*","/"}; // Os operadores suportados pelo compilador

    //Reveive the filePath and return the tokens
    public List<Token> scan(String filepath) throws FileNotFoundException, CharConversionException{
        List<String> lines = getSourceProgramLines(filepath);
        ArrayList<Token> scannedTokens = new ArrayList<Token>();

        for (String line : lines) {
            Token token = tokenize(line);
            scannedTokens.add(token);
            System.out.println(token);
        }

        return scannedTokens;
    }

    //Returns the source program as a list of it lines
    private List<String> getSourceProgramLines(String filePath) throws FileNotFoundException{
        File source = new File(filePath);
        Scanner fileScanner = new Scanner(source);
        ArrayList<String> programLines = new ArrayList<String>();
        
        //Insert the lines in the list removing trailing spaces
        while(fileScanner.hasNextLine()){
            programLines.add(fileScanner.nextLine().trim());
        }
        fileScanner.close();

        return programLines;
    }

    private Token tokenize(String lexeme) throws CharConversionException{
        TokenType type = null;

        if(isOperator(lexeme)){
            switch (lexeme) {
                case "+":
                    type = TokenType.PLUS;
                    break;
                case "-":
                    type = TokenType.MINUS;
                    break;
                case "*":
                    type = TokenType.STAR;
                    break;
                case "/":
                    type = TokenType.SLASH;
                    break;
            }
        } else if (isNum(lexeme)){
            type = TokenType.NUM;
        } else {
            throw new CharConversionException("Unexpected character: " + lexeme.toString());
        }

        return new Token(type,lexeme);
    }

    // Função que verifica se um lexeme é um operador
    private boolean isOperator(String exp){
        for (String op : OPERATORS) {
            if (op.equals(exp)){
                return true;
            }
        }
        return false;
    }

    // Função que verifica se um lexeme é numérico
    private boolean isNum(String exp){
        try{
            Double.parseDouble(exp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    } 
}
