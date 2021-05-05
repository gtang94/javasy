package io.github.gtang94.finejar.hutool;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

/**
 * @class: HttpOperation
 * @description:
 * @author: tanggq
 * @date: 5/2/21
 * @version: 1.0
 */
public class HttpOperation {

    @Test
    public void checkHttpGet() {
        String url = "https://restapi.amap.com/v3/geocode/regeo?output=JSON&location=116.39,39.9&key=d17951186585776d652c1e7e8dfc4ba8&radius=0&extensions=base";
        String res = HttpUtil.get(url);
        System.err.println(res);
    }
}
