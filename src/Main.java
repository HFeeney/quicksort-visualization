import processing.core.PApplet;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main extends PApplet {

    static final int SIZE = 100;
    static final int[] arr = new int[SIZE];
    static final int WINDOW_SIZE = 600;

    static final float ARRAY_WIDTH = (float) WINDOW_SIZE * 4 / 5;
    static final float ARRAY_POS_X = (float) WINDOW_SIZE / 2 - ARRAY_WIDTH / 2;
    static final float ARRAY_POS_Y = (float) WINDOW_SIZE * 9 / 10;

    static final Object ARRAY_LOCK = new Object();
    static final ForkJoinPool POOL = new ForkJoinPool();

    public void settings() {
        size(WINDOW_SIZE, WINDOW_SIZE);
    }

    public void setup() {
        // create randomized array
        List<Integer> elements = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            elements.add(i);
        }
        Collections.shuffle(elements);
        for (int i = 0; i < elements.size(); i++) {
            arr[i] = elements.get(i);
        }


        // change color mode
        colorMode(HSB);
        noStroke();

        new Thread(() -> POOL.invoke(new QuickSort(arr, 0, arr.length, ARRAY_LOCK))).start();
    }

    public void draw() {
        background(0, 0, 255);

        for (int i = 0; i < arr.length; i++) {
            fill((float) arr[i] * 255 / SIZE, 255, 255);
            int rectHeight = (int) ((double) arr[i] / SIZE * WINDOW_SIZE * 4 / 5);
            rect(ARRAY_POS_X + i * ARRAY_WIDTH / SIZE, ARRAY_POS_Y - rectHeight, ARRAY_WIDTH / SIZE, rectHeight);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}