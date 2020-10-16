/*
29.29 4 0.3 0.7 0.43 0.54
29.30 4 0.3 0.7 0.43 0.54
*/
public class Ranges {
    public static void main(String [] args){
        double [] nums = {1, 0.3, 1, 0.7, 1, 0.43, 1 ,0.54};
        double currNode = 1;
        int currPos = 0;
        while(currNode <= 30){
            double min = Double.MAX_VALUE;
            for(int i = 0; i < 8; i += 2){
                double tmp = nums[i] / nums[i+1];
                if(tmp < min){
                    min = tmp;
                    currPos = i;
                }
            }
            nums[currPos] = min;
            System.out.println(currNode);
            currNode = min;
        }

        /*
        double [] nums = {0.3,0.7,0.57,0.46};
        for (double d : nums) {
            double total = 1;
            while(total <= 30){
                total /= d;
                System.out.print(total + ", "); 
            }
            System.out.println();
        }
        */
    }
}
