import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Arrays;


class Solution {
    
    public class Heap{
        public int[] heap;
        private int size = 0;
        
        public void offer(int e){
            heap[size++] = e;
            
            // System.out.println(size + "offer");
            int pos = size-1;
            while(heap[pos] > heap[parent(pos)]){
                swap(pos,parent(pos));
                pos = parent(pos);
            }
        }
        
        public int poll(){
            int max = heap[0];
            heap[0] = 0;
            swap(0, size-1);

            size--;
            
            // System.out.println(size + "poll: " + max);
            
            maxHeapify(0);
            
            return max;
        }
        
        private void maxHeapify(int pos){
            if(isLeaf(pos)){
                return;
            }
            
            if(heap[pos] < heap[leftChild(pos)] 
               || heap[pos] < heap[rightChild(pos)]){
                if(heap[leftChild(pos)] > heap[rightChild(pos)]){
                    swap(pos,leftChild(pos));
                    maxHeapify(leftChild(pos));
                }
                else {
                    swap(pos,rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
        
        private void swap(int pos1, int pos2){
            int tmp = heap[pos2];
            heap[pos2] = heap[pos1];
            heap[pos1] = tmp;
        }
        
        private int parent(int pos){
            return pos / 2;
        }
        
        private int leftChild(int pos){
            return pos * 2;
        }
        
        private int rightChild(int pos){
            return pos * 2 + 1;
        }
        
        private boolean isLeaf(int pos){
            return pos > size && pos <= size;
        }
        
        public Heap (int size){
            heap = new int[size];
        }
    }
    
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        Heap heap = new Heap(2000001);
        

        int sum = 0;
        
        while(answer < enemy.length){
            sum = sum + enemy[answer];
            heap.offer(enemy[answer]);
            
            if(sum > n){
                if (k > 0) {
                    sum -= heap.poll();
                    k--;
                }
                else {
                    break;
                }
            }
            
            answer++;
    
        }
        
        // System.out.println(Arrays.toString(heap.heap));
        
        return answer;
    
    }
}