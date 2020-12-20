import java.util.*;
import java.io.*;

public class TM{
    static int[][] values = new int[100001][7];

    public static void main(String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        int cities = io.nextInt();
        for(int i = 0; i < cities; i++){
            int initial = io.nextInt();
            int iterator = io.nextInt();
            for(int j = 0; j < 4; j++){
                int currValue = initial + iterator * j;
                values[i][j] = currValue;
                values[i][6 - j] = currValue;
            }
        }

        int paths = io.nextInt();
        for(int i = 0; i < paths; i++){
            int start = io.nextInt() - 1;
            int end = io.nextInt() - 1;

            w.println(profitForward(Integer.MIN_VALUE, 1, 0, 1, start, end));


            
        }
        w.close();

    }

    static int profitForward(int max, int pointer, int day, int dCount, int start, int end){
        if(dCount >= 7){
            dCount = 0;
        }

        if(pointer >= end){
            return max;
        }

        int tmp = values[start][day] - values[pointer + start][dCount];
        if(max < tmp){
            max = tmp;
            dCount = day + 2;
            return Math.max(max, profitForward(max, pointer++, day++, dCount, start++, end));
        }else{
            profitForward(max, pointer++, day, dCount++, start, end);
        }

        return max;
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