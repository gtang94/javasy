package io.github.gtang94.springboot.rely.methodA;

import io.github.gtang94.springboot.rely.methodA.SetterReply1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanggq
 * @class ClassB
 * @description
 * @date 2022/2/28
 **/
@Component
public class SetterReply2 {

    @Autowired
    private SetterReply1 a1;
}
