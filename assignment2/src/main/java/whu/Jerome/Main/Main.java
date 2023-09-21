package whu.Jerome.Main;

import whu.Jerome.Annoation.InitMethod;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties pro = new Properties();
        try(InputStream input = Main.class.getResourceAsStream("/one.properties"))  //资源文件在根目录，即classpath下
        {
            if(input != null){
                pro.load(input);
                String classname = pro.getProperty("classname");
                Object object = createObject(classname);
                String methodname = checkAnnotation1(classname);    //存储要调用的方法的名字
                Class<?>[] types = checkAnnotation2(classname);     //存储要调用的方法的参数列表的Class类数组
                executeMethod(classname,object,methodname,types);
            }
        }
        catch(IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
              InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    //根据资源文件创建对应类型对象的方法
    public static Object createObject(String classname)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> userclass = Class.forName(classname);
        Constructor<?> constructor = userclass.getConstructor(int.class, String.class, boolean.class);
        return constructor.newInstance(239,"Flora",false);
    }

    //检查方法是否有@InitMethod注解，返回对应方法的名称
    public static String checkAnnotation1(String classname) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> userclass = Class.forName(classname);
        Method[] methods = userclass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(InitMethod.class)){
                return method.getName();
            }
        }
        throw new NoSuchMethodException("不存在对应的方法");      //存疑，可能需要修改为抛异常
    }

    //检查方法是否有@InitMethod注解，返回对应方法的参数列表
    public static Class<?>[] checkAnnotation2(String classname) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> userclass = Class.forName(classname);
        Method[] methods = userclass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(InitMethod.class)){
                return method.getParameterTypes();
            }
        }
        throw new NoSuchMethodException("不存在对应的方法");
    }

    //执行对应的方法
    //返回值为boolean类型，方便测试
    public static boolean executeMethod(String classname, Object object, String methodname, Class<?>[] types)
            throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> userclass = Class.forName(classname);
        Method method = userclass.getMethod(methodname, types);
        method.invoke(object);
        return true;
    }
}
