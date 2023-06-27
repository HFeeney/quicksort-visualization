import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class QuickSortTest {
    @Test
    public void sort_correct_small_randomElements() {
        int[] elements = new int[10];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = (int) (Math.random() * 10);
        }

        int[] elementsCopySorted = Arrays.copyOf(elements, elements.length);
        Arrays.sort(elementsCopySorted);

        QuickSort.sort(elements);

        for (int i = 0; i < elements.length; i++) {
            assertEquals(elementsCopySorted[i], elements[i]);
        }
    }

    @Test
    public void sort_correct_small_oddNumberRandomElements() {
        int[] elements = new int[11];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = (int) (Math.random() * 10);
        }

        int[] elementsCopySorted = Arrays.copyOf(elements, elements.length);
        Arrays.sort(elementsCopySorted);

        QuickSort.sort(elements);

        for (int i = 0; i < elements.length; i++) {
            assertEquals(elementsCopySorted[i], elements[i]);
        }
    }

    @Test
    public void sort_correct_large_randomElements() {
        int[] elements = new int[1000];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = (int) (Math.random() * 100);
        }

        int[] elementsCopySorted = Arrays.copyOf(elements, elements.length);
        Arrays.sort(elementsCopySorted);

        QuickSort.sort(elements);

        for (int i = 0; i < elements.length; i++) {
            assertEquals(elementsCopySorted[i], elements[i]);
        }
    }

    @Test
    public void sort_unchanged_sorted() {
        int[] elements = new int[1000];

        elements[0] = (int) (Math.random() * 100);
        for (int i = 1; i < elements.length; i++) {
            elements[i] = elements[i - 1] + (int) (Math.random() * 100);
        }

        int[] elementsCopy = Arrays.copyOf(elements, elements.length);

        QuickSort.sort(elements);

        for (int i = 0; i < elements.length; i++) {
            assertEquals(elementsCopy[i], elements[i]);
        }
    }
}