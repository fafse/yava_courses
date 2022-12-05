package ru.croc.task14.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import ru.croc.task14.src.CommentFilter;

public class Task14 {

    public static void main(String[] args) {
        CommentFilter commentFilter = new CommentFilter();
        List<String> comments = new ArrayList<>();
        comments.add("one");
        comments.add("word");
        comments.add("bad bad word");
        comments.add("very bad very  bad word");
        comments.add("tWOwoowaoo");
        comments.add("Threrererewrwer");
        comments.add("bAdWoRD");
        comments.add("VeRY BAD WorD");
        Predicate<String> badWords = new Predicate<String>() {
            Set<String> censoredWords = Set.of("bad word","very bad word", "wordbad");
            @Override
            public boolean test(String comment) {
                for(var badword :censoredWords)
                {
                    if(comment.toLowerCase().contains(badword)) {
                        return true;
                    }
                }
                return false;
            }
        };
        System.out.println("Before:");
        System.out.println(comments);
        System.out.println("After");
        System.out.println(commentFilter.filterComments(comments,badWords));
    }
}