package me.whiteship.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        // future 기본값과 함께 생성 - new
//        CompletableFuture<String> future = new CompletableFuture<String>();
//        future.complete("hwi");
//        System.out.println(future.get()); // 작업 끝
//
//        // future 기본값과 함께 생성 - static method
//        CompletableFuture<String> future = CompletableFuture.completedFuture("hwi");
//        System.out.println(future.get()); // 작업 끝

        // 스레드 풀 명시적으로 만들기
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 리턴값이 없는 작업을 비동기로 실행하기 : runAsync
        // 매개변수 없고 리턴값 없는 콜백 : thenRun(Runnable)
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                System.out.println("hello" + Thread.currentThread().getName());
                }, executorService).thenRun(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
        voidCompletableFuture.get();

        System.out.println("=========");

        // 리턴값이 있는 작업을 비동기로 실행하기 : supplyAsync
        // 매개변수 있고 리턴값 있는 콜백 : thenApply(Function)
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello" + Thread.currentThread().getName());
            return "hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(stringCompletableFuture.get());

        System.out.println("=========");



        // 리턴값이 있는 작업을 비동기로 실행하기 : supplyAsync
        // 매개변수 있고 리턴값 없는 콜백 : thenAccept(Consumer)
        CompletableFuture<Void> voidFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello" + Thread.currentThread().getName());
            return "hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        System.out.println(voidFuture.get());

        executorService.shutdown();

    }
}
