package ru.croc.task13.src;

import java.util.*;

public class Task13 {

    public static void main(String[] args) {
        ru.croc.task13.src.ReaderFiles fileWorker = new ru.croc.task13.src.ReaderFiles();
        List<String> watchedFilms = new ArrayList<>();
        List<String> films = new ArrayList<>();
        Set<String> userFilms = new LinkedHashSet<>();
        films.addAll(fileWorker.readFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\films.txt"));
        userFilms.addAll(fileWorker.readFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\watched_films.txt"));
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter watched films");
        String watchedFilmsByCurUser = cin.nextLine();
        ru.croc.task13.src.ReccomendationGenerator reccomendationGenerator = new ru.croc.task13.src.ReccomendationGenerator(films,userFilms,watchedFilmsByCurUser);
        Integer recToUser= reccomendationGenerator.getOneRecommendation()-1;
        System.out.println("Your recommendation is\n"+
                films.get(recToUser));
    }
}
