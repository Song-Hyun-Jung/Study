import java.util.Arrays;
import java.util.Scanner;

public class Student20190972 {
	static int[] nums;
	static int minError = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		int slice = input.nextInt();
		
		nums = new int[n];
		for(int i = 0; i < n; i++)
			nums[i] = input.nextInt();
		
		Arrays.sort(nums);
		//for(int i : nums) System.out.print(i + " ");
		
		int[] bucket = new int[slice];
		
		solution(nums.length, bucket, slice - 1);

		System.out.println(minError);
	}
	
	public static void solution(int len, int[] bucket, int k) {
		if(k == 0) 
		{
			int currentError = 0;
			int minIdx = 0;
			int maxIdx = 0;
			for (int i = 0; i < bucket.length; i++) 
			{
				//System.out.println(bucket[i]);
				maxIdx = minIdx + 1;
				if(minIdx == bucket.length - 1)
					currentError += getError(bucket[minIdx], nums.length - 1);
				else if(minIdx == 0)
					currentError += getError(0, bucket[maxIdx] - 1);
				else
					currentError += getError(bucket[minIdx], bucket[maxIdx] - 1);
				minIdx += 1;
			}
			
			if(currentError < minError) {
				minError = currentError;
			}
		
			//System.out.println("error: " + currentError);
			return;
		
		}
		
		int lastIndex = bucket.length - k - 1;
	
		
		for (int i = 1; i <= len - 1; i++) {
			if(bucket.length == k) {
				bucket[0] = 0;
				solution(len, bucket, k - 1);
			}
			else if(bucket[lastIndex] < i)
			{
				bucket[lastIndex + 1] = i;
				solution(len, bucket, k - 1);
			}
		}
	}
	
	public static int getError(int minIdx, int largeIdx) {	
		int sum = 0;
		int len = largeIdx - minIdx + 1;
		int[] temp = new int[len];
		int j = 0;
		for(int i = minIdx; i <= largeIdx ; i++) {
			temp[j++] = nums[i];
			sum += nums[i];
		}
		
		float groupAvg = (float)sum / len;
		int smallRepresentNum = (int) Math.floor(groupAvg);
		int largeRepresentNum = (int) Math.ceil(groupAvg);
		//System.out.println("min " + minIdx + " max " +  largeIdx + " sum " + sum + " avg " + groupAvg);
		if(smallRepresentNum != largeRepresentNum) {
			int error1 = 0;
			int error2 = 0;
			for(int i = 0; i < temp.length; i++){
				error1 += Math.pow((temp[i] - smallRepresentNum), 2 );
				error2 += Math.pow((temp[i] - largeRepresentNum), 2 );
			}
			return (error1 < error2 ? error1 : error2);
		}
		else {
			int error = 0;
			for(int i = 0; i < temp.length; i++) {
				error += Math.pow((temp[i] - smallRepresentNum), 2 );
			}
			return error;
		}
	}
}
