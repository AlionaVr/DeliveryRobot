package org.example;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final int ROUTES_COUNT = 1000;
    static final char TARGET_CHAR = 'R';
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        ExecutorService executor = Executors.newFixedThreadPool(ROUTES_COUNT);
        for (int i = 0; i < ROUTES_COUNT; i++) {
            executor.submit(() -> {
                String route = generateRoute("RLRFR", 100);
                int count = countChar(route);
                System.out.println("Количество поворотов направо (R): " + count);

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(count)) {
                        sizeToFreq.put(count, sizeToFreq.get(count) + 1);
                    } else {
                        sizeToFreq.put(count, 1);
                    }
                }
            });
        }
        executor.shutdown();
        findFrequencyAndShowStatistics();
    }

    private static int countChar(String route) {
        int count = 0;
        for (int i = 0; i < route.length(); i++) {
            if (route.charAt(i) == Main.TARGET_CHAR) {
                count++;
            }
        }
        return count;
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    private static void findFrequencyAndShowStatistics() {
        int mostFrequentKey = sizeToFreq.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        int mostFrequentValue = sizeToFreq.get(mostFrequentKey);

        System.out.println("Самое частое количество повторений " + mostFrequentKey + " (встретилось " + mostFrequentValue + " раз)");
        System.out.println("Другие размеры:");

        sizeToFreq.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != mostFrequentKey)
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .forEach(entry ->
                        System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)")
                );
    }
}

