package io.github.gtang94.finejar.jdk.map;


import java.util.HashMap;
import java.util.Objects;

public class HashCodePrinciple {

    static class Key {
        Integer index;

        public Integer getIndex() {
            return index;
        }

        public Key(int index) {
            this.index = index;
        }

        /**
         * 如果不重写hashCode和equals方法时，会默认用Object类的这两个方法。
         * Object类的hashCode返回引用地址的地址
         * Object类的equals方法比较引用地址是否相同
         */
        public int hashCode() {
            return index.hashCode();
        }

        public boolean equals(Object obj) {
            if (null == obj || !(obj instanceof Key)) {
                return false;
            } else {
                return this.getIndex().equals(((Key) obj).index);
            }
        }
    }

    public static void main(String[] args) {

        Key k1 = new Key(100);
        Key k2 = new Key(100);
        HashMap<Key, String> hashMap = new HashMap<>();
        hashMap.put(k1, "100");
        System.out.println(hashMap.get(k2));
    }
}
