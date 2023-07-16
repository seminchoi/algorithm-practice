import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] party = new long[m];
        boolean[] lieParty = new boolean[m];

        st = new StringTokenizer(br.readLine());
        long known = 0L;
        int knownCnt = Integer.parseInt(st.nextToken());

        for (int i = 0; i < knownCnt; i++) {
            long newKnown = 1L << Long.parseLong(st.nextToken());
            known |= newKnown;
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int partyCnt = Integer.parseInt(st.nextToken());
            long partyInfo = 0L;
            for (int j = 0; j < partyCnt; j++) {
                long newKnown = 1L << Long.parseLong(st.nextToken());
                partyInfo |= newKnown;
            }

            party[i] = partyInfo;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if ((party[j] & known) != 0 && !lieParty[j]) {
                    known |= party[j];
                    lieParty[j] = true;
                }
            }
        }

        int liePartyCnt = 0;
        for (int i = 0; i < m; i++) {
            if (!lieParty[i])
                liePartyCnt++;
        }

        System.out.println(liePartyCnt);
    }
}
