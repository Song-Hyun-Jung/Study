import java.util.*;
public class Q002_1546 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
	        int num = sc.nextInt();
	       
	        int[] scores = new int[num];
	        int maxScore = -1;
	        for(int i = 0; i < num; i++){
	            scores[i] = sc.nextInt();
	            if(maxScore < scores[i])
	                maxScore = scores[i];
	        }
	     
	        float sum = 0;
	        for(int i = 0; i < num; i++)
	        {
	            sum += scores[i] ;
	            
	        }
	        
	        System.out.println(sum * 100.0 / maxScore / num);
	}

}
