package ru.croc.task12.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Task12 {

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
        Set<String> badWords = Set.of("badword","very bad word", "wordbed");
        System.out.println("Before:");
        System.out.println(comments);
        commentFilter.filterComments(comments,badWords);
        System.out.println("After");
        System.out.println(comments);
    }
}