package search.string.substring;

public class KMP_DFA {
    private String pat;
    private int[][] dfa;

    public KMP_DFA(String pat) {
        this.pat = pat;
        int R = 256;
        int M = pat.length();
        // 初始化dfa数组
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        // 计算dfa数组
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];  // 复制匹配失败的情况下的值
            }
            dfa[pat.charAt(j)][j] = j + 1; // 设置匹配成功情况下的值
            X = dfa[pat.charAt(j)][X];  // 更改重启状况
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;
        else return -1;
    }

    public static void main(String[] args) {
        String txt = "BCBAABACAABABACAA";
        String pat = "ABABAC";
        KMP_DFA kmp = new KMP_DFA(pat);
        System.out.println(kmp.search(txt)); // 9
    }
}
