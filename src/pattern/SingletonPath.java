package pattern;

import java.io.File;

public class SingletonPath {
    private static SingletonPath instance;

    private String generalPath;

    private File account;

    public static void init() throws Exception {
        if (instance == null) {
            instance = new SingletonPath();
        } else {
            throw new Exception("Only one singleton at a time!");
        }
    }

    public static void setPath(String path){
        instance.generalPath = path;
    }

    public String getPath(String path){
        return instance.generalPath;
    }

    public static void setAccount(File file){
        instance.account = file;
    }

    public File getAccount(){
        return instance.account;
    }
}
