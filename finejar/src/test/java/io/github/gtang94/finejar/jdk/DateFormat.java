package io.github.gtang94.finejar.jdk;

import org.junit.Test;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author tanggq
 * @class DateFormat
 * @description JDK日期时间转换，JDK1.8前 及 JDK1.8后操作
 * @date 2021/4/25
 **/
public class DateFormat {

    /**
     * @description JDK1.8及之后的时间类
     * @return: null
     * @author tanggq
     * @date 2021/4/25
     **/
    @Test
    public void DateAfter18() {
        // 获取当前日期
        LocalDate localDate = LocalDate.now();
//        LocalDate localDate = LocalDate.of(9012, 12, 1); // 或用of方式指定具体年月日创建
        System.err.println(localDate);

        // 获取当前时间
        LocalTime localTime = LocalTime.now();
//        LocalTime localTime = LocalTime.of(20, 12, 23); // 或用of方式指定具体时分秒创建
        System.err.println(localTime);

        // 获取当前日期及时间
        LocalDateTime localDateTime = localDate.atTime(localTime);
        System.err.println(localDateTime);

        // 获取月
        System.err.println(localDate.getMonth());
        // 获取天
        System.err.println(localDate.getDayOfMonth());

        // 获取时间戳（纳米级）
        Instant instant = Instant.now();
        System.err.println(instant);

        // Instant 表示时间戳， Duration表示时间段，所以Duration不能用now()静态方法创建
        Duration duration = Duration.between(
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2021, 12, 31, 23, 59, 59)
        );
        System.err.println( duration.toDays() ); // 获取这段时间的总天数
        System.err.println( duration.toHours() ); // 获取这段时间的总小时数
        System.err.println( duration.toMinutes() ); // 获取这段时间的总分钟数

        // Period 和 Duration 类似，也表示时间段，但 Duration 以秒计算；Period以年月日计算
        Period period = Period.of(2, 3, 6);
        System.err.println(period.getYears());
    }

    /**
     * @description  JDK1.8及之后的时间类的操作及格式化
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void DateFormatAfter18() {
        LocalDate localDate = LocalDate.now();

        // 修改年，并返回新的LocalDate类
        System.err.println(localDate.withYear(2019));
        // 增加2年，并返回新的LocalDate类
        System.err.println(localDate.plusYears(2));
        System.err.println(localDate.minusDays(1).toString());

        // 返回一下距离当前时间最近的周六
        System.err.println(localDate.with(nextOrSame(DayOfWeek.SATURDAY)));
        // 返回本月最后一个星期日
        System.err.println(localDate.with(lastInMonth(DayOfWeek.SUNDAY)));

        LocalDateTime localDateTime = LocalDateTime.now();
        // 20111203 格式 格式化日期
        System.err.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
        // 2021-04-25 格式 格式化日期
        System.err.println(localDate.format(DateTimeFormatter.ISO_DATE));
    }
}
