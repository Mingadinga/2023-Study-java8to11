package me.whiteship.completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApp {
    public static void main(String[] args) {
        // 스레드 하나만 사용하는 Executors 사용
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // execute : 나중에 명령(Runnable)을 실행합니다.
        // 명령은 실행자 구현의 재량에 따라 새 스레드, 풀링된 스레드 또는 호출 스레드에서 실행될 수 있습니다.
        executorService.execute(getRunnable("Hello"));

        // ExecutorService는 다음 작업이 들어올 때까지 대기하므로 프로세스가 죽지 않아서 셧다운이 필요하다
        // Graceful Shutdown : 하던 일을 모두 마치고 죽인다
        executorService.shutdown();
//        executorService.shutdownNow(); // 바로 죽인다

        // 2개의 고정 풀을 가진 Executors를 받아서 5개의 Runnable을 할당했다
        // Executor 내부에 Thread Pool과 Blocking Queue(대기 큐)가 있다
        // 실행해야할 작업이 풀 개수보다 많으면 대기 큐에서 대기시키다가 풀 받은 스레드 실행이 끝나면 풀에 할당한다
        // 풀 개수를 고정하면 그렇지 않을 떄보다 스레드 생성 비용이 덜 드는 장점이 있다
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        // submit : executor와 마찬가지로 Runnable을 받아서 스레드에게 할당
        executorService2.submit(getRunnable("Hello"));
        executorService2.submit(getRunnable("Min"));
        executorService2.submit(getRunnable("Hwi"));
        executorService2.submit(getRunnable("Yoon"));
        executorService2.submit(getRunnable("Seeun"));
        executorService2.shutdown();

        System.out.println("=======ScheduledExecutorService=======");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 3초 delay 후에 실행. 참고로 schedule은 매개변수로 Runnable 혹은 Callable을 받는다.
        scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        // 1초 delay 후 2초에 한번씩 실행
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);

        // fork join 프레임워크라는 것도 있다
        // 얘는 멀티 프로세스 프로그래밍에서 활용

        // 스레드에 작업을 할당하고 리턴 값을 가져와서 활용하고 싶다면,
        // void 타입인 Runnable이 아니라 V 타입인 Callable을 사용한다
        // Callable의 리턴 값을 받아오는 오브젝트가 바로 Future이다 !



    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
