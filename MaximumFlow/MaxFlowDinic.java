import java.util.*;
import java.io.*;

class Edge{
    int vertex1;
    int vertex2;
    int capacity;
    int flow;
    Edge reverse;

    public Edge(int Vertex1, int Vertex2, int Capacity, int Flow){
        vertex1 = Vertex1;
        vertex2 = Vertex2;
        capacity = Capacity;
        flow = Flow;
    }
}

public class MaxFlowDinic {
    static ArrayList<Edge>[] graph;
    static int numNodes, numEdges, source, sink;

    static boolean bfs(int [] distance){
        Arrays.fill(distance, -1);
        distance[source] = 0;
        ArrayDeque<Integer> nodes = new ArrayDeque<>();
        nodes.add(source);

        while(!nodes.isEmpty()){
            int currNode = nodes.poll();
            for (Edge edge : graph[currNode]) {
                if(distance[edge.vertex2] < 0 && edge.reverse.flow < edge.reverse.capacity){
                    distance[edge.vertex2] = distance[currNode] + 1;
                    nodes.add(edge.vertex2);
                }
            }
        }

        return distance[sink] >= 0;
    }
    
    public static int dfs(int [] distance, int currPos, int min, boolean [] blocked){
        if(source == sink){
            return min;
        }

        int flow = 0;
        for (Edge edge : graph[currPos]) {
            int currentFlow = 0;
            if(!blocked[edge.vertex2] && distance[edge.vertex2] == distance[currPos]-1 && edge.capacity - edge.flow > 0){
                currentFlow = dfs(distance, edge.vertex2, Math.min(min - flow, edge.capacity - edge.flow), blocked);
                edge.flow += currentFlow;
                edge.reverse.flow = -edge.flow;
                flow += currentFlow;
            }

            if(flow == min){
                return flow;
            }
        }

        blocked[currPos] = flow != min;
        return flow;
    }

    public static int dinicMaxFlow(){
        int flow = 0;
        int [] distance = new int[numNodes];
        while(bfs(distance)){
            boolean [] blocked = new boolean[numNodes];
            flow += dfs(distance, source, Integer.MAX_VALUE, blocked);
        }

        return flow;
    }
    
    public static void main (String [] args){
        InputReader io = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        numNodes = io.nextInt();
        numEdges = io.nextInt();
        source = io.nextInt();
        sink = io.nextInt();

        //Create Array of Lists to represent graph
        graph = new ArrayList[numNodes];
        for(int i = 0; i < numNodes; i++){
            graph[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i < numEdges; i++){
            int x = io.nextInt();
            int y = io.nextInt();
            int capacity = io.nextInt();

            Edge forward = new Edge(x, y, capacity, 0);
            Edge reverse = new Edge(y, x, 0, 0);
            reverse.reverse = forward;
            forward.reverse = reverse;
            graph[x].add(forward);
            graph[y].add(reverse);
        }

        w.println(dinicMaxFlow());

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
