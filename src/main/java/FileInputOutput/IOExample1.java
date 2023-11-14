package FileInputOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOExample1 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        inputStream = new ByteArrayInputStream(inSrc);
        outputStream = new ByteArrayOutputStream();

        int data = 0;

        while ((data = inputStream.read())!= -1) {
            outputStream.write(data);
        }

        outSrc = outputStream.toByteArray();

        System.out.println("Input source : " + Arrays.toString(inSrc));
        System.out.println("Output source : " + Arrays.toString(outSrc));
    }
}

