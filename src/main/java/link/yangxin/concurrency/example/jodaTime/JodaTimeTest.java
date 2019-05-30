package link.yangxin.concurrency.example.jodaTime;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

/**
 * @author yangxin
 * @date 2019/5/29
 */
public class JodaTimeTest {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toDate());
        dateTime.plusDays(1);// 明天
        dateTime.minusDays(1);//昨天
        dateTime.plusMonths(1);//下个月
        dateTime.minusMonths(1);//一个月前
        dateTime.toString("yyyy-MM-dd");// 格式化
        // 获取年月日时分秒
        dateTime.getYear();
        dateTime.getMonthOfYear();
        dateTime.getDayOfMonth();
        dateTime.getDayOfWeek();//周几
        //DateTimeConstants.SUNDAY;
        // 月末日期
        DateTime lastday = dateTime.dayOfMonth().withMaximumValue();

        //90天后那周的周一
        DateTime firstday = dateTime.plusDays(90).dayOfWeek().withMinimumValue();

        // 计算区间
        //计算区间毫秒数
        DateTime begin = new DateTime("2012-02-01");
        DateTime end = new DateTime("2012-05-01");
        Duration d = new Duration(begin, end);
        long time = d.getMillis();

        // 计算区间天数
        Period p = new Period(begin, end, PeriodType.days());
        int days = p.getDays();

        //计算特定日期是否在该区间内
        Interval i = new Interval(begin, end);
        boolean contained = i.contains(new DateTime("2012-03-01"));

        // 日期比较
        DateTime d1 = new DateTime("2012-02-01");
        DateTime d2 = new DateTime("2012-05-01");

        //和系统时间比
        boolean b1 = d1.isAfterNow();
        boolean b2 = d1.isBeforeNow();
        boolean b3 = d1.isEqualNow();

        //和其他日期比
        boolean f1 = d1.isAfter(d2);

        // 格式化输出
        String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa");
        String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        DateTime.parse("20190529", DateTimeFormat.forPattern("yyyyMMdd"));

        //下面的代码将计算五年后的第二个月的最后一天：
        DateTime now = DateTime.now();
        DateTime then = now.minusYears(5) // five years ago
                .monthOfYear() // get monthOfYear property
                .setCopy(2) // set it to February
                .dayOfMonth() // get dayOfMonth property
                .withMaximumValue();// the last day of the month
        System.out.println(then);
    }

}