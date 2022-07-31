import java.util.Scanner;

public class Student20190972 {
	static int min = 999999;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		int[] items = {0, 1, 2, 3};
		int[] bucket = new int[10];
		
		int[][] switchBtn = new int[10][];
		int[] present = new int[16];
		
		for(int i = 0; i < switchBtn.length; i++)
		{
			int whatSwitch = input.nextInt(); 
			int connectNum = input.nextInt();
			
			switchBtn[whatSwitch] = new int[connectNum];
			for(int j = 0; j < connectNum; j++)
				switchBtn[whatSwitch][j] = input.nextInt();
		}
		for(int i = 0; i < present.length; i++)
			present[i] = input.nextInt();
		
		solution(items, bucket, 10, switchBtn, present);
		System.out.println(min);
		
		input.close();
	}
	public static void solution(int[] items, int[] bucket, int k, int[][] switchBtn, int[] present)
	{
		
		if(k == 0)
		{
			int result = movingClock(bucket, switchBtn, present);
			if(result == -1)
				return;
			else if(result < min)
				min = result;
			
			return;
		}
		
		int lastIndex = bucket.length - k - 1;
		
		for(int i = 0; i < items.length; i++)
		{
			bucket[lastIndex + 1] = items[i];
			solution(items, bucket, k - 1, switchBtn, present);
		}
		
	}
	public static int movingClock(int[] bucket, int[][] switchBtn, int[] present)
	{
		int count = 0;
		int[] copyPresentClock = new int[present.length];
		
		for(int i = 0; i < present.length; i++)
			copyPresentClock[i] = present[i];
		
		for(int i = 0; i < bucket.length; i++)
		{
			if(bucket[i] > 0)
			{
				for(int j = 0; j < switchBtn[i].length; j++)
				{
					int toMoveClock = switchBtn[i][j];
					copyPresentClock[toMoveClock] += bucket[i] * 3;
				}
			}
			count += bucket[i];
		}
		
		for(int i = 0; i < copyPresentClock.length; i++)
		{	
			if(copyPresentClock[i] % 12 != 0 )
				return -1;	
		}
		
		return count;
	}
}
