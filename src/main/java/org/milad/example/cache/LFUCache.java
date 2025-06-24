package org.milad.example.cache;

import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<V>> valueMap = new HashMap<>();
    private final Map<Integer, LinkedHashSet<K>> freqMap = new HashMap<>();
    private int minFreq = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private static void memoryUsage() {
        LFUCache<Integer, Integer> cache = new LFUCache<>(100_000);

        for (int i = 0; i < 100_000; i++) {
            cache.put(i, i);
        }

        // Measure deep size including referenced objects
        System.out.println("Deep size of cache in bytes: " + GraphLayout.parseInstance(cache).totalSize());
    }


    public static void benchmarkLFUCache() {
        int capacity = 1000;
        LFUCache<Integer, Integer> cache = new LFUCache<>(capacity);

        int operations = 1_000_000;

        long start = System.nanoTime();

        // Mix of put and get
        for (int i = 0; i < operations; i++) {
            int key = i % capacity;
            cache.put(key, i);
            cache.get(key);
        }

        long end = System.nanoTime();

        double elapsedMs = (end - start) / 1_000_000.0;
        System.out.printf("Performed %,d put/get operations in %.2f ms%n", operations * 2, elapsedMs);
    }

    public static void benchmarkConcurrentLFUCache() throws InterruptedException {
        int capacity = 1000;
        LFUCache<Integer, Integer> cache = new LFUCache<>(capacity);

        int threadsCount = 4;
        int opsPerThread = 250_000;

        Runnable task = () -> {
            for (int i = 0; i < opsPerThread; i++) {
                int key = i % capacity;
                cache.put(key, i);
                cache.get(key);
            }
        };

        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(task, "T" + i);
        }

        long start = System.nanoTime();

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        long end = System.nanoTime();

        double elapsedMs = (end - start) / 1_000_000.0;
        System.out.printf("Performed %,d put/get operations concurrently in %.2f ms%n", threadsCount * opsPerThread * 2, elapsedMs);
    }

    public synchronized V get(K key) {
        if (!valueMap.containsKey(key)) return null;
        updateFreq(key);
        return valueMap.get(key).value;
    }

    public synchronized void put(K key, V value) {
        if (capacity == 0) return;

        if (valueMap.containsKey(key)) {
            valueMap.get(key).value = value;
            updateFreq(key);
            return;
        }

        if (valueMap.size() >= capacity) {
            evict();
        }

        valueMap.put(key, new Node<>(value));
        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;


    }

    private void updateFreq(K key) {

        Node<V> node = valueMap.get(key);
        int oldFreq = node.freq;
        node.freq++;

        LinkedHashSet<K> oldSet = freqMap.get(oldFreq);
        oldSet.remove(key);
        if (oldSet.isEmpty()) {
            freqMap.remove(oldFreq);
            if (oldFreq == minFreq) minFreq++;
        }
        freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(key);


    }

    private void evict() {
        LinkedHashSet<K> keys = freqMap.get(minFreq);
        K keyToRemove = keys.getFirst();
        keys.remove(keyToRemove);
        if (keys.isEmpty()) {
            freqMap.remove(minFreq);
        }
        valueMap.remove(keyToRemove);
    }

    private static class Node<V> {
        V value;
        int freq;

        Node(V value) {
            this.value = value;
            this.freq = 1;
        }
    }

    public static class TestLFU {
        public static void main(String[] args) throws InterruptedException {
            memoryUsage();
            benchmarkLFUCache();
            benchmarkConcurrentLFUCache();
        }
    }
}
