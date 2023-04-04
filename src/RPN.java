import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RPN {

    static String[] operators = new String[]{"+","-","*","/"}; // Os operadores suportados pelo compilador
    private Stack<String> rpnStack = new Stack<String>(); // Pilha de tokens usada no cálculo da expressão

    // Metodo usado para avaliar uma expressão contida em um arquivo
    public double evaluateFile(File file, boolean debug) throws FileNotFoundException{
        Scanner fileScanner = new Scanner(file);

        while(fileScanner.hasNextLine()){
            String token = fileScanner.nextLine().trim();

            if(isOperator(token)){
                //Caso o token seja um operador, realiza sua operação e armazena o resultado na pilha
                double e2 = Double.parseDouble(rpnStack.pop());
                double e1 = Double.parseDouble(rpnStack.pop());

                double res = evaluateOperation(e1,e2,token);

                if(debug){
                    System.out.println(e1 + " " + e2 + " " + token + " = " + res);
                }
                rpnStack.push(Double.toString(res));
            }
            else {
                // Caso o token seja um número, o coloca na pilha
                rpnStack.push(token);
            }

        }
        fileScanner.close();

        //O resultado da operação será o ultimo elemento da pilha
        return Double.parseDouble(rpnStack.pop());
    }

    // Função que verifica se um token é um operador
    private static boolean isOperator(String exp){
        for (String op : operators) {
            if (op.equals(exp)){
                return true;
            }
        }
        return false;
    }

    // Função que realiza as operações e retorna seus resultados
    public static double evaluateOperation(double e1, double e2, String operator){
        double res = 0.0;

        switch (operator) {
            case "+": 
                res = e1 + e2;
                break;
            case "-":
                res = e1 - e2;
                break;
            case "*":
                res = e1 * e2;
                break;
            case "/":
                res = e1 / e2;
                break;
 
        }
        return res;
    }
}
