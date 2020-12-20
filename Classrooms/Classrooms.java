import java.util.*;
import java.io.*;

public class Classrooms{

    static int lowerBound(ArrayList<Long> a, long x) {
        int l = -1;
        int r = a.size();

        while(l + 1 < r) {
            int m = (l + r)>>>1;
            if(a.get(m) >= x){
                r = m;
            }else{
                l = m;
            }
        }

        return r;
    }

    public static void main(String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        int n = io.nextInt();
        int rooms = io.nextInt();
        long[][] events = new long[n][2];

        for(int i = 0; i < n; i++){
            events[i][0] = io.nextInt();
            events[i][1] = io.nextInt();
        }
        
        Arrays.sort(events, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1] == o2[1]){
                    return Long.compare(o1[0], o2[0]);
                }
                return Long.compare(o1[1], o2[1]);
            } 
        });

        int total = 0;
        ArrayList<Long> endtimes = new ArrayList<Long>();
        for(int i = 0; i < n; i++){
            int pos = 0;

            pos = lowerBound(endtimes, -events[i][0]);
            if(pos == endtimes.size()){
                if(endtimes.size() < rooms){
                    endtimes.add(-events[i][1] - 1);
                    total++;
                }
                continue;
            }

            endtimes.remove(pos);
            endtimes.add(-events[i][1] - 1);
            total++;
        }

        w.println(total);


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