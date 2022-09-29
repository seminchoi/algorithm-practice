package algorithm;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] array, int left, int right){
        if(left >= right){
            return;
        }

        int pivot = partition(array, left, right);

        quickSort(array,left, pivot-1);
        quickSort(array,pivot+1, right);
    }

    public static int partition(int[] array, int left, int right){
        int pivot = array[left];

        int low = left;
        int high = right;

        while(low < high){
            while (pivot < array[high]){
                high--;
            }
            while (low < high && pivot >= array[low]){
                low++;
            }
            swap(array,low,high);
        }
        swap(array,left,low);

        return low;
    }

    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 1, 3 ,10 ,8 , 7 ,22 ,31};

        quickSort(array, 0, array.length-1);

        System.out.println(Arrays.toString(array));
    }
}
