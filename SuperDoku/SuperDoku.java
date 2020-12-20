import java.util.*;
import java.io.*;

public class SuperDoku {
    static int n;
    static int completeRows;
    static int[][] board = new int[101][101];
    static BitBoard[] bitsCol = new BitBoard[101];
    static BitBoard[] bitsRow = new BitBoard[101];

    static class BitBoard{
        long r0;
        long r1;

        public BitBoard(long x, long y){
            r0 = x;
            r1 = y;
        }
    }

    public static void main(String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        n = io.nextInt();
        completeRows = io.nextInt();

        for(int i = 0; i < n; i++){
            bitsCol[i] = new BitBoard(Long.MAX_VALUE, Long.MAX_VALUE);
            bitsRow[i] = new BitBoard(Long.MAX_VALUE, Long.MAX_VALUE);
        }

        for(int i = 0; i < completeRows; i++){
            for(int j = 0; j < n; j++){
                board[j][i] = io.nextInt();
                bitsCol[j].r0 = setBit(bitsCol[j].r0, board[j][i], false);
            }
        }

        int count = completeRows;
        for(int j = 1; j <= n; j++){
            if(getBit(bitsCol[0].r0, j)){
                bitsCol[0].r0 = setBit(bitsCol[0].r0, j, false);
                bitsRow[count - completeRows].r0 = setBit(bitsRow[count - completeRows].r0, j, false);
                board[0][count] = j;
                count++;
            }
        }

        if(!solveBoard()){
            w.println("no");
        }else{
            w.println("yes");
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    w.print(board[j][i] + " ");
                }
            w.println();
            }
        }

        w.close();
    }

    static boolean solveBoard(){
        for(int i = 1; i < n; i++){
            for(int j = 0; j < n - completeRows; j++){
                long op = (bitsCol[i].r0 & bitsRow[j].r0);
                for(int q = 1; q <= n; q++){
                    if(getBit(op, q)){
                        bitsRow[j].r0 = setBit(bitsRow[j].r0, q, false);
                        bitsCol[i].r0 = setBit(bitsCol[i].r0, q, false);
                        board[i][j + completeRows] = q;
                        break;
                    }else if(q == n && j == n - completeRows - 1){
                        for(int x = 0; x < n - completeRows; x++){
                            if(getBit(bitsRow[x].r0, q) && getBit(bitsRow[j].r0, board[i][j + completeRows - x - 1])){
                                System.out.println(i + " : " + q + " : " + board[i][j + completeRows - x - 1]);
                                bitsRow[x].r0 = setBit(bitsRow[x].r0, q, false);
                                bitsRow[x].r0 = setBit(bitsRow[x].r0, board[i][j + completeRows - x - 1], true);

                                bitsRow[j].r0 = setBit(bitsRow[j].r0, board[i][j + completeRows - x - 1], false);

                                bitsCol[i].r0 = setBit(bitsCol[i].r0, q, false);
                                System.out.println(x + " " + Long.toBinaryString(bitsRow[x].r0));

                                board[i][j + completeRows] = board[i][j + completeRows - x - 1];
                                board[i][j + completeRows - x - 1] = q;
                                break;
                            }
                        }
                        
                    }
                }
                
            }
        }
        return true;
    }

    static long setBit(long b, int bitPosition, boolean bitValue){
        if (bitValue){
            return (b | (1 << bitPosition));
        }
        return (b & ~(1 << bitPosition));
    }

    static boolean getBit(long b, int bitPosition){
        return (b & (1 << bitPosition)) != 0;
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
