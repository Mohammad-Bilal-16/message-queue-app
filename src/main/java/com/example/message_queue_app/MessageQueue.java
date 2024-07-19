package com.example.message_queue_app;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<Message> queue = new LinkedList<>();

    public synchronized void produce(Message message){
        queue.add(message);
        notifyAll();
    }

    public synchronized Message consume() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }
        return queue.poll();
    }
}
