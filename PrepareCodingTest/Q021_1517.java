import java.io.*;
import java.util.*;
//합병정렬 O(nlogn)으로 해결해야함 버블정렬O(n^2)로는 시간초과. 버블소트에서 swap이 일어나는 조건은 앞의 숫자가 뒤에 숫자보다 클때.
//합병정렬에서 뒤의 배열에 있는 숫자가 앞의 배열에 있는 숫자보다 작을때 merge시 앞으로 이동하는 것과 같은 원리
//ex)3281 -> 23 18 로 +2(+1 + +1), 1238로 +2(1의 인덱스가 2였는데 0의 위치로 왔기 때문. 정렬 전 index - 정렬 후 index) => swap횟수 = 4
//swap의 횟수는 이동한 인덱스 차이와 같다. 
public class Q021_1517 {
	static long result = 0;
	static long[] merged;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] arr = new long[N];
		for(int i = 0; i < N; i++) {
			arr[i] =  Long.parseLong(st.nextToken());
		}
		
		merged = new long[N];
		
		mergeSort(arr, 0, N - 1);
		
		/*for(int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}*/
		
		System.out.println(result);
	
	}
	public static void mergeSort(long[] A, int p, int r) {
		if(p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q); //왼쪽 정렬
			mergeSort(A, q + 1, r); //오른쪽 정렬
			merge(A, p, q, r); //정렬한 두 배열 합병
		}
	}
	public static void merge(long[] A, int p, int q, int r) {
			
		int i = p;
		int j = q + 1;
		int k = p;
		
		while(i <= q && j <= r) {
			if(A[i] > A[j]) {
				result += j - k; //뒷쪽 배열의 숫자값이 작은경우 이동한 인덱스만큼 더해주게 된다. 
				merged[k++] = A[j++];
			}
			else {
				merged[k++] = A[i++]; //앞쪽 배열의 숫자값이 작은경우는 swap이 일어난것으로 보지 않는다.
			}
		}
		while(i <= q) { //남은 원소들 배열에 넣어줌
			merged[k++] = A[i++];
		}
		while(j <= r) {
			merged[k++] = A[j++];
		}
		
		i = p;
		k = p;
		while(i <= r) //변경된 배열을 원래 배열에 반영
		{
			A[i++] = merged[k++];
		}
	}
}
