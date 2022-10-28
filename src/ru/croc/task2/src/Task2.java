import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double startEl= scanner.nextDouble(),
                raznProgr= scanner.nextDouble(),
                numMembers=scanner.nextDouble(), sum = 0;
        for(double i = 0;i<numMembers;i++)
        {
            sum+=startEl;
            startEl+=raznProgr;
        }
        System.out.println("Sum:"+sum);
    }
}
