import java.io.*;
import java.util.*;
//합병정렬 O(nlogn), 배열 동적할당이 시간 초과 문제였음
public class Q020_2751 {
	static int[] merged;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		merged = new int [N]; //한번만 만들고 재사용!
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		mergeSort(arr, 0, N - 1);
		
	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			bw.write(arr[i] + "\n");
		}
		bw.flush();
		
		
		/*
		 //방법2) Collections.sort()
		 //Arrays.sort()는 오류남-테스트 케이스 중에 최악인 경우가 있음 O(n^2)
		 //Arrays.sort()는 정렬 방식이 DualPivotQuickSort 평균O(nlog(n)) 최악 O(n^2)
		 //Collection.sort()는 TimeSort(삽입정렬 + 합병정렬) 평균과 최악 모두 O(nlogn)
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(arr);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			bw.write(arr.get(i) + "\n");
		}
		bw.flush();
		*/
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
		
		int i = p;
		int j = q + 1;
		//int[] merged = new int[A.length] ->매번 동적할당을 하면 데이터 길이가 길 때 부담이 됨! 시간초과- 메모리 초과문제.
		int k = 0;
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
		k = 0;
		while(i <= r) {
			A[i++] = merged[k++]; //merged의 원소들을 원래 배열인 A에 반영해줌
		}
	}


}
