import java.util.Scanner;
import java.lang.Math;
//부분합
public class Main{
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int[] mushrooms = new int[10];
        for(int i = 0; i < 10; i++)
        {
            mushrooms[i] = sc.nextInt();
        }
        
        int[] sumMushrooms = new int[10];
        
        int maxMushroom = 0;
        for(int i = 0; i < 10; i++)
        {
            if(i == 0)
                sumMushrooms[i] = mushrooms[i];
            else
                sumMushrooms[i] = sumMushrooms[i - 1] + mushrooms[i];
            
            if(Math.abs(sumMushrooms[i] - 100) < Math.abs(maxMushroom - 100))
            {
                maxMushroom = sumMushrooms[i];
            }
            else if(Math.abs(sumMushrooms[i] - 100) == Math.abs(maxMushroom - 100))
            {
                maxMushroom = (sumMushrooms[i] > maxMushroom) ? sumMushrooms[i] : maxMushroom;
            }
            
            if(maxMushroom == 100)
                break;
        }
        System.out.println(maxMushroom);
    }
}
