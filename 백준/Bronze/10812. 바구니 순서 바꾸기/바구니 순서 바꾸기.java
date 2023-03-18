import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //바구니개수
        int m = sc.nextInt(); //바꿀 횟수
        int temp[] = new int[n+1];
        int arr[] = new int[n+1];
        for(int i=1;i<n+1;i++){
            arr[i] = i;
            temp[i] = i;
        }
        for(int i=0;i < m;i++){
            int begin = sc.nextInt();
            int end = sc.nextInt();
            int mid = sc.nextInt();

            int idx = begin;

            for(int j = mid; j <= end; j++){
                temp[idx++] = arr[j];
            }

            for(int j = begin; j < mid; j++){
                temp[idx++] = arr[j];
            }

            for(int j = begin; j<=end; j++){
                arr[j] = temp[j];
            }


        }

        for(int j = 1;j < n+1;j++){
            System.out.print(arr[j]+" ");
        }

        sc.close();
    }
}
