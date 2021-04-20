package io.github.gtang94.finejar.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * @author tanggq
 * @class CollectionOpration
 * @description 集合操作
 * @date 2021/4/20
 **/
public class CollectionOpration {

    /**
     * @description: 创建不可变的集合
     * @return: null
     * @throws:
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void createImmutableCollection() {
        // builder 创建
        ImmutableList<Object> list = ImmutableList.builder().add("1111").add("2222").build();
        System.out.println(list.toString());

        // of创建
        ImmutableSet<String> set1 = ImmutableSet.of("aaaa", "bbb");
        System.out.println(set1.toString());

        // copyOf创建
        ArrayList arrayList = new ArrayList();
        arrayList.add("000000");
        arrayList.add("8888888888");
        ImmutableSet<String> set2 = ImmutableSet.copyOf(arrayList);
        System.out.println(set2.toString());

        list.add("33333");
        System.out.println(list.toString());
    }

    /**
     * @description: 使用Guava提供的集合工厂创建集合
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void createCollections() {
        // 创建空集合
        List<String> list1 = Lists.newArrayList();

        // 创建集合并插入数据
        List<String> list2 = Lists.newArrayList("111", "222", "333");

        // 初始化容量为100的集合
        List<String> list3 = Lists.newArrayListWithCapacity(100);

        // 创建空的 hash map
        HashMap<Object, Object> hashMap = Maps.newHashMap();

        // 创建空的 hash set
        HashSet<Object> hashSet = Sets.newHashSet();

        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
        System.out.println(hashMap.toString());
        System.out.println(hashSet.toString());
    }

    /**
     * @description: 集合之间的运算（交、并、差集）
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void collectionArithmetic() {
        Set<String> set1 = Sets.newHashSet("a", "b", "c", "d");
        Set<String> set2 = Sets.newHashSet("c", "d", "e", "f");

        // 交集
        Sets.SetView<String> intersectionSet = Sets.intersection(set1, set2);
        System.out.println(intersectionSet.toString());

        // 并集
        Sets.SetView<String> unionSet = Sets.union(set1, set2);
        System.out.println(unionSet.toString());

        // 只在set1中有，在set2中没有的元素（差集）
        Sets.SetView<String> set = Sets.difference(set1, set2);
        System.out.println(set.toString());
    }

    /**
     * @description: 集合中元素数量计算
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void collectionCount() {

        // JDK
        List<String> words = Lists.newArrayList("a", "a", "b", "c", "c", "c");
        Map<String, Integer> coutMap = new HashMap<>();
        for (String word: words) {
            Integer cnt = coutMap.get(word);
            cnt = (cnt == null) ? 1 : ++cnt;
            coutMap.put(word, cnt);
        }
        coutMap.forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println();

        // Guava
        HashMultiset<String> multiset = HashMultiset.create(words);
        multiset.elementSet().forEach(s->System.out.println(s + ":" + multiset.count(s)));
    }

    /**
     * @description: 复杂集合嵌套
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void complexCollectionNest() {
        // JDK
        HashMap<String, Set<String>> animalMap = new HashMap<>();
        HashSet<String> dogSet = new HashSet<>();
        dogSet.add("旺财");
        dogSet.add("大黄");
        animalMap.put("狗", dogSet);
        HashSet<String> catSet = new HashSet<>();
        catSet.add("加菲");
        catSet.add("汤姆");
        animalMap.put("猫", catSet);
        System.out.println(animalMap.get("猫"));

        // Guava
        HashMultimap<String, String> multimap = HashMultimap.create();
        multimap.put("狗", "大黄");
        multimap.put("狗", "旺财");
        multimap.put("猫", "加菲");
        multimap.put("猫", "汤姆");
        System.out.println(multimap.get("猫"));
    }

}
