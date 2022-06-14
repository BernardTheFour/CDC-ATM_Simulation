package util;

import java.io.File;

import pattern.SingletonPath;

public class FileManagement {

    public static void extractPath(String path){
        SingletonPath.setPath(path);

        File[] files = new File(path).listFiles();

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            String extension = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());

            if (!"csv".equals(extension)){
                
            }
        }
    }
}
