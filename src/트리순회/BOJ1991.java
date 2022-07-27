package 트리순회;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.util.List;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {
    static List<Character>[] tree;

    //전위 순회 (루트 -> 좌측 노드 -> 우측 노드)
    public static void preOrder(int s){
        Iterator<Character> it = tree[s].listIterator();
        System.out.print((char)(s+'A'));
        //좌측 노드
        char node = it.next();
        if(node != '.') preOrder((int)node - 'A');
        //우측 노드
        node = it.next();
        if(node != '.') preOrder((int)node - 'A');
    }

    //중위 순환 (좌측노드 -> 루트 -> 우측노드)
    public static void inOrder(int s){
        Iterator<Character> it = tree[s].listIterator();
        char node = it.next();
        //좌측 노드
        if(node != '.') inOrder((int)node - 'A');

        System.out.print((char)(s+'A'));

        //우측 노드
        node = it.next();
        if(node != '.') inOrder((int)node - 'A');
    }

    //중위 순환 (좌측노드 -> 루트 -> 우측노드)
    public static void postOrder(int s){
        Iterator<Character> it = tree[s].listIterator();
        char node = it.next();
        //좌측 노드
        if(node != '.') postOrder((int)node - 'A');

        //우측 노드
        node = it.next();
        if(node != '.') postOrder((int)node - 'A');

        System.out.print((char)(s+'A'));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new List[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] root = {st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0)};
            int r = root[0] - 'A';
            tree[r] = new LinkedList<>();
            tree[r].add(root[1]); tree[r].add(root[2]);
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();

    }
}
