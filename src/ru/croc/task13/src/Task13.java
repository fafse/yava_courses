package ru.croc.task12.src;

import java.util.*;

public class Task13 {

    public static Set<String> getReccomentadionList(List<String> filmsList, List<String> userFilms, String watchedFilms)
    {
        int matchedFilms = 0;
        String[] filmsArr = watchedFilms.split(",");
        Set<String> recommendations = new HashSet<>();
        List<String> curUserFilms = new ArrayList<>();
        for(String user:userFilms)
        {
            matchedFilms=0;
            curUserFilms.clear();
            Collections.addAll(curUserFilms, user.split(","));
            for(int i = 0;i<filmsArr.length;i++)
            {
                if(curUserFilms.indexOf(filmsArr[i])!=-1)
                {
                    matchedFilms++;
                }
            }
            if(matchedFilms>=(user.length()-user.length()/2)/2)
            {
                recommendations.addAll(curUserFilms);
            }
        }
        recommendations.removeAll(List.of(filmsArr));

        return recommendations;

    }


    public static Integer getOneRecommendation(List<String> userFilms,Set<String> recommendations)
    {
        int maxIndex = 0;
        Integer maxContain = 0;
        int counter=0;
        for(var reccomend:recommendations)
        {
            counter=0;
            for(var watched:userFilms)
            {
                if(watched.contains(reccomend))
                {
                    counter++;
                }
            }
            if(counter>maxIndex)
            {
                maxIndex=counter;
                maxContain=Integer.parseInt(reccomend);
            }
        }
        return maxContain;
    }

    public static void main(String[] args) {
        ru.croc.task12.src.ReadWriteFiles fileWorker = new ru.croc.task12.src.ReadWriteFiles();
        List<String> watchedFilms = new ArrayList<>();
        fileWorker.ReadFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\films.txt");
        ru.croc.task12.src.ReadWriteFiles userFilms = new ru.croc.task12.src.ReadWriteFiles();
        userFilms.ReadFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\watched_films.txt");
        Set<String> recommendations =new HashSet<>();
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter watched films");
        String watchedFilmsByCurUser = cin.nextLine();
        recommendations= getReccomentadionList(fileWorker.getFileContent(),userFilms.getFileContent(),watchedFilmsByCurUser);
        System.out.println("Your recommendation is\n"+fileWorker.getFileContent().get(getOneRecommendation(userFilms.getFileContent(),recommendations)-1));
    }
}