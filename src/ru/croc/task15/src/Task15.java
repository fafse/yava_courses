package ru.croc.task15.src;

import java.util.*;

public class Task15 {
    public static void main(String[] args) {
        int tmp = -1;
        String stringTmp;
        Scanner cin = new Scanner(System.in);
        List<AgeGroup> groups = new ArrayList<>();
        if(args.length!=0) {
            for (String arg : args) {
                groups.add(new AgeGroup(tmp+1, Integer.parseInt(arg)));
                tmp = Integer.parseInt(arg);
            }
        }
        groups.add(new AgeGroup(tmp+1, 123));
        Collections.reverse(groups);
        stringTmp= cin.nextLine();
        while(!stringTmp.equals("END")&&!stringTmp.equals(""))
        {
            for(var group:groups)
            {
                if(group.addRespondent(Respondent.createRespondent(stringTmp)))
                {
                    break;
                }
            }
            stringTmp= cin.nextLine();
        }


        for(var group : groups)
        {
            System.out.print(group);
        }


    }
}