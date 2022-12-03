package ru.croc.task13.src;
import java.util.*;

public class ReccomendationGenerator {

    List<String> filmsList = new ArrayList<>();
    Set<String> userFilms = new HashSet<>();
    String watchedFilms;
    Map<String,Integer> recommendations = new HashMap<>();

    ReccomendationGenerator(List<String> filmsList,Set<String> userFilms,String watchedFilms)
    {
        this.filmsList.addAll(filmsList);
        this.userFilms.addAll(userFilms);
        this.watchedFilms = watchedFilms;
    }

    private void getReccomentadionList()
    {
        int matchedFilms = 0;
        String[] filmsArr = watchedFilms.split(",");
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

    }


    public Integer getOneRecommendation()
    {
        getReccomentadionList();
        int maxIndex = 0;
        Integer maxContain = 0;
        int counter=0;
        for(var reccomend:recommendations.keySet())
        {
            counter = recommendations.get(reccomend);
            if(counter>maxIndex)
            {
                maxIndex=counter;
                maxContain=Integer.parseInt(reccomend);
            }
        }
        return maxContain;
    }
}
