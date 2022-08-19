import java.io.*;
import java.util.Arrays;
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
		
		Arrays.sort(arr); //value기준으로 정렬
		
		for(int i = 0; i < N; i++) { //기존 index에서 새로 정렬되어 변경된 index를 뺀다. 
			result[i] = arr[i].index - i; //ex) 10 1 5 2 3 에서 2의 인덱스는 3. 정렬 후 1 2 3 5 10 에서 2의 인덱스 => 2
		}
		
		int max = -1;
		for(int i = 0; i < N; i++) { //인덱스 차를 저장한 배열에서 최대값 찾기.
			if(max < result[i])
				max = result[i];
		}
		
		System.out.println(max + 1); //swap이 일어나지 않는 반복문이 한번 더 실행되는 것을 감안해 최댓값에 1을 더함
	}
	
	public static class arrSet implements Comparable<arrSet>{ 
		//커스텀 클래스 타입 배열 만들기 위함. Arrays.sort 정렬 위해 compareTo넣어줘야
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
