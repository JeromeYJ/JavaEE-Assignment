package whu.Jerome.Annoation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})   //制定该自定义注解只能标注在方法上
@Inherited
public @interface InitMethod {

}
