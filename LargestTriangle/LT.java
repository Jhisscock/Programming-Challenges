import java.util.*;
import java.io.*;

public class LT{

    static class Point{
        double x;
        double y;
        long value;
        int key;

        public Point(double i, double j, long v, int k){
            x = i;
            y = j;
            value = v;
            key = k;
        }
    }

    static Point[] points= new Point[5001];
    static long[] values = new long[5001];

    public static void main(String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        int n = io.nextInt();
        for(int i = 0; i < n; i++){
            long x = io.nextLong();
            long y = io.nextLong();
            points[i] = new Point((double)x, (double)y, x + y, i);
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        int minKey = -1;
        int maxKey = -1;
        
        for(int i = 0; i < n; i++){
            long curr = points[i].value;
            if(curr < min){
                min = curr;
                minKey = i;
            }

            if(curr > max){
                max = curr;
                maxKey = i;
            }
        }
        
        double diff = points[maxKey].y - points[minKey].y;
        double minX = points[minKey].x;
        double minY = points[minKey].y;
        double maxX = points[maxKey].x;
        double maxY = points[maxKey].y;
        double best = Double.MIN_VALUE;
        for(int i = 0; i < n; i++){
            double area = Math.abs((minX * (points[i].y - maxY) + points[i].x * diff  + maxX * (minY - points[i].y)) / 2);
            if(area > best){
                best = area;
            }
        }

        w.println(best);
        w.close();
    }



    static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}