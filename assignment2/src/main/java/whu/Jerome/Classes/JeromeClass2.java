package whu.Jerome.Classes;

public class JeromeClass2 {
    private int id;
    private String name;
    private boolean isTeacher;

    public JeromeClass2(int id, String name, boolean isTeacher){
        this.id = id;
        this.name = name;
        this.isTeacher = isTeacher;
    }

    //@InitMethod
    public void Init(){
        id = 2;
        name = "Iris";
        isTeacher = true;
        System.out.println("Successfully called the method");
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
