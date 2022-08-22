import java.util.*;
import java.io.*;
//오름차순 선택정렬 O(n^2)
public class SelectionSort {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < N; i++) { //마지막 요소는 자동으로 정렬된다. 
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			for(int j = i + 1; j < N; j++) { //현재 원소 다음부터 최소값 찾기
				if(arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}
			if(arr[i] > min) //만약 현재원소가 찾은 최소값보다 크다면 최소값이 앞으로 오도록 자리 바꾸기
				swap(arr, i, minIndex);
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
