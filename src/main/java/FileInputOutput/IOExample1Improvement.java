package FileInputOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOExample1Improvement {
    public static void main(String[] args) {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        try {
            while (input.available() > 0) {
                int len = input.read(temp); // 읽어 온 데이터의 개수를 반환한다.
                output.write(temp, 0, len); // 읽어 온 만큼만 write를 함 (가져_올_배열, offset, 읽어_온_데이터_개수)
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        outSrc = output.toByteArray();

        System.out.println("input source  : " + Arrays.toString(inSrc));
        System.out.println("temp          : " + Arrays.toString(temp));
        System.out.println("output source : " + Arrays.toString(outSrc));
    }
}
