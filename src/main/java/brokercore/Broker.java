package brokercore;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private final static int MAX_SIZE = 3;
    private static ArrayBlockingQueue<String> mq = new ArrayBlockingQueue<String>(MAX_SIZE);

    public static void produce(String msg) {
        if (mq.offer(msg)) {
            System.out.println("Successfully Delivered Message: " + msg +
                    ", Current Message Queue Size: " + mq.size());
        } else {
            System.out.println("Message Queue Oversized!");
        }
        System.out.println("================");
    }

    public static String consume() {
        String msg = mq.poll();
        if (msg != null) {
            System.out.println("Message Consumed: " + msg + ", Current Message Queue Size: " + mq.size());
        } else {
            System.out.println("Empty Queue");
        }

        System.out.println("================");
        return msg;
    }
}
