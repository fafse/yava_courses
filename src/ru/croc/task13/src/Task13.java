package ru.croc.task13.src;

import java.util.*;

public class Task13 {

    public static Map<String,Integer> getReccomentadionList(List<String>  filmsList, Set<String>  userFilms, String watchedFilms)
    {
        int matchedFilms = 0;
        String[] filmsArr = watchedFilms.split(",");
        Map<String,Integer> recommendations = new HashMap<>();
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
                for (var tmp:
                     curUserFilms) {
                    if(recommendations.keySet().contains(tmp))
                    {
                        recommendations.put(tmp,recommendations.get(tmp)+1);
                    }else
                    {
                        recommendations.put(tmp,1);
                    }
                }
            }
        }
        for (var tmp:
                filmsArr) {
            if(recommendations.keySet().contains(tmp))
            {
                recommendations.remove(tmp);
            }
        }
        return recommendations;

    }


    public static Integer getOneRecommendation(Map<String,Integer> recommendations)
    {
        int maxIndex = 0;
        Integer maxContain = 0;
        int counter=0;
        for(var reccomend:recommendations.keySet())
        {
            counter = recommendations.get(reccomend);
            if(counter>=maxIndex)
            {
                maxIndex=counter;
                maxContain=Integer.parseInt(reccomend);
            }
        }
        return maxContain;
    }

    public static void main(String[] args) {
        ru.croc.task13.src.ReaderFiles fileWorker = new ru.croc.task13.src.ReaderFiles();
        List<String> watchedFilms = new ArrayList<>();
        List<String> films = new ArrayList<>();
        Set<String> userFilms = new LinkedHashSet<>();
        films.addAll(fileWorker.readFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\films.txt"));
        userFilms.addAll(fileWorker.readFile("C:\\Users\\k5469\\IdeaProjects\\yava_courses\\src\\ru\\croc\\task13\\src\\watched_films.txt"));
        Map<String,Integer> recommendations =new HashMap<>();
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter watched films");
        String watchedFilmsByCurUser = cin.nextLine();
        recommendations= getReccomentadionList(films, userFilms,watchedFilmsByCurUser);
        Integer recToUser= getOneRecommendation(recommendations)-1;
        System.out.println("Your recommendation is\n"+
                films.get(recToUser));
    }
}
