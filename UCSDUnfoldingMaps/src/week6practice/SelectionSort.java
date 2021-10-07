package week6practice;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = { 313, 21, 4, 497, 91, 32, 1};

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        for(int number : array) {
            System.out.print(number + " ");
        }
    }
}
