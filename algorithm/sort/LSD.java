package sort;

import java.io.*;
import java.util.Arrays;

public class LSD {
    public static void sort(String[] data) {
        int N = data.length;
        int W = data[0].length();
        int R = 256;
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                try {
                    count[data[i].charAt(d) + 1]++;
                } catch (Exception e) {
                }
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < N; i++) {
                aux[count[data[i].charAt(d)]++] = data[i];
            }

            for (int i = 0; i < N; i++) {
                data[i] = aux[i];
            }
        }
    }

    public static String[] readStrings(String filename, int N) {
        String[] data = new String[N];
        BufferedReader br = null;
        try {
            data = new String[N];
            br = new BufferedReader(new FileReader(filename));
            String buffer;
            int i = 0;
            while ((buffer = br.readLine()) != null) {
                data[i] = buffer;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) {
        String[] data = readStrings("String.txt", 13);
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }


}
