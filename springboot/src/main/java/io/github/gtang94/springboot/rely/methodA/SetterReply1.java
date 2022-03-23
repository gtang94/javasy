package io.github.gtang94.springboot.rely.methodA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanggq
 * @class ClassA
 * @description
 * @date 2022/2/28
 **/
@Component
public class SetterReply1 {

    @Autowired
    private SetterReply2 b1;
}
