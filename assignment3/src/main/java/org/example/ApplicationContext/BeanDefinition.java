package org.example.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String id;
    private String className;
    private List<Property> properties = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId(){
        return id;
    }

    public String getClassName(){
        return className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property){
        properties.add(property);
    }
}
