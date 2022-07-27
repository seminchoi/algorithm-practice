package 트리의높이와너비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2250 {

    static public Tree[] trees;
    static public int[][] maxWidth = new int[2][];
    static boolean[] isRoot;

    static public class Tree{
        int depth;
        int width;
        int left;
        int right;
        Tree(int left, int right){
            this.left = left;
            this.right = right;
            //최상위 부모를 빠르게 찾기 위해 생성을 함과 동시에 isRoot에 값을 할당한다.
            //자식으로 입력된 값들은 최상위 부모일 수 없으므로 boolean 배열에 식별을 위해 true 를 할당한다.
            if(left != -1) {
                isRoot[left] = true;
            }
            if(right != -1) {
                isRoot[right] = true;
            }
        }
    }

    static public int inOrder(int root,int depth, int width){
        trees[root].depth = depth;
        if(trees[root].left != -1) {
            //좌측 노드를 탐색 하고 돌아왔을 때 width에 +1을 해준다.
            width = inOrder(trees[root].left, depth + 1, width) + 1;
        }
        //좌측 노드의 넓이에 +1된 값이 들어간다.
        trees[root].width = width;
        if(trees[root].right != -1)
            //우측노드를 탐색할 때는 넓이에 1을 더해서 진입하고 우측노드의 최종 넓이 값을 반환한다.
            //우측노드는 직속 부모노드의 넓이에 영향을 주지 않는다.
            width = inOrder(trees[root].right,depth+1,width+1);
        if(maxWidth[0][depth] == 0 || maxWidth[0][depth] > width)
            maxWidth[0][depth] = trees[root].width;
        if(maxWidth[1][depth] == 0 || maxWidth[1][depth] < width)
            maxWidth[1][depth] = trees[root].width;
        return width;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        trees = new Tree[N+1];
        maxWidth[0] = new int[N+1];
        maxWidth[1] = new int[N+1];
        isRoot = new boolean[N+1];

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            trees[root] = new Tree(left,right);
        }

        int s = 1;
        for (int i = 1; i < N+1; i++) {
            if(!isRoot[i]){
                s = i;
                break;
            }
        }
        inOrder(s,1,1);

        int maxDif = -1;
        int maxDifDepth = 0;
        for (int i = 1; i < N+1; i++) {
            if(maxWidth[0][i] == 0)
                break;
            int dif = maxWidth[1][i] - maxWidth[0][i];
            if(maxDif < dif) {
                maxDif = dif;
                maxDifDepth = i;
            }
        }
        System.out.println(maxDifDepth + " " + (maxDif+1));
    }
}
