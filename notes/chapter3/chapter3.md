[TOC]
# <center>Chapter3  Spring Core</center>
## 1. Introduction to Spring
+ Spring Ecosphere
1. Spring Framework(the core framework,the basis)
2. SpringBoot(�ص㣬�򻯿���)
3. SpringCloud
+ Architecture of Spring
![Alt text](image-1.png)


## 2. Spring IOC�����Ʒ�ת��
+ Tight-couple programming�������ʽ�ı�̣�
�������ԭ��֮������������ԭ�򣨶��ڹ�����չ���ţ����ڴ����޸ķ�գ�
+ Loose-coupled programming������ϣ�<br>
![Alt text](image-2.png)
����֮�⣬ʹ��������ʽΪ����ֵ��������ͼ�е�setBookDao()������

+ IOC�����Ʒ�ת��

## 3. Spring
+ create a Spring project with Maven


## 4. Lifecycle of Beans
1. Scope of Beans
  + scope="singleton"(default)��һ�������֣�
  + scope="prototype"
2. Instantiation with Factory��ʹ�ù�������ʵ������
3. init-method and destroy-method
init-method����ʼ��beanʱ�Ĳ���
destroy-method��bean������ǰ�Ĳ���
4. Initializing and DisposableBean


## 5. Dependency Injection
1. Dependency Injection with Setter
���õķ�ʽ������set������ʹ��setterע��
2. Dependency Injection with Constructor
3. Injection for Collections
4. Autowired Injection By Type
5. Autowired Injection By Name

## 6. IOC Containers


## 7. Spring Annotations
+ Spring with annotations<br>
![Alt text](image-3.png)
@Component ֮�����޲�������Ĭ�Ͻ����µ�����Ϊbean��id