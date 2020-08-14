package search.string.substring;

public class BruteForce {
    public static int search1(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;
        }
        return -1;
    }

    public static int search2(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) return i - M;
        else return -1;
    }

    public static void main(String[] args) {
        String txt = "BCBAABACAABABACAA";
        String pat = "ABABAC";
        System.out.println(search1(txt, pat));  // 9
        System.out.println(search2(txt, pat));
    }
}
