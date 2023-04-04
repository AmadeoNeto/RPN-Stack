import java.io.File;
public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("Calc1.stk");

        RPN rpn = new RPN();
        double result = rpn.evaluateFile(file,false);

        System.out.println(result);
    }
}
