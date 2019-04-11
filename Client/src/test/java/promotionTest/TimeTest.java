package promotionTest;

import java.time.LocalDateTime;

public class TimeTest {
    public static void main(String[] args) {
        LocalDateTime t1 = LocalDateTime.now().minusDays(30);
        LocalDateTime t2 = LocalDateTime.now();

        System.out.println(t1.compareTo(t2));
    }
}
