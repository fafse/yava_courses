package ru.croc.task12.src;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFiles {
    private ArrayList<String> fileContent = new ArrayList<>();

    public ArrayList<String> getFileContent() {
        return fileContent;
    }

    public List<String> ReadFile(String path) {
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

    public void WriteToFile(String path, List<String> listToWrite) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(var content:fileContent)
        {
            writer.write(content+"\n");

        }
        writer.close();
    }


}
