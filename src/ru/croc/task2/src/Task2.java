import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double startEl=scanner.nextDouble(),
        double raznProgr=scanner.nextDouble(),
        double numMembers=reader.readDouble();

        for(int i = 0;i<numMembers;i++)
        {
            startEl+=raznProgr;
        }
        System.out.println(startEl);
    }
}
