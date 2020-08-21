package brokercore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private final static int MAX_SIZE = 10;
    private static String sentMsg = null;
    private static ArrayBlockingQueue<String> mq = new ArrayBlockingQueue<String>(MAX_SIZE);

    public static void produce(String msg) {
        if (mq.offer(msg)) {
            System.out.println("Message Delivered: " + msg + ", Current Message Queue Size: " + mq.size());
        } else {
            System.out.println("Message Queue Oversized!");
        }
        System.out.println("================");
    }

    public static void blkProduce(String msg) throws InterruptedException {
        mq.put(msg);
    }

    public static String consume() {
        String msg = mq.poll();
        if (msg != null) {
            System.out.println("Message Consumed: " + msg + ", Current Message Queue Size: " + mq.size());
        } else {
            System.out.println("Empty Queue");
        }
        sentMsg = msg;
        System.out.println("================");

        return msg;
    }

    public static String blkConsume() throws InterruptedException {
        String msg = mq.take();
        sentMsg = msg;

        return msg;
    }

    // Simple BufferWriter write consumed message to a text file
    // TODO: protobuf writer
    public static void serialize() throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter("msgfile.txt"));
        wr.write(sentMsg);
        wr.close();

        System.out.println("Message Serialized: " + sentMsg);
        System.out.println("================");
    }
}
