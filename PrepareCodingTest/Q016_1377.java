import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
//버블 정렬
//버블정렬로 분류되어있지만 퀵소트 + 정렬후 인덱스 차이로 푸는 문제
public class Q016_1377 {
	
	/*
	//시간초과-이유:O(n^2) 버블정렬 O(nlogn)에 끝낼 수 없음.
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		boolean check = false;
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1 - i; j++) {
				if(arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					check = true;
				}
			}
			if(check == false) {
				count++;
			}
			else {
				check = false;
			}
		}
		
		System.out.println(count);
	}
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	*/

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] result = new int[N];
		arrSet[] arr = new arrSet[N];
		for(int i = 0; i < N; i++) {
			arr[i] = new arrSet(i, Integer.parseInt(br.readLine()));
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N; i++) {
			result[i] = arr[i].index - i;
		}
		
		int max = -1;
		for(int i = 0; i < N; i++) {
			if(max < result[i])
				max = result[i];
		}
		
		System.out.println(max + 1);
	}
	
	public static class arrSet implements Comparable<arrSet>{ //커스텀 클래스 타입 배열 만들기 위함. Arrays.sort 정렬 위해 compareTo넣어줘야
		int index;
		int value;
		
		public arrSet(int index, int value) {
			this.index = index;
			this.value = value;
		}
		
		@Override
		public int compareTo(arrSet o) {
			return this.value - o.value;
		}
	}
}
