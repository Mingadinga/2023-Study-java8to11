package me.whiteship.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testSingleCallable();


        ExecutorService service = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> min = () -> {
            Thread.sleep(1000L);
            return "Min";
        };

        // invokeAll : 여러 작업 동시에 실행
        // 실행 시간은 동시에 실행한 작업 중에 제일 오래 걸리는 작업이 결정한다
        // Hello, Java, Min 중 Java 실행 시간만큼 걸린다
        List<Future<String>> futures = service.invokeAll(Arrays.asList(hello, java, min));

        for(Future<String> f : futures) {
            System.out.println(f.get());
        }

        // invokeAny : 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기
        // 동시에 실행한 작업 중 제일 짧게 걸리는 작업만큼 시간이 걸린다
        // 블로킹 콜이다 : 실행이 끝날 때까지 대기한다
        // 주의할 것 : 스레드 풀이 부족하면 대기 큐에 들어가서, 기대한 결과가 안 나올 수도 있다(먼저 할당받은 애부터 나올 수도)..
        String s = service.invokeAny(Arrays.asList(hello, java, min));
        System.out.println(s);

        service.shutdown();


    }

    private static void testSingleCallable() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        // 상태값을 반환하는 ExecutorService, Future을 반환한다
        Future<String> submit = service.submit(hello);
        // 상태 확인 - False
        System.out.println(submit.isDone());
        System.out.println("Started!");

        // 취소
        // 파라미터 true : 현재 진행중인 스레드에 인터럽트를 발생시킨다
        // 파라미터 false : 현재 작업이 끝낼 때까지 기다리고 인터럽트를 발생시킨다
        // isDone은 true가 되나, get으로 결과값을 가져오려고 하면 오류가 난다
        submit.cancel(false);

        // 블로킹 콜
        // Callable의 결과값을 가져올 때까지 대기한다
        // get으로 기다리고 있다면 Callable을 사용한다고 해서 빨라지지 않는다
        String helloResult = submit.get();
        System.out.println(helloResult);


        // 상태 확인 - True
        System.out.println(submit.isDone());

        // Callable의 실행이 끝나면 실행한다
        System.out.println("End!");


        service.shutdown();
    }

}
