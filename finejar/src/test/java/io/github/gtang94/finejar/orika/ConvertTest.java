package io.github.gtang94.finejar.orika;

import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class ConvertTest {

    private static final MapperFactory mapperFactory;
    private static final MapperFacade mapperFacade;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).mapNulls(true).build();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public void orikaCopy() {
        // init source bean
        A a = initA();

        // copy
        X x = mapperFacade.map(a, X.class);
        System.err.println(a);
        System.err.println(x);

//        mapperFactory.classMap(A.class, X.class)
//                .field("bList", "yList")
//                .byDefault().register();
//
//        X x = mapperFacade.map(a, X.class);
//        System.err.println(a);
//        System.err.println(x);
    }

    public void mapStructCopy() {
        A a = initA();

        X x = AMapper.INSTANCE.aToX(a);
        System.err.println(a);
        System.err.println(x);

//        B b = initB();
//        Y y = BMapper.INSTANCE.bToY(b);
//        System.err.println(b.toString());
//        System.err.println(y.toString());
    }


    public static void main(String[] args) {
        ConvertTest convertTest = new ConvertTest();

//        convertTest.orikaCopy();

        convertTest.mapStructCopy();
    }

    public A initA() {
        List<B> bList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            B b = new B();
            b.setId(i + 10);

            List<C> cList = Lists.newArrayList();
            for (int j = 1; j < 10; j++) {
                C c = new C();
                c.setId(i + j);
                cList.add(c);
            }

            b.setCList(cList);

            bList.add(b);
        }
        A a = new A();
        a.setId(0);
        a.setBList(bList);

        return a;
    }

    public B initB() {
        B b = new B();
        b.setId(0);
        List<C> cList = Lists.newArrayList();
        for (int j = 1; j < 10; j++) {
            C c = new C();
            c.setId(j);
            cList.add(c);
        }
        b.setCList(cList);


        return b;
    }

}