package com.example.message_queue_app;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread{

    private MessageQueue queue;
    private AtomicInteger successCount;
    private AtomicInteger errorCount;

    public Consumer(MessageQueue queue, AtomicInteger successCount, AtomicInteger errorCount) {
        this.queue = queue;
        this.successCount = successCount;
        this.errorCount = errorCount;
    }

    @Override
    public void run() {
        while (true) {
            try{
                Message message = queue.consume();
                System.out.println("Consumed:- " + message.getContent());
                successCount.incrementAndGet();
            }catch (Exception e){
                errorCount.incrementAndGet();
                System.err.println("Error consuming message:- " + e.getMessage());
            }
        }
    }
}
