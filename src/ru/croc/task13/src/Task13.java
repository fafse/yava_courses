package ru.croc.task12.src;

import java.util.ArrayList;
import java.util.List;

public class Task13 {

    public String getSameUser(List<String> filmsList, List<String> userFilms, String watchedFilms)
    {
        int matchedFilms = 0;
        String[] filmsArr = watchedFilms.split(",");
        List<String> same = new ArrayList<>();
        for(String user: userFilms)
        {
            matchedFilms=0;
            for(int i = 0;i<filmsArr.length;i++)
            {
                if(user.contains(filmsArr[i]))
                {
                    matchedFilms++;
                }
            }
            if(matchedFilms>=(user.length()-user.length()/2)/2)
            {
                same.add(user);
            }
        }


    }

    public static void main(String[] args) {
        ru.croc.task12.src.ReadWriteFiles fileWorker = new ru.croc.task12.src.ReadWriteFiles();
        List<String> watchedFilms = new ArrayList<>();
        fileWorker.ReadFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\films.txt");
        for(var film:fileWorker.getFilms())
        {
            System.out.println(film);
        }
        ru.croc.task12.src.ReadWriteFiles userFilms = new ru.croc.task12.src.ReadWriteFiles();
        userFilms.ReadFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\watched_films.txt");
        for(var film:userFilms.getFilms())
        {
            System.out.println(film);
        }
    }
}