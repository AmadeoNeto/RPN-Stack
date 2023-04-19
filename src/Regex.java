import java.util.regex.Pattern;

public class Regex {

    public static boolean isNum(String token) {
        Pattern pattern = Pattern.compile("d*");
        return pattern.matcher(token).find();
    }

    public static boolean isOP(String token) {
        Pattern pattern = Pattern.compile("\\+|-|\\*|\\/");
        return pattern.matcher(token).find();
    }
}
