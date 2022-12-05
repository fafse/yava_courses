package ru.croc.task15.src;

public class Respondent {
    private Integer age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    Respondent(String name, int age)
    {
        this.name=name;
        this.age=age;
    }
    public static Respondent createRespondent(String man)
    {
        if(!man.contains(","))
            return null;
        String[] datas = man.split(",");
        String name=datas[0];
        int age=Integer.parseInt(datas[1]);
        if(name!=null&&name!=""&&age>=0)
        {
            return new Respondent(name,age);
        }
        return null;
    }
    @Override
    public String toString()
    {
        return name+" ("+age+")";
    }

}
