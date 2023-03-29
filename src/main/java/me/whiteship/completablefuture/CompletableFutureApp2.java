package me.whiteship.completablefuture;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureApp2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 두 future의 연관관계가 있는 경우 : thenCompose
        testRelatedFutures();
        System.out.println("=========");

        // 두 future의 연관관계가 없는 경우 : thenCombine
        testUnrelatedFutures();
        System.out.println("=========");

        // 두개 이상의 future 조합하기
        // allOf로 넘긴 모든 Future의 작업이 끝나면 결과값 사용
        // 그런데 Future의 타입이 일치하지 않을 수도 있고, 서로 다른 예외를 던질수도 있음
        CompletableFuture.allOf(getHello(), getWorld())
                .thenAccept(System.out::println); // null

        // future들의 반환값을 Collection으로 받기
        testAllOf();
        System.out.println("=========");

        // 여러개 중에 가장 먼저 끝나는 것 받아서 반환
        testAnyOf();
        System.out.println("=========");

        // 예외 처리
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) { // 예외 발생
                throw new IllegalStateException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> "Error!"); // 에러 타입을 받아서 리턴하는 Function 정의

        System.out.println(hello.get());


        // 정상 종료 및 예외 처리
        CompletableFuture<String> helloHandle = CompletableFuture.supplyAsync(() -> {
            if (throwError) { // 예외 발생
                throw new IllegalStateException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error!";
            }
            return result;
        }); // 에러 타입을 받아서 리턴하는 Function 정의

        System.out.println(hello.get());


    }

    private static void testAnyOf() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.anyOf(getHello(), getWorld()).thenAccept(System.out::println);
        System.out.println("future.get() = " + future.get());
    }

    private static void testAllOf() throws InterruptedException, ExecutionException {
        List<CompletableFuture> futures = Arrays.asList(getHello(), getWorld());
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join) // get 대신 런타임 예외 던지는 join
                            .collect(Collectors.toList()));
        results.get().forEach(System.out::println);
    }

    private static void testUnrelatedFutures() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = getHello();
        CompletableFuture<String> world = getWorld();

        // future1.thenCombine(future2, BiFunction(f1result, f2result)) ->
        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });
        System.out.println(future.get());
    }

    private static void testRelatedFutures() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = getHello().thenCompose(CompletableFutureApp2::getWorldMessage);
        System.out.println("future.get() = " + future.get());
    }

    private static CompletableFuture<String> getHello() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        return hello;
    }

    private static CompletableFuture<String> getWorldMessage(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + "World";
        });
    }

    private static CompletableFuture<String> getWorld() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });
    }
}
