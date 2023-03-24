package me.whiteship.completablefuture;

public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

        });
        thread.start();
        System.out.println("Hello: " + Thread.currentThread().getName());
        try {
            thread.join(); // Thread의 종료 기다리기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread + " is finished");
    }
    // 지금 쓰레드 두개(main, thread)인데도 이렇게 인터럽트가 많은데
    // 쓰레드 많아지면 너무 너무 복잡함!!
    // -> Executors 사용
}
