package io.github.gtang94.finejar.jdk.reflect;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author tanggq
 * @class ReflectOption
 * @description 反射操作类
 *
 * Circle.class 和 Class.forName(“Circle”) 都可以创建Circle类的Class对象，
 * 但用Circle.class创建时，不会自动初始化该Class对象，也就是不会执行Circle中的static{}部分的代码(如果有这部分代码的话)
 *
 * @date 2021/4/21
 **/
public class ReflectOption {

    /**
     * @description: 根据反射获取Class的 类名、属性、属性名称、属性类型、属性权限
     * @return: null
     * @author: tanggq
     * @date: 2021/4/21
     **/
    @Test
    public void gainFields() {
        Class person = Person.class;
        System.err.println(person.getName()); // 获取类名

        Field[] fields = person.getFields(); // 获取public权限的属性
        for (Field field : fields) {
            int modifiers = field.getModifiers(); // 获取属性访问权限
            String type = field.getType().getName(); // 获取参数类型

            System.err.println(
                    Modifier.toString(modifiers) + " : " + type + " : " + field.getName()
            );
        }
        System.out.println();

        Field[] fields1 = person.getDeclaredFields(); // 获取所有权限类型的属性
        for (Field field : fields1) {
            int modifiers = field.getModifiers(); // 获取属性访问权限
            String type = field.getType().getName(); // 获取参数类型

            System.err.println(
                    Modifier.toString(modifiers) + " : " + type + " : " + field.getName()
            );
        }
    }

    /**
     * @description: 获取Class中的方法
     * @return: null
     * @throws:
     * @author: tanggq
     * @date: 2021/4/21
     **/
    @Test
    public void gainMethods() {
        Class person = Person.class;

//        Method[] methods = person.getMethods(); // 获取所有public方法
        Method[] methods = person.getDeclaredMethods(); // 获取所有类型的方法
        for (Method method : methods) {
            int modifiers = method.getModifiers(); // 获取方法权限

            Class resType = method.getReturnType(); // 获取方法返回值类型

            Parameter[] parameters = method.getParameters(); // 获取方法所有参数
            String params = "";
            if (null != parameters) {
                params += Arrays.asList(parameters).stream().map(Parameter::getName).collect(Collectors.joining(", "));
            }

            Class[] exceptions = method.getExceptionTypes(); // 获取方法的所有异常
            String excep = "";
            if (null != exceptions) {
                excep += Arrays.asList(exceptions).stream().map(Class::getName).collect(Collectors.joining(","));
            }

            System.out.println(
                    Modifier.toString(modifiers) + "(权限) => "
                    + resType.getName() + "(返回值类型) => "
                    + method.getName() + "(方法名) => "
                    + params + "(参数) => "
                    + excep + "(异常)"
            );
        }
    }

    /**
     * @description: 调用执行Class中的私有方法
     * @return: null
     * @author: tanggq
     * @date: 2021/4/21
     **/
    @Test
    public void callMethod() {
        Person person = new Person("zhangsan", 22);
        Class personClass = person.getClass(); // 获取类实例
        try {
            Method sayMethod = personClass.getDeclaredMethod("say", null); // 获取Person类中的say方法
            if (sayMethod != null) {
                sayMethod.setAccessible(true); // 获取私有方法的访问权限
                sayMethod.invoke(person); // 用 invoke 方法调用私有方法
            }
        } catch (NoSuchMethodException var1) {
            var1.printStackTrace();
        } catch (InvocationTargetException var3) {
            var3.printStackTrace();
        } catch (IllegalAccessException var2) {
            var2.printStackTrace();
        }
    }

    /**
     * @description: 通过反射修改Class的private属性值
     * @return: null
     * @author: tanggq
     * @date: 2021/4/21
     **/
    @Test
    public void updateAttribute() {
        Person person = new Person("张三", 22);
        Class personClass = person.getClass();
//        Class personClass = Class.forName("io.github.gtang94.finejar.jdk.reflect.Person");
        try {
            Field eyeField = personClass.getDeclaredField("commonEye");
            if (null != eyeField) {
                System.err.println("更改前: " + person.getCommonEye());
                eyeField.setAccessible(true);
                eyeField.set(person, 3);
                System.err.println("更改后: " + person.getCommonEye());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
