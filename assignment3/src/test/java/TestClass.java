import org.dom4j.DocumentException;
import org.example.ApplicationContext.MyApplicationContext;
import org.example.Service.BookService;
import org.example.Service.TestService;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    //对整体IOC运行结果的测试
    @Test
    public void testResult() throws Exception {
        MyApplicationContext myApplicationContext = new MyApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) myApplicationContext.getBean("bookService");

        TestService testService = (TestService) myApplicationContext.getBean("testService");

        assertTrue(bookService.save());
        assertEquals(2,testService.run());
    }

    //测试MyApplication中的几个方法
    @Test
    public void testXmlConfigFileResolver() throws DocumentException {
        MyApplicationContext myApplicationContext = new MyApplicationContext("applicationContext.xml");
        MyApplicationContext myApplicationContext1 = new MyApplicationContext();
        assertNotNull(myApplicationContext.beanDefinitionList);
        assertThrows(DocumentException.class,()->myApplicationContext1.XmlConfigFileResolver("error.xml"));
    }

    @Test
    public void testloadXmlFile() throws DocumentException {
        MyApplicationContext myApplicationContext = new MyApplicationContext();
        assertThrows(DocumentException.class,()->myApplicationContext.loadXmlFile("error.xml"));
    }

    @Test
    public void testgetBean() throws Exception {
        MyApplicationContext myApplicationContext = new MyApplicationContext("applicationContext.xml");
        assertNotNull(myApplicationContext.getBean("testClass"));
        assertThrows(Exception.class,()->myApplicationContext.getBean("error"));
        assertThrows(Exception.class,()->myApplicationContext.getBean("BookDao2"));
    }
}
