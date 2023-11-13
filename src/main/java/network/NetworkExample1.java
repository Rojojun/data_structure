package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkExample1 {
    public static void main(String[] args) {
        InetAddress ip = null;
        InetAddress[] ipArr = null;

        try {
            ip = InetAddress.getByName("www.naver.com");
            System.out.println("getHostName()    : " + ip.getHostName());
            System.out.println("getHostAddress() : " + ip.getHostAddress());
            System.out.println("toString()       : " + ip.toString());

            byte[] ipAddr = ip.getAddress();
            System.out.println("getAddress()     : " + Arrays.toString(ipAddr));

            StringBuilder result = new StringBuilder();
            for (byte b : ipAddr) {
                result.append((b < 0) ? b + 256 : b);
                result.append(".");
            }
            System.out.println("getAddress() + 256 : " + result);
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            ip = InetAddress.getLocalHost();
            System.out.println("getHostName()    : " + ip.getHostName());
            System.out.println("getHostAddress() : " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            ipArr = InetAddress.getAllByName("www.naver.com");

            for (int i = 0; i < ipArr.length; i++) {
                System.out.println("ipArr[" + i + "] : " + ipArr[i]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
