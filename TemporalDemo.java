import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class TemporalDemo {
    public static void main(String[] args) {

        LocalDate date= LocalDate.now();
        System.out.println("Today is :"+ date);

        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("Next Monday is :" +nextMonday);



    }
}
