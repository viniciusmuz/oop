package week6practice;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = { 313, 21, 4, 497, 91, 32, 1};

        for (int i = 1; i < array.length; i++) {
            int position = i;

            while (position > 0 && array[position] < array[position-1]) {
                int temp = array[position];
                array[position] = array[position - 1];
                array[position - 1] = temp;
                position -= 1;
            }
        }

        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}
