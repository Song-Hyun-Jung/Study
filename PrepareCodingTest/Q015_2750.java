import java.io.*;
//버블정렬
public class Q015_2750 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < N - 1 - i; j++) {
				if(arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
