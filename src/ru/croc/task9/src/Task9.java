package ru.croc.task9.src;
//passwrd - ответ
//java src\ru\croc\task9\src\Task9.java 100 40682260CC011947FC2D0B1A927138C5
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Task9 {
    public static String[] passwordsToCheck;
    public static String[] elements = {"qwertyuiopasdfghjklzxcvbnm"};

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int numThreads = 0;
        String hex_password = "40682260CC011947FC2D0B1A927138C5";
        if(args.length==2) {
            hex_password = args[1];//"40682260CC011947FC2D0B1A927138C5";
            numThreads = Integer.parseInt(args[0]);
        }
        int numPassword= (int) Math.pow(26,7);//количество возможных комбинаций
        if(numThreads==0)
        {
            System.out.println("Enter num of threads");
            numThreads=cin.nextInt();
        }
        Thread[] threads = new Thread[numThreads];
        long t0 = System.nanoTime();
        for(int i = 0;i<numThreads;i++)
        {
            threads[i] = new Thread(new ru.croc.task9.src.Hack((long)numPassword*(i)/numThreads,(long)numPassword*(i+1)/numThreads,hex_password));
            threads[i].start();
        }
        for(int i = 0;i<numThreads;i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long t = System.nanoTime()-t0;
        System.out.println(t/1e9 +" Seconds required to solve this problem");
    }
}