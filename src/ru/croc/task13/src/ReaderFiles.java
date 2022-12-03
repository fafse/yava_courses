package ru.croc.task13.src;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFiles {

    public List<String> readFile(String path) {
        ArrayList<String> fileContent = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public void writeToFile(String path, List<String> listToWrite,ArrayList<String> fileContent) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(var content:fileContent)
        {
            writer.write(content+"\n");

        }
        writer.close();
    }


}
