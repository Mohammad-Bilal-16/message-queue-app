package com.example.message_queue_app;

import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);

        Producer producer = new Producer(queue , 10);
        Consumer consumer =new Consumer(queue , successCount , errorCount);

        producer.start();
        consumer.start();

        try{
            producer.join();
            consumer.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Total messages processed successfully: " + successCount.get());
        System.out.println("Total errors encountered: " + errorCount.get());
    }
}
