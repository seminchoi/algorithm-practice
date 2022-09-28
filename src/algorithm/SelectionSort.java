package algorithm;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {2, 5, 1, 3 ,10 ,8 , 7 ,22 ,31};

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if(array[minIndex] > array[j]){
                    minIndex = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }

        System.out.println(Arrays.toString(array));
    }
}
