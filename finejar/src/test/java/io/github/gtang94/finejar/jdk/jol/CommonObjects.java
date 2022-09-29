package io.github.gtang94.finejar.jdk.jol;


import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.nio.ByteOrder;

/**
 * 通过JOL（Java Object Layout）工具查看对象在内存中的实际布局
 */
public class CommonObjects {

    @Test
    public void jvmInfo() {
        // 查看字节序列
        System.err.println(
                ByteOrder.nativeOrder()
        );

        // JVM信息
        System.err.println(
                VM.current().details()
        );
    }

    @Test
    public void IntegerLayout() {
        System.err.println(
                ClassLayout.parseClass(Integer.class).toPrintable()
        );

        System.err.println(
                ClassLayout.parseInstance(Integer.MAX_VALUE).toPrintable()
        );
    }

    @Test
    public void StringLayout() {
        System.err.println(
                ClassLayout.parseClass(String.class).toPrintable()
        );

        String str = "string";
        System.err.println(
                ClassLayout.parseInstance(str).toPrintable()
        );
    }
}
