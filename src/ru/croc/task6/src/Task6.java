package ru.croc.task6.src;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.Files.readAllBytes;

public class Task6 {

    public static String readFromFile(String fileName)
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }
    public static String removeComments(String source)
    {
        Boolean flag = false;
        int indexStart=-1,indexEnd=-1;
        String result="";

        String[] sources = source.split("\n");
            for (int i = 0;i<sources.length;i++) {
                indexStart=sources[i].lastIndexOf("//");
                StringBuilder tmp = new StringBuilder(sources[i]);
                System.out.println(indexStart + sources[i]);
                if(indexStart!=-1&&tmp.charAt(indexStart-1)!='"')
                {
                    System.out.println(tmp.charAt(indexStart-1));
                    tmp.replace(indexStart,tmp.length(),"");
                    sources[i] = tmp.toString();
                }
            }
        result = String.join("\n", sources);
        return source;
    }
    public static String removeJavaComments(String source)
    {
        StringBuffer newSource=new StringBuffer(source);
        int indexStart= newSource.indexOf("//");
        int indexEnd = newSource.indexOf("\n");
        while(indexStart!=-1)//remove comments like this one
        {
            if(indexEnd!=-1&&newSource.charAt(indexStart-1)!= '"')
                newSource.replace(indexStart,indexEnd+1,"");
            else if(newSource.charAt(indexStart-1)!= '"'){
                newSource.delete(indexStart,newSource.length());
            }
           indexStart = newSource.indexOf("//");
           indexEnd = newSource.indexOf("\n");
        }
        indexStart=newSource.indexOf("/*");
        indexEnd=newSource.indexOf("");
        while(indexStart!=-1)//remove comments like this one
        {
            System.out.println(indexStart + " " + indexEnd);
            if(indexEnd!=-1)
                newSource.replace(indexStart,indexEnd+2,"");
            else {
                newSource.delete(indexStart,newSource.length());
            }
            indexStart=newSource.indexOf("/*");
            indexEnd=newSource.indexOf("*/");
        }
        return newSource.toString();
    }

    public static void main(String[] args) {
        String source =
                "//lol\n//holol\nhol\n/*loh\nhol\nlol\n*/"; // test data
        String fileName ="C:\\Users\\k5469\\OneDrive\\Рабочий стол\\testing.txt";
        String test = readFromFile(fileName);
        String noComments = removeComments(test);
        System.out.println(noComments);
    }
}
