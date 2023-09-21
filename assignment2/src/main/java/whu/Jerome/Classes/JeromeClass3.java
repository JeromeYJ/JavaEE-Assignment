package whu.Jerome.Classes;

import whu.Jerome.Annoation.InitMethod;

public class JeromeClass3 {
    private int id;
    private String name;
    private boolean isTeacher;

    public JeromeClass3(int id, String name, boolean isTeacher){
        this.id = id;
        this.name = name;
        this.isTeacher = isTeacher;
    }

    //@InitMethod
    public void Init(){
        id = 3;
        name = "Tom";
        isTeacher = true;
        System.out.println("Successfully called the method");
    }

    @InitMethod
    public int getId(){
        System.out.println("Successfully called the method of JeromeClass3");
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
