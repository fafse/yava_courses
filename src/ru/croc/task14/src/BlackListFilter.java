package ru.croc.task14.src;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface BlackListFilter<T> {
    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blackList)
    {
        ArrayList<T> resultComments = new ArrayList<>();
        for(var comment:comments)
        {
                if(!blackList.test(comment))
                {
                    resultComments.add(comment);
                }
        }
        return resultComments;
    }
}