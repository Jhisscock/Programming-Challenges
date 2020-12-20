import java.util.*;
import java.io.*;
import java.util.regex.*;

public class d4{
  public static void main(String[] args){
    int result = 0;
    try {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        int total = 0;
        while (myReader.hasNextLine()) {
            String in = myReader.nextLine();
            int i = 0;
            Pattern p = Pattern.compile(":");
            Matcher m = p.matcher( in );
            Pattern p2 = Pattern.compile("cid");
            Matcher m2 = p2.matcher( in );
            while (m.find()){
                if(m2.find()){
                    i--;
                }
                i++;
            }
            if(in.isEmpty()){
                if(total >= 7){
                    result++;
                }
                total = 0;
            }
            total += i;
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    System.out.println(result + 1);
  }
}