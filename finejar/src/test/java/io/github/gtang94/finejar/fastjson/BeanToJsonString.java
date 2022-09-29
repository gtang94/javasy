package io.github.gtang94.finejar.fastjson;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * fastjson将对象转化为json字符串时，是通过反射拿到所有方法，再遍历过滤出所有的getter方法来获取属性的。
 * 所以如果方法名字是get开头的，就会产生死循环，造成栈溢出
 */
public class BeanToJsonString {

    private int gold = 14;
    private BigDecimal discount = BigDecimal.valueOf(10);
    private int flipTime;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getFlipTime() {
        return flipTime;
    }

    public void setFlipTime(int flipTime) {
        this.flipTime = flipTime;
    }

    public String getJsonString() {
//    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        BeanToJsonString bean = new BeanToJsonString();
        System.err.println(
                bean.getJsonString()
//                bean.toJsonString()
        );
    }
}
