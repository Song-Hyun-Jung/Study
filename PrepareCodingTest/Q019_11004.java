import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//퀵소트-다시 시도 필요
public class Q019_11004 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//quickSort(arr, 0, N - 1, K);
		
		Arrays.sort(arr);
	
		System.out.println(arr[K - 1]);
		
	}
	
	public static void quickSort(int[] arr, int p, int r, int K) {
		if(p >= r) return;
		int pivot = partition(arr, p, r);
		if(K == pivot + 1) return;
		if(K < pivot + 1) quickSort(arr, p, pivot - 1, K);
		else quickSort(arr, pivot + 1, r, K);
		
	}
	public static int partition(int[] arr, int p, int r) { //p는 시작 인덱스, r은 마지막 인덱스
		int M = (p + r) / 2;
		swap(arr, p, M);
		int pivot = arr[p];
		int i = p;
		int j = r;
		
		while(i < j) {
			while(pivot < arr[j]) {
				j--;
			}
			while(i < j && pivot >= arr[i]) {
				i++;
			}
			swap(arr, i, j);
		}
		arr[p] = arr[i];
		arr[i] = pivot;
		return i;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
