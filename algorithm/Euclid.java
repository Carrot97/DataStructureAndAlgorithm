public class Euclid {
    public static int gcd(int p, int q) {
        int r = 0;
        while (q > 0) {
            r = p % q;
            p = q;
            q = r;
        }
        return p;
    }

//    public static int gcd(int p, int q) {
//        if (q == 0) return p;
//        else return gcd(q, p % q);
//    }

    public static void main(String[] args) {
        System.out.println(gcd(64, 16));
    }
}
