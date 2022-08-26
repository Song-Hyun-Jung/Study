import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//RadixSort로 분류되어있지만 RadixSort + CountingSort 누적합 이용
public class Q022_10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		radixSort(arr, 5);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			bw.write(arr[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void radixSort(int[] A, int max_size) {
		int[] output = new int[A.length];
		int jarisu = 1;
		int count = 0;
		
		while(count != max_size) {//최대 자릿수만큼 반복
			int[] bucket = new int[10];
			for(int i = 0; i < A.length; i++) {
				bucket[(A[i] / jarisu) % 10]++; //일의 자리부터 시작-해당 자리수 숫자 카운팅++
			}
			for(int i = 1; i < 10; i++) {
				bucket[i] += bucket[i - 1]; //누적합
			}
			for(int i = A.length - 1; i >= 0; i--) {
				output[bucket[(A[i] / jarisu % 10)] - 1] = A[i]; //카운팅 소트
				bucket[(A[i] / jarisu) % 10]--;
			}
			for(int i = 0; i < A.length; i++) {
				A[i] = output[i];
			}
			jarisu = jarisu * 10;
			count++;
			
		}
			
	}
}
