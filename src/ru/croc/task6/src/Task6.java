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
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }
    public static String removeJavaComments(String source)
    {
        if(source==null)
        {
            return null;
        }
        Boolean isBlockComment_noEnd = false;
        int indexStart=-1,indexEnd=-1,indexStartBlock=-1,indexEndBlock=-1;

        String[] sources = source.split("\n");
            for (int i = 0;i<sources.length;i++) {
                StringBuilder tmp = new StringBuilder(sources[i]);
                                                               //block who checks double slash blocks
                indexStart = sources[i].lastIndexOf("/*");
                indexEnd = sources[i].lastIndexOf("*/");
                if(indexEnd!=-1)
                {
                    indexEndBlock=indexEnd;
                }
                if(indexStart!=-1)
                {
                    indexStartBlock=indexStart;
                    isBlockComment_noEnd=true;
                    if(indexEnd!=-1)
                    {
                        tmp.replace(indexStartBlock,indexEndBlock+2,"");
                        sources[i] = tmp.toString();
                        isBlockComment_noEnd=false;
                    }
                }
                if(isBlockComment_noEnd&&indexEndBlock!=-1)//если нашли конец блочного комментария
                {
                    isBlockComment_noEnd=false;
                    tmp.replace(0,indexEndBlock+2,"");
                    sources[i] = tmp.toString();
                }
                if(isBlockComment_noEnd&&indexEnd==-1)//если строчка внутри блочного комментария или вначале
                {
                    if(indexStart==-1)//если строчка внутри блочного комментария но не вначале
                    {
                        indexStart=0;
                    }
                    tmp.replace(indexStart,tmp.length(),"");
                    sources[i] = tmp.toString();
                }
                indexStart=sources[i].lastIndexOf("//");
                if(indexStart!=-1)
                {
                    tmp.replace(indexStart,tmp.length(),"");
                    sources[i] = tmp.toString();
                }
                            //block who checks multilines blocks

            }
        String result = String.join("\n", sources);
        return result;
    }

    public static void main(String[] args) {
        String fileName =System.getProperty("user.dir")+"\\src\\"+"testing.txt";
        System.out.println(fileName);
        System.out.println(removeJavaComments(readFromFile(fileName)));
    }
}
