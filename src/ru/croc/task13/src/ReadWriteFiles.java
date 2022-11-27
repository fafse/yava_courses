package ru.croc.task12.src;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFiles {
    private ArrayList<String> films = new ArrayList<>();

    public ArrayList<String> getFilms() {
        return films;
    }

    public List<String> ReadFile(String path) {
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                films.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return films;
    }

    public void WriteToFile(String path, List<String> listToWrite) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(var film:films)
        {
            writer.write(film+"\n");

        }
        writer.close();
    }


}
