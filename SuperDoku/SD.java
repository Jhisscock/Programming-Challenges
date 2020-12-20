import java.util.*;
import java.io.*;

public class SD {
    static int n;
    static int[][] board = new int[101][101];
    static List<Integer>[] colList = new ArrayList[101];
    static boolean[][] instRow = new boolean [101][101];
    static boolean[][] instCol = new boolean [101][101];
    static int[][] rows = new int[101][101];
    static int[][] cols = new int[101][101];
    static int[] rowCount = new int[101];
    static int[] colCount = new int[101];
    static int completeRows;
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
                instCol[board[j][i]][j] = true;
                bitsCol[j].r0 = setBit(bitsCol[j].r0, board[j][i], false);
            }
        }

        if(!solveBoard(0, completeRows)){
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

    static boolean solveBoard(int col, int row){
        if(row == n){
            return true;
        }
        
        int newCol;
        int newRow = row;
        if(col == n - 1){
            newCol = 0;
            newRow = row + 1;
        }else{
            newCol = col + 1;
        }

        if(board[col][row] == 0){
            for(int i = 0; i < n - completeRows; i++){
                int val = colList[col].get(i);
                if(!instCol[val][col] && !instRow[val][row]){
                    instRow[val][row] = true;
                    instCol[val][col] = true;
                    bitsRow[row].r0 = setBit(bitsRow[row].r0, q, false);
                    bitsCol[col].r0 = setBit(bitsCol[col].r0, q, false);
                    board[col][row] = val;

                    boolean result = solveBoard(newCol, newRow);
                    if(result){
                        return result;
                    }
                    
                    instRow[val][row] = false;
                    instCol[val][col] = false;
                    board[col][row] = 0;
                }
            }

            return false;
        }else{
            return solveBoard(newCol, newRow);
        }
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
