import java.io.*;
import java.util.*;

public class QueryOnArray{
    static long[] bitArray = new long[100000];
    static int arraySize;
    static final long MOD = 1000000007;

    public static void main(String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        arraySize = io.nextInt();
        int numOfQueries = io.nextInt();

        for(int i = 0; i < numOfQueries; i++){
            int queryType = io.nextInt();
            int x = io.nextInt() - 1;
            int y = io.nextInt() - 1;

            long count = 0;
            long sum = 0;
            switch (queryType){
                case 0:
                    sum = sumRange(x, y);
                    if(sum < 0 ){
                        sum += MOD;
                    }
                    w.println(sum);
                    break;
                case 1:
                    for(int j = x; j <= y; j++){
                        updateBitArray(j, (count + 1) * (count + 2) * (count + 3));
                        count++;
                    }
                    break;
                case 2:
                    for(int j = x; j <= y; j++){
                        updateBitArray(j, -((count + 1) * (count + 2) * (count + 3)));
                        count++;
                    }
                    break;
                default:
            }
        }
        w.close();
    }

    public static void updateBitArray(int i, long value){
        i++;
        while(i <= arraySize){
            bitArray[i] = (bitArray[i] + value)%MOD;
            i += (i & -i);
        }
    }

    public static long sum(int i){
        long sum = 0;
        i++;
        while(i > 0){
            sum = (bitArray[i] + sum)%MOD;
            i -= (i & -i);
        }
        return sum;
    }

    public static long sumRange(int i, int j){
        return sum(j) - sum(i - 1);
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