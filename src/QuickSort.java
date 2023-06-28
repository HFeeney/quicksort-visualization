import java.util.concurrent.RecursiveAction;

public class QuickSort extends RecursiveAction {
    private static final int SLEEP_DURATION = 10; // in ms

    private final int[] arr;
    private final int lo;
    private final int hi;
    private final Object lock;

    /**
     * Creates a thread based quick sort routine to sort a range of a provided array
     * @param arr the array of ints to be sorted lo to high
     * @param lo lowest index of range (inclusive)
     * @param hi highest index of range (exclusive)
     * @param lock object guarding read/write of provided array
     */
    public QuickSort(int[] arr, int lo, int hi, Object lock) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.lock = lock;
    }

    @Override
    protected void compute() {
        if (hi - lo <=  1) return;

        // index of the end of the small section of array
        int i = lo - 1;

        // choose pivot as last element of array
        int pivot = arr[hi - 1];

        // move elements less than the pivot to first section of array
        for (int j = lo; j < hi - 1; j++) {
            if (arr[j] < pivot) {
                // small section is one element larger
                i++;
                // swap elements at i, j
                synchronized (lock) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException ex) {
                System.out.println("Thread sorting range " + lo + " - " + hi + " interrupted");
            }
        }

        // move pivot back to the index following the end of the small portion (i + 1)
        synchronized (lock) {
            arr[hi - 1] = arr[i + 1];
            arr[i + 1] = pivot;
        }

        QuickSort left = new QuickSort(arr, lo, i + 1, lock);
        QuickSort right = new QuickSort(arr, i + 2, hi, lock);

        left.fork();
        right.compute();
        left.join();
    }

    /**
     * Sorts the given array in ascending order
     * @param arr the array of ints to sort
     */
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi - lo <=  1) return;

        // index of the end of the small section of array
        int i = lo - 1;

        // choose pivot as last element of array
        int pivot = arr[hi - 1];


        // move elements less than the pivot to first section of array
        for (int j = lo; j < hi - 1; j++) {
            if (arr[j] < pivot) {
                // small section is one element larger
                i++;
                // swap elements at i, j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // move pivot back to the index following the end of the small portion (i + 1)
        arr[hi - 1] = arr[i + 1];
        arr[i + 1] = pivot;

        // quick sort left half, right half
        sort(arr, lo, i + 1);
        sort(arr, i + 2, hi);
    }
}
