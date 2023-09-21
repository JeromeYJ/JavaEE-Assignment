# <center>Chapter2  Java Advance Features</center>
## 1. Stream and IO
+ Streams(��)���ļ���������ķ�ʽ
+ Java IO classes
    + �ֽ����/����
    ![ ](image-4.png) ![Alt text](image-5.png) <br/>
    + �ַ����/����
    ![Alt text](image-7.png)![Alt text](image-8.png)
+ FileInputStream and FileOutputStream(�ַ�I/O)
+ FileReader adn FileWriter
+ **<font color=red>Try-with-resource**
ʹ��try����ͷ���Դ��һЩJVM�޷��ͷŵ���Դ������ʹ�����ݿ�ʱ����ʹ�á�</font>
+ Load files in classpath

## 2. Generics ����

## 3. Reflection ���䣨��Ҫ��
+ Get Class objects(��ȡClass��Ķ���)�������ַ���
+ Field���ֶ��ࡣ
����getField()��getDeclaredFields()
+ ��ȡ��д�����˽�ж���
nameField.setAccessible(true)
+ ��ȡ��������Method��
+ ʹ��reflection��������

## 4. Annotation ע��
+ ����
1. ��д�ĵ���ͨ���������ʶ��Ԫ���������ĵ���
2. ���������ͨ���������ʶ��Ԫ���ݶԴ�����з�����
3. �����飺ͨ���������ʶ��Ԫ�����ñ�������ʵ�ֻ����ı�����
+ ����ʹ��reflection�õ�ע�⡣
    + isAnnotationPresent()����
    + getAnnotation()����

## 5. Maven ��Ŀ������
+ ����
1. �ṩ��׼�����ļ��ṹ
2. �ṩ��׼����Ŀ��������
3. �����������й���
+ pom.xml:��Ŀ�����ļ�����Ҫ��
+ pom.xml�ļ��е�scopeָ���Ǵ�����ʹ�õĽ׶Σ���compile��test�ȡ�
+ how does maven work?
![Alt text](image-9.png)

## 6. Unit Testing ��Ԫ����
+ JUnit
assert: ���Զ��ԡ������жϲ��Խ���Ƿ���ȷ
+ Fixture
+ Test for exceptions
���Ժ����Ƿ��������׳��쳣��ʹ��assertThrows()��lambda expression��lambda���ʽ����������������![Alt text](image-6.png)