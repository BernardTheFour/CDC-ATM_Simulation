package pattern;

public class SingletonUtils {

    private static SingletonUtils instance;

    /*
     * windows EOL delimiter \r\n
     * Linux and Mac EOL delimiter \n
     * Change before deploy to different OS
     */
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
