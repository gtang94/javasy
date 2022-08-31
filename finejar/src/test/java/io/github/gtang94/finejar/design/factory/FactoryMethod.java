package io.github.gtang94.finejar.design.factory;

class Benz implements Car {
}

class Toyota implements Car {
}

interface CarFactory<T extends Car> {
    T getCar();
}

class BenzFactory implements CarFactory<Benz> {
    @Override
    public Benz getCar() {
        return new Benz();
    }
}

class ToyotaFactory implements CarFactory<Toyota> {
    @Override
    public Toyota getCar() {
        return new Toyota();
    }
}

// 工厂方法
public class FactoryMethod {

    public static void main(String[] args) {
        Benz benz = new BenzFactory().getCar();
        Toyota toyota = new ToyotaFactory().getCar();
        System.err.println(benz);
        System.err.println(toyota);
    }
}
