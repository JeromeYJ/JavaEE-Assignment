package org.example.ApplicationContext;

public class Property {
    private String name;
    private String ref;

    public Property(String name,String ref){
        this.name = name;
        this.ref = ref;
    }

    public String getName(){
        return name;
    }

    public String getRef(){
        return ref;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
