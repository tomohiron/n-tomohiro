package sandbox;

public class RegexSample {

    private static final String URL_REGEX = "https?://[-_.!~*'()a-zA-Z0-9;/?:@&=+$,%#]+";

    public static void main(String[] args) {
        System.out.println("abcdefabcd".replaceAll("bc", "$0-$0"));
        System.out.println("abcdefabcd".replaceAll("(bc)", "$1-$1"));
        System.out.println("abcdef".matches(URL_REGEX));
        System.out.println("http://www.google.com/".matches(URL_REGEX));
    }

}
