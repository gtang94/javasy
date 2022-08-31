package io.github.gtang94.finejar.design.factory;

interface Engine {}

class BmwEngine implements Engine {}
class ChevroletEngine implements Engine {}

interface Tire {}
class BmwTire implements Tire {}
class ChevroletTire implements Tire {}

// 抽象工厂
interface AbstractFacotryInterface {
    Tire createTire();
    Engine createEngine();
}

class BmwFactory implements AbstractFacotryInterface {
    @Override
    public Tire createTire() {
        return new BmwTire();
    }
    @Override
    public Engine createEngine() {
        return new BmwEngine();
    }
}

class ChevroletFactory implements AbstractFacotryInterface {
    @Override
    public Tire createTire() {
        return new ChevroletTire();
    }
    @Override
    public Engine createEngine() {
        return new ChevroletEngine();
    }
}

public class AbstractFactory {

    public static void main(String[] args) {
        AbstractFacotryInterface bmwFatory = new BmwFactory();
        System.err.println(bmwFatory);
    }
}
