package pattern;

public class SingletonUtils {

    private static SingletonUtils instance;

    private String csvRowDelimiter = "\r\n";

    public static void init() {
        if (instance == null) {
            instance = new SingletonUtils();
        }
    }

    public static String getCSVRowDelimiter(){
        return instance.csvRowDelimiter;
    }
}
