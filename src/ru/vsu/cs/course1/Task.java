package ru.vsu.cs.course1;


import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


public class Task {
    public static void sortQueue(Queue<Integer> q) {
        if (q.size() == 0) {
            return;
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            int tmp = q.poll();
            for (int i = 0; i < q.size(); i++) {
                if (tmp < q.peek()) {
                    q.add(tmp);
                    tmp = q.poll();
                } else {
                    q.add(q.poll());
                    isSorted = false;
                }
            }
            q.add(tmp);
        }
    }
    public static void sort(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            q.add(arr[i]);
        }

        sortQueue(q);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = q.poll();
        }
    }
}
