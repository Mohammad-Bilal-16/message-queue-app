package com.example.message_queue_app;

public class Producer extends Thread{
    private MessageQueue queue;
    private int messageCount;

    public Producer(MessageQueue queue , int messageCount){
        this.queue = queue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        for(int i = 0 ; i <= messageCount; i++){
            Message message = new Message("Message" + i);
            queue.produce(message);
            System.out.println("Produced:- " + message.getContent());
        }
    }
}
