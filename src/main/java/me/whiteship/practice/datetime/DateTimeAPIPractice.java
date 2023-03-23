package me.whiteship.practice.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeAPIPractice {
    public static void main(String[] args) {

        // API 개관

        /* 시간 : 사람용 API, 기계용 API
         기계용 시간 : EPOCK부터 현재까지의 타임스탬프
         사람용 시간 : 연월일시분초 표현
        */

        // 기계용 - 실행 시간 측정에 사용
        Instant instant = Instant.now(); // 기준시 UTC
        System.out.println(instant); // 영국 2023-03-22T22:38:54.108030Z

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone); // Asia/Seoul
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime); // 2023-03-23T07:38:54.108030+09:00[Asia/Seoul]

        // 사람용 : Local, Zone
        LocalDateTime now = LocalDateTime.now(); // 로컬 시스템의 zone 정보로 뜬다
        System.out.println(now); // 서버에 배포한다면 따로 처리를 해야겠다

        LocalDateTime birthDay = LocalDateTime.of(2003, Month.JUNE, 14, 0, 0, 0);
        System.out.println(birthDay);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        // 기계용 Instant <-> 사람용 ZonedDateTime
        Instant nowInstant = Instant.now();
        ZonedDateTime dateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(dateTime); // 2023-03-23T07:49:10.391579+09:00[Asia/Seoul]


        /*
         기간 표현 : 시간 기반, 날짜 기반
         */

        // LocalDate 다루기
        LocalDate.now();
        LocalDate practiceDay = LocalDate.of(2023, Month.MARCH, 23);

        LocalDate.of(2015, Month.FEBRUARY, 20);
        LocalDate.parse("2015-02-20");

        // 덧셈 뺄셈
        // plus, minus 같은 일반적인 연산을 사용할 때는 ChronoUnit으로 단위 명시
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // 파싱
        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        boolean leapYear = LocalDate.now().isLeapYear(); // 윤년


        // LocalTime 다루기
        LocalTime.now();
        LocalTime morning = LocalTime.of(8, 40);
        LocalTime eightFourty = LocalTime.parse("08:40");

        int six = LocalTime.parse("06:30").getHour();
        boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        LocalTime maxTime = LocalTime.MAX;


        // LocalDateTime 다루기
        LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2023, Month.MARCH, 23, 8, 40);
        LocalDateTime localDateTime2 = LocalDateTime.parse("2023-03-23T08:40:00");

        localDateTime1.plusDays(1);
        localDateTime1.plus(1, ChronoUnit.DAYS);
        localDateTime2.minusHours(2);
        localDateTime2.minus(2, ChronoUnit.HOURS);

        System.out.println("===");
        // ZonedDateTime
        ZonedDateTime.of(practiceDay, morning, ZoneId.of("Asia/Seoul"));
        ZonedDateTime.of(localDateTime1, ZoneId.of("Asia/Seoul"));
        // 표준시가 23년 3월 23일 8시 40분일 때 한국 시간
        System.out.println(ZonedDateTime.parse("2023-03-23T08:40:00+00:00[Asia/Seoul]"));
        // 표준시가 23년 3월 23일 8시 40분일 때 1시간 빠른 지역으로부터 한국 시간
        System.out.println(ZonedDateTime.parse("2023-03-23T08:40:00+01:00[Asia/Seoul]"));

        ZoneOffset offset = ZoneOffset.of("+02:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime1, offset);
        System.out.println(offSetByTwo.toLocalDateTime().atZone(ZoneId.of("Asia/Seoul"))); // 2023-03-23T08:40+09:00[Asia/Seoul]


        // 기간 비교
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.AUGUST, 14);
        System.out.println(today); // 2023-03-23

        // 특정 날짜 비교(~와 ~ 사이)
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.toString()); // P4M22D : 0년 4개월 22일 남았음
        System.out.println(period.getYears()); // 0
        System.out.println(period.getMonths()); // 4
        System.out.println(period.getDays()); // 22
        System.out.println(period.get(ChronoUnit.DAYS)); // 22

        // 특정 날짜 비교(~부터 ~까지)
        Period until = today.until(thisYearBirthday);
        System.out.println(until.toString()); // P4M22D : 0년 4개월 22일 남았음
        System.out.println(until.getYears()); // 0
        System.out.println(until.getMonths()); // 4
        System.out.println(until.getDays()); // 22
        System.out.println(until.get(ChronoUnit.DAYS)); // 22

        // Instant 기계용 시간 비교
        Instant instant1 = Instant.now();
        Instant plus = instant1.plus(10, ChronoUnit.SECONDS); // immutable!
        Duration duration = Duration.between(instant1, plus);
        System.out.println(instant1.getEpochSecond()); // 1679526620
        System.out.println(plus.getEpochSecond()); // 1679526630
        System.out.println(duration.getSeconds()); // 10

        // 특정 날짜가 날짜 범위에 속하는지 확인
        LocalDate date1 = LocalDate.of(2023, 2, 1);
        LocalDate date2 = LocalDate.of(2023, 3, 21);
        LocalDate date3 = LocalDate.of(2023, 4, 1);

        System.out.println(isWithinOneMonthRange(date1));  // false
        System.out.println(isWithinOneMonthRange(date2));  // true
        System.out.println(isWithinOneMonthRange(date3));  // false


        /* 포매팅 : LocalDateTime -> 문자열 */
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2023-03-23T09:22:27.275695

        DateTimeFormatter mmddyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(localDateTime.format(mmddyy));

        /* 파싱 : 문자열 -> LocalDateTime */
        LocalDate parse = LocalDate.parse("07/15/1982", mmddyy);
        System.out.println(parse);


        /* 레거시(Date) 호환 */
        Date date = new Date();
        Instant instant2 = date.toInstant();
        Date newDate = Date.from(instant2);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime1 = gregorianCalendar.toInstant().atZone(
                ZoneId.systemDefault()).toLocalDateTime();

        ZonedDateTime zonedDateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime1);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);


    }

    public static boolean isWithinOneMonthRange(LocalDate dateToCheck) {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthFromNow = now.plusMonths(1);

        return !dateToCheck.isBefore(now) && !dateToCheck.isAfter(oneMonthFromNow);
    }
}
