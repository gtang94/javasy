package io.github.gtang94.finejar.aspose.creck;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class SlidesCreck {

    public static void main(String[] args) {
        modifySlidesJar();
    }

    public static void modifySlidesJar() {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath("D:\\workspace\\lib\\aspose-slides-21.10-jdk16.jar");
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.slides.internal.of.public");
            //从class对象中解析获取所有方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            //遍历重载的方法
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ps.length == 3 && ctMethod.getName().equals("do")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    ctMethod.setBody("{}");
                }
            }

            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile("C:\\Users\\olivi\\Desktop\\");

        } catch (Exception e) {
            System.out.println("错误==" + e);
        }

    }
}
