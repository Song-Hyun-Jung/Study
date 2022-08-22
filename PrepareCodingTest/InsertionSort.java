import java.io.*;
import java.util.StringTokenizer;
//삽입정렬 O(n^2)
public class InsertionSort {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
			int target = arr[i];
			int j = i - 1;
			while(j >= 0 && target < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = target;
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
}
