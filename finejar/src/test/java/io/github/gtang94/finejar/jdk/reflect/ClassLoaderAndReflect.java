package io.github.gtang94.finejar.jdk.reflect;

/**
 * 类加载流程：
 * 1. 加载: 将.class文件解析成java.lang.Class对象，
 * 2. 链接
 *  2.1 验证：确保Class文件的字节流中包含的信息符合JVM要求
 *  2.2 准备：为静态变量分配内存并设置默认初始值
 *  2.3 解析：将常量池中的符号引用替换成直接引用
 * 3. 初始化：真正执行类中定义的代码，主要是给静态变量赋值、及执行静态代码块
 */
public class ClassLoaderAndReflect {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 通过此方式获取的Class，只是进行到 类加载流程的第一步，即仅仅将.class文件解析成
         * java.lang.Class了，没有进行下面的步骤
         */
        Class<Student> clz1 = Student.class;
        System.err.println(clz1);

        /**
         * Class.forName方法中，forName0中指定初始化参数恒定为true，即用这种方式获取的Class一定会
         * 进行到 类加载流程的第三步，所以会出现 ’静态代码块初始化ing‘
         */
        Class<?> clz2 = Class.forName("io.github.gtang94.finejar.jdk.reflect.Student");
        System.err.println(clz2);

        /**
         * 类的静态方法、静态代码块、非静态代码块、构造方法都被加载了，是一个完整的运行时类
         */
        Student student = new Student();
        Class<? extends Student> clz3 = student.getClass();
        System.err.println(clz3);
    }
}

class Student {

    static {
        System.err.println("静态代码块初始化ing");
    }

    {
        System.err.println("非静态代码块初始化ing");
    }

    public Student() {
        System.err.println("构造方法初始化ing");
    }
}
