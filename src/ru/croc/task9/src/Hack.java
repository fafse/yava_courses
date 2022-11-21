package ru.croc.task9.src;
public class Hack implements Runnable {
    private long begin;
    public static volatile boolean is_password_found = false;
    private long end;
    private static String hex_password;

    public Hack(long begin, long end, String hex_password) {
        this.begin = begin;
        this.end = end;
        this.hex_password = hex_password;
    }

    public void run() {
        for (long i = begin; i <= end && !is_password_found; i++) {
            String password = createPassword(i).toString();
            if (ru.croc.task9.src.Task9.hashPassword(password).equals(hex_password)) {
                is_password_found = true;
                System.out.println(password);
            }
        }
    }

    private static String createPassword(long current_combination) {
        int[] passwordInt = new int[7];
        for (int i = 0; i < 7; i++) {
            passwordInt[i] = (int) current_combination % 26;
            current_combination /= 26;
        }
        String password = "";
        for (int i = 0; i < 7; i++) {
            password += (char) ('a' + passwordInt[i]);
        }
        return password;
    }
}
