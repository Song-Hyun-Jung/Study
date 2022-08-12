import java.util.Scanner;

public class Q006_2018 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
	     int N = sc.nextInt();
	     
	     int startIdx = 0;
	     int endIdx = 0;
	     int count = 0;
	     int sum = 0;
	     
	     while(endIdx <= N) {
	    	 if(sum == N) {
	    		 endIdx++;
	    		 count++;
	    		 sum += endIdx;
	    	 }
	    	 else if(sum < N) {
	    		 endIdx++;
	    		 sum += endIdx;
	    	 }
	    	 else if(sum > N) {
	    		 sum -= startIdx;
	    		 startIdx++;
	    	 }
	    		 
	    	 
	     }

	     System.out.println(count);
	}
	/*
	    	Scanner sc = new Scanner(System.in);
	        int N = sc.nextInt();
	     
	        int startIdx = 1;
	        int endIdx = 1;
	        int sum = 1;
	        int count = 1;
	        
	        while(endIdx != N){
	            if(sum == N){
	                count++;
	                endIdx++;
	                sum += endIdx;
	            }
	            else if(sum < N){
	                endIdx++;
	                sum += endIdx;
	            }
	            else{
	                sum -= startIdx;
	                startIdx++;
	            }
	        }
	        System.out.print(count);
    }
	 */
}
