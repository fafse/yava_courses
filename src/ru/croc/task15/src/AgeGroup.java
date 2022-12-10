package ru.croc.task15.src;

import java.util.ArrayList;
import java.util.List;
public class AgeGroup {
    private int lowerBound=-1;
    private int higherBound = -1;

    private List<Respondent> members = new ArrayList<>();
    public AgeGroup(int lowerBound,int higherBound)
    {
        this.lowerBound=lowerBound;
        this.higherBound=higherBound;
    }
    public boolean addRespondent(Respondent person)
    {
        if(person==null)
        {
            return false;
        }
        if(person.getAge()>=lowerBound&&person.getAge()<=higherBound)
        {
            members.add(person);
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        if(!members.isEmpty()) {
            String msg = "" + lowerBound + "-" + higherBound + ":";
            members.sort(((o1, o2) ->{
                if(o2.getAge()==o1.getAge())
                {
                    return o1.getName().compareTo(o2.getName());
                }
                return Integer.compare(o2.getAge(), o1.getAge());
            } ));
            for (var member : members) {
                msg += member.toString() + ", ";
            }
                msg = msg.substring(0, msg.lastIndexOf(", "));
            return msg+"\n";
        }
        return "";
    }

}
