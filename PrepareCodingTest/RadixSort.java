import java.util.*;
import java.io.*;
//RadixSort->기수정렬. 시간복잡도: O(kn) - k는 데이터의 자릿수. 기수 정렬은 값을 놓고 비교할 자릿수를 정한 다음 해당 자릿수만 비교. 
//기수정렬은 10개의 큐를 이용. 각 큐는 값의 자릿수를 대표. ex)일의 자릿수를 기준으로 데이터 저장->일의자릿수 기준으로 데이터 정렬 후에 십의자릿수를 기준으로 같은 과정 반복
//일의자리 비교후 십의자리 비교, 백의자리 비교 이런식으로 오름차순 출력
public class RadixSort {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		radixSort(arr, arr.length);
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < arr.length; i++) {
			bw.write(arr[i] + " ");
		}
		bw.flush();
		bw.close();
	}
	public static void radixSort(int[] arr, int n) {
		Queue<Integer>[] bucket = new LinkedList[10];
		for(int i = 0; i < 10; i++) {
			bucket[i] = new LinkedList<>();
		}
		
		int factor = 1;
		
		for(int i = 0; i < 5; i++) { //5는 최대 숫자 자리수
			for(int j = 0; j < n; j++) {
				bucket[(arr[j] / factor) % 10].add(arr[j]);
			}
			for(int j = 0, k = 0; j < 10; j++) {
				while(!bucket[j].isEmpty()) {
					arr[k++] = bucket[j].poll();
				}
			}
			factor *= 10;
		}
	}

}
