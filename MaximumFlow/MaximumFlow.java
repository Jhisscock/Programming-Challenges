import java.io.*;
import java.util.*;

public class MaximumFlow{
    static int [][] graph = new int[501][501];
    static int [][] rGraph = new int [501][501];
    static Edge [] edges = new Edge[10001];
    static int [] path = new int[501];
    static int count = 0;

    static class Edge{
        int x;
        int y;
        int flow;

        public Edge(int X, int Y, int Flow){
            x = X;
            y = Y;
            flow = Flow;
        }
    }
    
    public static boolean bfs(int source, int sink, int [] path, int numNodes){
        boolean [] visited = new boolean[numNodes];
        ArrayDeque<Integer> nodeList = new ArrayDeque<Integer>();
        nodeList.add(source);
        visited[source] = true;
        path[source] = -1;

        while(!nodeList.isEmpty()){
            int currNode = nodeList.poll();
            for(int i = 0; i < numNodes; i++){
                if(!visited[i] && rGraph[currNode][i] > 0){
                    nodeList.add(i);
                    path[i] = currNode;
                    visited[i] = true;
                }
            }
        }

        return visited[sink];
    }

    public static int maxFlow(int source, int sink, int numNodes){
        int max = 0;
        int x = numNodes;

        while(bfs(source, sink, path, numNodes)){
            int currFlow = Integer.MAX_VALUE;
            for(int i = sink; i != source; i = path[i]){
                x = path[i];
                currFlow = Math.min(currFlow, rGraph[x][i]);
            }

            for(int i = sink; i != source; i = path[i]){
                x = path[i];
                rGraph[x][i] -= currFlow;
                rGraph[i][x] += currFlow;
            }
            max += currFlow;
        }

        for(int i = 0; i < numNodes; i++){
            for(int j = 0; j < numNodes; j++){
                if(graph[i][j] > 0 && rGraph[i][j] < graph[i][j]){
                    edges[count] = new Edge(i, j, graph[i][j] - rGraph[i][j]);
                    count++;
                }
            }
        }

        
        return max;
    }
    
    public static void main (String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int numNodes = io.nextInt();
        int numEdges = io.nextInt();
        int source = io.nextInt();
        int sink = io.nextInt();

        for(int i = 0; i < numEdges; i++){
            int x = io.nextInt();
            int y = io.nextInt();
            int flow = io.nextInt();

            graph[x][y] = flow;
            rGraph[x][y] = flow;
        }
        
        w.println(numNodes + " " + maxFlow(source, sink, numNodes) + " " + count);
        for(int i = 0; i < count; i++){
            w.println(edges[i].x + " " + edges[i].y + " "  + edges[i].flow);
        }
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