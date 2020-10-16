import java.util.*;

//https://open.kattis.com/problems/textprocessor
public class TextProcessor{
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);

        String inputString = sc.nextLine();
        int cases = sc.nextInt();
        int width = sc.nextInt();

        for(int i = 0; i < cases; i++){
            int startRange = sc.nextInt() - 1;

            String longestSubString = inputString.substring(startRange, startRange + width);
            String[] bwtMatrix = new String[longestSubString.length()];
            bwtMatrix[0] = longestSubString;

            for(int j = 1; j < longestSubString.length(); j++){
                bwtMatrix[j] = rotate(bwtMatrix[j - 1]);
            }

            System.out.println(Arrays.toString(bwtMatrix));
        }
    }

    static String rotate(String str){
        String result = str.substring(1) + str.substring(0, 1);
        return result;
    }
}