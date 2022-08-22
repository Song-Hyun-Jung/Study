import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//방법1)삽입정렬, 방법2)퀵소트 + 부분합
public class Q018_11399 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		/*
		//방법1) 삽입정렬 - O(n^2)안에 들어온다면 다른 정렬을 사용해도 무관
		for(int i = 1; i < N; i++) {
			int target = arr[i];
			int j = i - 1;
			while(j >= 0 && target < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = target;
		}
		*/
		
		//퀵소트 + 부분합 -> O(n)
		Arrays.sort(arr);
		
		int total = 0;
		int tempTotal = 0;
		int[] sumTotal = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			sumTotal[i] = sumTotal[i - 1] + arr[i];
			total += sumTotal[i];
		}
		
		/*
		for(int i = 1; i <= N; i++) {
			System.out.print(sumTotal[i] + " ");
		}
		*/
		
		System.out.println(total);
	}

}
