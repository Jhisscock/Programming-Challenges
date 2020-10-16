import java.util.*;

//https://open.kattis.com/problems/funnygames
public class FunnyGames{

    static class Winner{
        int index;
        float score;

        public Winner(int newIndex, float newScore){
            index = newIndex;
            score = newScore;
        }
    }
    public static float planetSize = 0;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for(int i = 0; i < cases; i++){
<<<<<<< Updated upstream
            planetSize = sc.nextFloat();
            int numOfWeapons = sc.nextInt();
            float [] weapons = new float[numOfWeapons];
            for(int j = 0; j < numOfWeapons; j++){
                weapons[j] = sc.nextFloat();
            }

            boolean winner = true;
            while(planetSize > 1){
                Winner bestMove = new Winner(0, 0);
                for(int j = 0; j < weapons.length; j++){
                    planetSize *= MiniMax(weapons, 5, true, weapons[j]);
                }
                winner = !winner;
            }            
=======
            double planetSize = sc.nextDouble();
            int numOfWeapons = sc.nextInt();
            double [] weapons = new double [numOfWeapons];
            for(int j = 0; j < numOfWeapons; j++){
                weapons[j] = sc.nextDouble();
            }
            if(Winner(weapons, planetSize)){
                System.out.println("Nils");
            }else{
                System.out.println("Mikael");
            }
>>>>>>> Stashed changes
        }

        sc.close();
    }

<<<<<<< Updated upstream
    public static float MiniMax(float [] weapons, int depth, boolean player, float currWeapon){
        if(depth == 0){
            //Evaluate score of position
            return 0;
        }
        if(player){
            float max = Float.MIN_VALUE;
            for (float f : weapons) {
                float score = MiniMax(weapons, depth, player, f);
                max = Math.max(score, max);
            }
            return max;
        }else{
            float min = Float.MAX_VALUE;
            for (float f : weapons) {
                float score = MiniMax(weapons, depth, player, f);
                min = Math.min(score, min);
            }
            return min;
        }
=======
    private static boolean Winner(double [] weapons, double planetSize){
        double min = 0.1;
        double max = 1;
        for (double f : weapons) {
            if((1 - f) < (1 - min)){
                min = f;
            }
            if((1 - f) > (1 - max)){
                max = f;
            }
        }

        double topRange = 1;
        boolean turn = true;
        while(topRange <= planetSize / max){
            if(turn){
                topRange /= max;
            }else{
                topRange /= min;
            }
            turn = !turn;
        }

        return turn;
>>>>>>> Stashed changes
    }

    //Find range starting from bottom in which the lowest F would result in a forced win for a player then construct ranges from there to determine winner.
}