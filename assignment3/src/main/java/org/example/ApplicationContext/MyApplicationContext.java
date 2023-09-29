package org.example.ApplicationContext;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.InputStream;

public class MyApplicationContext {

    //存放各bean的解析后的信息
    public List<BeanDefinition> beanDefinitionList = new ArrayList<>();
    //存放生成好的bean
    private Map<String, Object> beans = new HashMap<>();
    public MyApplicationContext(String filepath) throws DocumentException {
        XmlConfigFileResolver(filepath);
    }

    public MyApplicationContext(){

    }

    //解析xml配置文件的方法
    public void XmlConfigFileResolver(String filepath) throws DocumentException {
        // 通过dom4j解析xml得到Document
        Document document = loadXmlFile(filepath);
        // 根节点为beans
        Element rootElement = document.getRootElement();
        List<Element> beanElements = rootElement.elements();

        //遍历所有bean标签
        for(Element element : beanElements){
            BeanDefinition beanDefinition = new BeanDefinition();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            //遍历bean标签下的子标签
            List<Element> subElements = element.elements("property");
            for(Element subElement : subElements){
                String propertyName = subElement.attributeValue("name");
                String propertyRef = subElement.attributeValue("ref");
                Property property = new Property(propertyName, propertyRef);
                beanDefinition.addProperty(property);
            }
            AddBeanDefinition(beanDefinition);
        }
    }

    //加载xml文件的方法
    public Document loadXmlFile(String filePath) throws DocumentException {
        Document document;
        try {
            SAXReader saxReader = new SAXReader();
            InputStream file = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            document = saxReader.read(file);    //读取XML文件,获得document对象
        } catch (DocumentException e) {
            throw new DocumentException("解析xml出现异常");
        }
        return document;
    }

    private void AddBeanDefinition(BeanDefinition beanDefinition){
        beanDefinitionList.add(beanDefinition);
    }

    public Object getBean(String id) throws Exception {
        if(beans.get(id) != null)
            return beans.get(id);

        BeanDefinition beanDefinition = null;
        for(BeanDefinition b : beanDefinitionList){
            if(id.equals(b.getId()))
                beanDefinition = b;
        }

        if(beanDefinition == null)
            throw new Exception("不存在这样的Bean");

        String className = beanDefinition.getClassName();
        Class<?> userClass = Class.forName(className);
        Object object = userClass.newInstance();

        if(beanDefinition.getProperties() == null)
            return object;
        for(Property property : beanDefinition.getProperties())
        {
            BeanDefinition propertyBean = null;
            for(BeanDefinition b : beanDefinitionList){
                if(property.getRef().equals(b.getId()))
                    propertyBean = b;
            }
            if(propertyBean == null)
                throw new Exception("property中的ref出现错误，不存在这样的Bean");

            Object propertyObject = beans.get(property.getRef());
            Class<?> propertyClass = Class.forName(propertyBean.getClassName());
            if(propertyObject == null)
                propertyObject = propertyClass.newInstance();

            //String setterName = "set" + property.getName();
            //Method setter = userClass.getMethod(setterName,propertyClass);
            //setter.invoke(propertyObject);

            //使用BeanUtil工具类，注入属性对应的对象
            BeanUtils.setProperty(object,property.getName(),propertyObject);
        }

        return object;
    }
}
