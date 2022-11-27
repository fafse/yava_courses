package ru.croc.task12.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommentFilter implements ru.croc.task12.src.BlackListFilter
{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList)
    {
        List<String> censoredComments = new ArrayList<>();
        for(var comment:comments)
        {
            for(var badWord : blackList) {

                if(comment.toLowerCase().contains(badWord))
                {
                    censoredComments.add(comment);
                    break;
                }
            }
        }
        comments.removeAll(censoredComments);
    }
}