package com.example.message_queue_app;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testSuccessfulMessageProcessing() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);

        Producer producer = new Producer(queue, 5);
        Consumer consumer = new Consumer(queue, successCount, errorCount);

        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();

        assertEquals(5, successCount.get());
        assertEquals(0, errorCount.get());
    }

    @Test
    public void testErrorMessageProcessing() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);

        Consumer consumer = new Consumer(queue, successCount, errorCount);
        consumer.start();

        consumer.interrupt();

        consumer.join();

        assertEquals(0, successCount.get());
        assertEquals(1, errorCount.get());
    }
}
