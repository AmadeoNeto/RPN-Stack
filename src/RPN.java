import java.util.List;
import java.util.Stack;

public class RPN {

    private Stack<Token> rpnStack = new Stack<Token>(); // Pilha de tokens usada no cálculo da expressão

    public double evaluate(List<Token> tokenList){
        
        for (Token token : tokenList) {
            if(isOperator(token)){
                //Caso o token seja um operador, realiza sua operação e armazena o resultado na pilha
                double e2 = Double.parseDouble(rpnStack.pop().lexeme);
                double e1 = Double.parseDouble(rpnStack.pop().lexeme);

                double res = evaluateOperation(e1,e2,token);

                Token resToken = new Token(TokenType.NUM, Double.toString(res)); 
                rpnStack.push(resToken);
            }
            else {
                // Caso o token seja um número, o coloca na pilha
                rpnStack.push(token);
            }
        }


        //O resultado da operação será o ultimo elemento da pilha
        return Double.parseDouble(rpnStack.pop().lexeme);
    }

    private boolean isOperator(Token token) {
        if(token.type == TokenType.MINUS || token.type == TokenType.PLUS ||
            token.type == TokenType.STAR || token.type == TokenType.SLASH){
                return true;
        }
        else{
            return false;
        }
    }

    // Função que realiza as operações e retorna seus resultados
    public static double evaluateOperation(double e1, double e2, Token operator){
        double res = 0.0;

        switch (operator.type) {
            case PLUS: 
                res = e1 + e2;
                break;
            case MINUS:
                res = e1 - e2;
                break;
            case STAR:
                res = e1 * e2;
                break;
            case SLASH:
                res = e1 / e2;
                break;
        }
        return res;
    }
}
