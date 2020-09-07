import java.util.*;

//https://open.kattis.com/problems/funnygames
public class FunnyGames{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for(int i = 0; i < cases; i++){
            int planetSize = sc.nextInt();
            int numOfWeapons = sc.nextInt();
            List<Float> weapons = new ArrayList<Float>();
            for(int j = 0; j < numOfWeapons; j++){
                weapons.add(sc.nextFloat());
            }
            minimax(weapons, 5, true);
        }
    }

    //Mini-max: Player 1 is true
    private static boolean minimax(List<Float> weapons, int depth, boolean player){
        if(depth == 0){
            return false;
        }
        if(player){
            int max = Integer.MIN_VALUE;
            return true;
        }
        return true;
    }
}