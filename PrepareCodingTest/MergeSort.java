import java.io.*;
import java.util.*;
//합병정렬 O(nlogn)
public class MergeSort {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		mergeSort(arr, 0, N - 1);
		
		for(int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void mergeSort(int[] A, int p, int r) {
		if(p < r) {
			int q = (p + r) / 2; //중간지점
			mergeSort(A, p, q); //왼쪽 정렬
			mergeSort(A, q + 1, r); //오른쪽 정렬
			merge(A, p, q, r); //정렬한 두 배열 합병
		}
		return;
	}
	
	public static void merge(int[] A, int p, int q, int r) {
		int[] merged = new int[A.length];
		int i = p;
		int j = q + 1;
		
		int k = p;
		while(i <= q && j <= r) {
			if(A[i] <= A[j]) {
				merged[k++] = A[i];
				i++;
			}
			else {
				merged[k++] = A[j];
				j++;
			}
		}
		
		while(i <= q) { //i나 j 둘중 하나가 남는 경우
			merged[k++] = A[i++];
		}
		while(j <= r) {
			merged[k++] = A[j++];
		}
		
		i = p;
		k = p;
		while(i <= r) {
			A[i++] = merged[k++]; //merged의 원소들을 원래 배열인 A에 반영해줌
		}
	}



}
