import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double startEl,raznProgr,numMembers, sum = 0;
        if(args.length==3)
        {
            startEl=Double.parseDouble(args[0]);
            raznProgr=Double.parseDouble(args[1]);
            numMembers=Double.parseDouble(args[2]);
        }else
        {
            startEl= scanner.nextDouble();
            raznProgr= scanner.nextDouble();
            numMembers=scanner.nextDouble();
        }

        for(double i = 0;i<numMembers;i++)
        {
            sum+=startEl;
            startEl+=raznProgr;
        }
        System.out.println("Sum:"+sum);
    }
}
