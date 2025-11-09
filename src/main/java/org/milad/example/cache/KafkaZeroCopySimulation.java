package org.milad.example.cache;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;

public class KafkaZeroCopySimulation {

    private static final String FILE_PATH = "kafka_log_segment.data";
    private static final int MESSAGE_COUNT = 1_000_000;
    private static final String MESSAGE = "Kafka simulation message - ";

    public static void main(String[] args) throws Exception {
        createSequentialLog();       // Step 1: Producer simulation
        normalCopyRead();            // Step 2: Traditional I/O
        zeroCopyRead();              // Step 3: Zero-copy I/O
        zeroCopyRead();              // Step 4: Zero-copy I/O
        zeroCopyRead();              // Step 5: Zero-copy I/O
        normalCopyRead();            // Step 6: Traditional I/O
    }

    // Step 1: Simulate producer writing messages sequentially to disk
    private static void createSequentialLog() throws IOException {
        System.out.println("Creating simulated Kafka log segment...");
        long start = System.currentTimeMillis();

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             FileChannel channel = fos.getChannel()) {

            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String msg = MESSAGE + i + "\n";
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                channel.write(buffer);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Sequential write completed in: " + (end - start) + " ms\n");
    }

    // Step 2: Normal read (user-space copy)
    private static void normalCopyRead() throws IOException {
        System.out.println("Reading file using normal I/O...");
        long start = System.currentTimeMillis();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_PATH));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("normal_copy.out"))) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Normal copy took: " + (end - start) + " ms\n");
    }

    // Step 3: Zero-copy read using FileChannel.transferTo()
    private static void zeroCopyRead() throws IOException {
        System.out.println("Reading file using zero-copy (transferTo)...");
        long start = System.currentTimeMillis();

        try (FileChannel sourceChannel = new FileInputStream(FILE_PATH).getChannel();
             WritableByteChannel targetChannel = Channels.newChannel(new FileOutputStream("zero_copy.out"))) {

            long size = sourceChannel.size();
            long position = 0;
            while (position < size) {
                position += sourceChannel.transferTo(position, size - position, targetChannel);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Zero-copy took: " + (end - start) + " ms\n");
    }
}
