package whu.Jerome;

import org.junit.Test;
import whu.Jerome.Main.*;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    //对创建对象函数的测试
    @Test
    public void testCreateObject()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNotNull(Main.createObject("whu.Jerome.Classes.JeromeClass"));
        assertThrows(ClassNotFoundException.class,()->Main.createObject("whu.Jerome.Classes.Jerome"));  //类不存在的情况下的异常测试
    }

    //对两个注解检查方法的测试
    @Test
    public void testCheckAnnotation1() throws ClassNotFoundException, NoSuchMethodException {
        assertEquals("Init",Main.checkAnnotation1("whu.Jerome.Classes.JeromeClass"));
        assertThrows(ClassNotFoundException.class,()-> Main.checkAnnotation1("J"));
        assertThrows(NoSuchMethodException.class,()-> Main.checkAnnotation1("whu.Jerome.Classes.JeromeClass2"));  //含注解的方法不存在时的异常测试
    }

    @Test
    public void testCheckAnnotation2(){
        assertThrows(ClassNotFoundException.class,()-> Main.checkAnnotation2("J"));
        assertThrows(NoSuchMethodException.class,()-> Main.checkAnnotation2("whu.Jerome.Classes.JeromeClass2"));  //含注解的方法不存在时的异常测试
    }

    //对执行方法的测试
    @Test
    public void testExecuteMethod() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String classname1 = "whu.Jerome.Classes.JeromeClass";
        //String classname2 = "whu.Jerome.Classes.JeromeClass2";
        String classname3 = "whu.Jerome.Classes.JeromeClass3";
        //测试不同的类中调用含有对应注解的方法是否能成功
        assertTrue(Main.executeMethod(classname1,Main.createObject(classname1),Main.checkAnnotation1(classname1),Main.checkAnnotation2(classname1)));
        //assertTrue(Main.executeMethod(classname2,Main.createObject(classname2),Main.checkAnnotation1(classname2),Main.checkAnnotation2(classname2)));
        assertTrue(Main.executeMethod(classname3,Main.createObject(classname3),Main.checkAnnotation1(classname3),Main.checkAnnotation2(classname3)));
    }
}
