package search.string.substring;

public class BoyerMoore {
    private String pat;
    private int[] right;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int R = 256;
        int M = pat.length();
        right = new int[R];
        for (char c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < M; j++)
            right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip, i;
        for (i = 0; i <= N - M ; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String txt = "BCBAABACAABABACAA";
        String pat = "ABABAC";
        BoyerMoore bm = new BoyerMoore(pat);
        System.out.println(bm.search(txt)); // 9
    }
}
