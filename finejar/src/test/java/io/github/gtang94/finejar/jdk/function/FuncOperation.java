package io.github.gtang94.finejar.jdk.function;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tanggq
 * @class FuncOperation
 * @description
 * @date 2021/6/22
 **/
public class FuncOperation {

    @Test
    public void funcInterfaceTest() {
        TestFuncInterface testFuncInterface = str -> System.err.println(str);
    }

    @Test
    public void consumerTest() {
        Consumer<String> consumer = (str) -> System.err.println("2333 " + str);
        Stream stream = Stream.of("aaa", "bbb", "ccc");
        stream.forEach(consumer);
    }

    @Test
    public void functinTest() {
        Function<Integer, Integer> function = (a) -> {return a+2;};
        Stream stream = Stream.of(1, 2, 3, 4, 5);
        stream.map(function).forEach( (value) -> System.err.println(value) );
    }

    @Test
    public void predicateTest() {
        Predicate predicate = (a) -> a.equals("233");
        List<String> list = Lists.newArrayList("aa", "bb", "cc", "233");
        boolean b = list.stream().allMatch(predicate); // 所有的元素都匹配是返回true，否则返回false
        boolean c = list.stream().anyMatch(predicate); // 任何一个匹配即返回true
        System.err.println(b);
        System.err.println(c);

        list.stream().filter(predicate).forEach(value -> System.err.println(value)); //过滤
    }

    @Test
    public void supplierTest() {
        Supplier supplier = () -> {return Lists.newArrayList(1, 2, 3, 4, 5);};
        Stream.generate(supplier).forEach(value -> System.err.println(value));
    }
}
