package ru.croc.task9.src;
public class Hack implements Runnable {
    private static long begin;
    private static long end;
    private static String hex_password;

    public Hack(long begin, long end, String hex_password) {
        this.begin = begin;
        this.end = end;
        this.hex_password = hex_password;
    }

    public void run() {
        for (long i = begin; i <= end && !ru.croc.task9.src.Task9.is_password_found; i++) {
            String password = createPassword(i).toString();
            if (ru.croc.task9.src.Task9.hashPassword(password).equals(hex_password)) {
                ru.croc.task9.src.Task9.is_password_found = true;
                System.out.println(password);
            }
        }
    }


     /*private static String hackHashPassword(String hex) {
         String generated_password = "";
         int current_combination = begin;
         while(current_combination<=end) {
             generated_password = createPassword(current_combination++);
             if (generated_password.equals(hex)) {
                 System.out.println(generated_password);
             }
         }
         return generated_password;
         }*/

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
