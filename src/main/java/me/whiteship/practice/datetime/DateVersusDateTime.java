package me.whiteship.practice.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DateVersusDateTime {
    public static void main(String[] args) {

        // Clear 비교
        testClearancy();

        // Fluency 비교
        testFluency();

        // Immutable 비교
        testImmutable();

        // 사용자 정의 time adjuster 만들기
        // 현재 날짜에서 다음 달 첫 번째 평일(월~금)
        LocalDate today = LocalDate.now();
        LocalDate nextMonthFirstWeekday = today.with(new NextMonthFirstWeekdayAdjuster());
        System.out.println("다음 달 첫 번째 평일: " + nextMonthFirstWeekday);

    }

    private static void testClearancy() {
        // 3월인데 왜 2임? 날짜는 1로 시작하면서
        Date confusingDate = new Date(2022, 2, 21); // 2022년 3월 21일
        System.out.println(confusingDate);

        // 편-안
        LocalDate clearDate = LocalDate.of(2022, 3, 21); // 2022년 3월 21일
        System.out.println(clearDate);
    }

    private static void testFluency() {
        // Date나 Calendar는 불변 객체가 아니므로 매번 새로운 객체를 만들어야함
        // 메소드 체인을 사용하려면 set이나 get 같은 접두사를 붙여서 한줄씩 써야함
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime(); // 현재 날짜와 시간을 가져옴
        calendar.setTime(date); // calendar 객체의 날짜와 시간을 현재 시간으로 설정

        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, 2);
        calendar.add(Calendar.DATE, 3);

        date = calendar.getTime(); // 1년 2개월 3일 뒤의 날짜와 시간을 가져옴
        System.out.println(date);


        // 불변 객체이므로 static 메소드로 가져올 수 있고
        // 메소드 체이닝 사용 가능
        LocalDate fluentDate = LocalDate.now()
                .plusYears(1)
                .plusMonths(2)
                .plusDays(3); // 현재 날짜로부터 1년 2개월 3일 뒤의 날짜
        System.out.println(date);
    }

    private static void testImmutable() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Date mutableDate = new Date();
        LocalDateTime immutableDate = LocalDateTime.now();

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                // Date 클래스를 사용하여 Mutable한 객체를 공유하여 사용할 때 예기치 않은 결과 발생 가능
                synchronized (mutableDate) {
                    mutableDate.setTime(System.currentTimeMillis());
                    System.out.println(mutableDate);
                }

                // LocalDateTime 클래스를 사용하여 Immutable한 객체를 공유하여 사용할 때 Thread-safe함
                LocalDateTime now = LocalDateTime.now();
                System.out.println(now);
                LocalDateTime newLocalDateTime = immutableDate.plusMinutes(1);
                System.out.println(newLocalDateTime);
            });
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
