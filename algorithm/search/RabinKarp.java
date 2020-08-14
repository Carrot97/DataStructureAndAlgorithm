package search.string.substring;


public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private int R;
    private long Q;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        this.R = 256;
        this.Q = 997;
        this.patHash = hash(pat, M);
        RM = 1;
        for (int i = 1; i < M; i++)
            RM = (R * RM) % Q;
    }

    public long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
//        if (patHash == txtHash && check(0)) return 0;
        if (patHash == txtHash) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (txtHash == patHash)
                return i-M+1;
//                if (check(i-M+1)) return i-M+1;
        }
        return N;
    }

//    当Q足够大时可以保证结果的概率正确
//    private boolean check(int i) {
//        return true;
//    }

    public static void main(String[] args) {
        String txt = "BCBAABACAABABACAA";
        String pat = "ABABAC";
        RabinKarp rk = new RabinKarp(pat);
        System.out.println(rk.search(txt)); // 9
    }
}
