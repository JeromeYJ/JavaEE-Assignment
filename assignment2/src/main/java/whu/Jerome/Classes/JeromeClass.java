package whu.Jerome.Classes;

import whu.Jerome.Annoation.InitMethod;

public class JeromeClass {
    private int id;
    private String name;
    private boolean isTeacher;

    public JeromeClass(int id, String name, boolean isTeacher){
        this.id = id;
        this.name = name;
        this.isTeacher = isTeacher;
    }

    @InitMethod
    public void Init(){
        id = 1;
        name = "Jack";
        isTeacher = false;
        System.out.println("Successfully called the method of JeromeClass");
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getIsTeacher(){
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher){
        this.isTeacher = isTeacher;
    }
}
