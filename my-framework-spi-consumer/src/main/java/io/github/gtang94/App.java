package io.github.gtang94;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ServiceLoader<Phone> load = ServiceLoader.load(Phone.class);
        load.forEach(i -> {
            String type = i.getType();
            System.out.println(type);
        });
    }
}
