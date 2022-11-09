package io.github.gtang94.finejar.aspose;

import com.aspose.words.License;
import javassist.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class WordCreck {

    public static void main(String[] args) {
//        analysisWordsJar();
        modifyWordsJar();
    }

    public static void analysisWordsJar() {
        try {
            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
            License license = new License();
            license.setLicense(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyWordsJar() {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath("D:\\workspace\\lib\\aspose-words-21.11.0-jdk17.jar");
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzXDb");
            //从class对象中解析获取指定的方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods("zzY0J");
            //遍历重载的方法
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ctMethod.getName().equals("zzY0J")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{this.zzZ3l = new java.util.Date(Long.MAX_VALUE);this.zzWSL = com.aspose.words.zzYeQ.zzXgr;zzWiV = this;}");
                }
            }

            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile("C:\\Users\\olivi\\Desktop\\");

            //获取指定的class文件对象
            CtClass zzZJJClassB = ClassPool.getDefault().getCtClass("com.aspose.words.zzYKk");
            //从class对象中解析获取指定的方法
            CtMethod methodB = zzZJJClassB.getDeclaredMethod("zzWy3");
            //替换指定方法的方法体
            methodB.setBody("{return 256;}");
            //这一步就是将破译完的代码放在桌面上
            zzZJJClassB.writeFile("C:\\Users\\olivi\\Desktop\\");
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }

    }
}
