import java.util.*;
import java.io.*;

public class AC {
    public static void main(String [] args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();

        int [][] vertices = new int[numOfVertices][2];
        double max = Double.MIN_VALUE;
        List<Double[]> vertexList = new ArrayList<Double[]>();
        
        for(int i = 0; i <  numOfVertices; i++){
            vertices[i][0] = sc.nextInt();
            vertices[i][1] = sc.nextInt();
            vertexList.add(new Double[] {(double)vertices[i][0], (double)vertices[i][1]});
        }

        for(int i = 0; i < vertices.length; i++){
            int x = 0;
            int y = 0;
            
            if(i < vertices.length - 1){
                x = vertices[i + 1][0] - vertices[i][0];
                y = vertices[i + 1][1] - vertices[i][1];
            }else{
                x = vertices[0][0] - vertices[i][0];
                y = vertices[0][1] - vertices[i][1];
            }

            int gcdX = x / Math.abs(gcd(x, y));
            int gcdY = y / Math.abs(gcd(x, y));

            int currX = vertices[i][0];
            int currY = vertices[i][1];

            for(int j = 0; j <  Math.abs(gcd(x, y)); j++){
                vertexList.add(new Double[]{(double)(gcdX + currX), (double)(gcdY + currY)});
            }
        }

        for(int i = 0; i < vertexList.size() - 1; i++){
            for(int j = i + 1; j < vertexList.size(); j++){
                double distance = Math.sqrt(Math.pow(vertexList.get(j)[0] - vertexList.get(i)[0], 2) + Math.pow(vertexList.get(j)[1] - vertexList.get(i)[1], 2));
                max = Double.max(distance, max);
            }
        }

        System.out.println(max);

        CreatePolygon(vertices, numOfVertices);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void CreatePolygon(int [][] vertices, int numOfVertices) throws FileNotFoundException{
        int polygonSizeMax = Integer.MIN_VALUE;
        int polygonSizeMin = Integer.MAX_VALUE;
        int polygonSize = 0;

        for(int i = 0; i <  numOfVertices; i++){
            polygonSizeMax = Integer.max(vertices[i][0], polygonSizeMax);
            polygonSizeMax = Integer.max(vertices[i][1], polygonSizeMax);
            polygonSizeMin = Integer.min(vertices[i][0], polygonSizeMin);
            polygonSizeMin = Integer.min(vertices[i][1], polygonSizeMin);
        }

        polygonSize = polygonSizeMax - polygonSizeMin;
        
        int [][] drawPolygon = new int[(int)polygonSize + 1][(int)polygonSize + 1];

        for(int i = 0; i < polygonSize; i++){
            for(int j = 0; j < polygonSize; j++){
               drawPolygon[i][j] = 0;
            }
        }
        
        CreateOutline(drawPolygon, vertices, polygonSizeMin);
        
        //FillPolygon(drawPolygon, startPointX, startPointY);

        PrintStream o = new PrintStream(new File("Polygon.txt")); 
        System.setOut(o);

        for(int i = 0; i < polygonSize; i++){
            for(int j = 0; j < polygonSize; j++){ 
                System.out.print(drawPolygon[j][(int)polygonSize - 1 - i]);
            }
            System.out.println();
        }
    }

    public static void FillPolygon(int [][] polygon, int x, int y) throws FileNotFoundException{
        if(x < 0 || y < 0 || x >= polygon.length || y >= polygon.length || polygon[x][y] != 0){
            return;
        }

        polygon[x][y] = 1;

        FillPolygon(polygon, x + 1, y);
        FillPolygon(polygon, x - 1, y);
        FillPolygon(polygon, x, y + 1);
        FillPolygon(polygon, x, y - 1);
    }

    public static void CreateOutline(int[][] drawPolygon, int[][] vertices, int polygonSizeMin){
        for(int i = 0; i < vertices.length; i++){
            drawPolygon[vertices[i][0] + Math.abs(polygonSizeMin)][vertices[i][1] + Math.abs(polygonSizeMin)] = 1;
            int x = 0;
            int y = 0;
            
            if(i < vertices.length - 1){
                x = vertices[i + 1][0] - vertices[i][0];
                y = vertices[i + 1][1] - vertices[i][1];
            }else{
                x = vertices[0][0] - vertices[i][0];
                y = vertices[0][1] - vertices[i][1];
            }

            int gcdX = x / Math.abs(gcd(x, y));
            int gcdY = y / Math.abs(gcd(x, y));

            int currX = vertices[i][0] + Math.abs(polygonSizeMin);
            int currY = vertices[i][1] + Math.abs(polygonSizeMin);

            for(int j = 0; j <  Math.abs(gcd(x, y)); j++){
                drawPolygon[gcdX + currX][gcdY + currY] = 1;
                currX = gcdX + currX;
                currY = gcdY + currY;
            }
        }
    }
}