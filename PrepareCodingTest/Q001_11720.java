import java.util.Scanner;
public class Q001_11720 {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		    int len = sc.nextInt();
		    String nums = sc.next();
		    
		    int result = 0;
		    for(int i = 0; i < len; i++)
		    {
		        result += Integer.valueOf(nums.charAt(i) - '0');
		    }
		    
		    System.out.println(result);

	}

}
