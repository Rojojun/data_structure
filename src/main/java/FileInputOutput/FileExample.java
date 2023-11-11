package FileInputOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("/users/hojunna/Desktop/스크린샷 2022-06-15 오전 10.47.10.png");
        String fileName = file.getName();
        int pos = fileName.lastIndexOf(".");

        System.out.println("Extension : " + fileName.substring(pos + 1));

        File fileSec = new File(args[0]);
        File[] files = fileSec.listFiles();

        for (int i = 0; i < files.length; i++) {
            String fileNameInList = files[i].getName();
            System.out.println(files[i].isDirectory() ? "[" + fileNameInList + "]" : fileNameInList);
        }
    }
}
