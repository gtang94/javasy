package io.github.gtang94.finejar.design.factory;

interface Car {
}

class Byd implements Car {
}

class Tsl implements Car {
}

// 简单工厂
public class SimpleFactory {
    public static Car getCar(String type) {
        if ("byd".equals(type)) {
            return new Byd();
        } else if ("tsl".equals(type)) {
            return new Tsl();
        }
        return null;
    }

    public static void main(String[] args) {
        Car byd = SimpleFactory.getCar("byd");
        Car tsl = SimpleFactory.getCar("tsl");
        System.err.println(byd);
        System.err.println(tsl);
    }
}
