package algorithm;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {2, 5, 1, 3 ,10 ,8 , 7 ,22 ,31};

        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int prev = i - 1;
            while (prev >= 0 && tmp < array[prev]){
                array[prev+1] = array[prev];
                prev--;
            }
            array[prev+1] = tmp;
        }

        System.out.println(Arrays.toString(array));
    }
}
