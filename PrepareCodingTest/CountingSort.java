import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.StringTokenizer;
//CountingSort-시간복잡도 O(n) 
//But, 메모리가 낭비된다. 예를들어 10개의 수인데 수의 범위가 1억까지라면 낭비. 수열의 길이보다 수의 범위가 극단적으로 크면 메모리가 낭비된다. 
//counting 배열의 의미는 같은 숫자가 몇개 있는지 -> counting배열을 구간합 하고 counting 구간합 배열의 각 값은 해당 인덱스에 해당하는 숫자의 시작위치-1을 알려준다. 
//ex) array[7, 2, 3, 5, 7, 1, 4, 6, 7, 5, 0, 1]
//counting구간합[1, 3, 4, 5, 6, 8, 9, 12]
//1의 시작위치 = 구간합에서 인덱스1의 값 - 1 = 3 - 1 = 2 -> result에서 인덱스 2까지 1이 위치한다.
public class CountingSort {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] =  Integer.parseInt(st.nextToken());
		}
		
		countingSort(arr, N);
		
		
	}
	
	public static void countingSort(int[] arr, int N) {
		int[] counting = new int[100000]; //수의 범위
		int[] result = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			counting[arr[i]]++; //정렬하고자 하는 배열의 원소 숫자를 인덱스로 하는 counting배열 값 증가 ex)arr[0] = 2; counting[2]++; 배열에 같은 숫자가 몇개 있는지 알기 위함
		}
		
		for(int i = 1; i < counting.length; i++) { //구간합. 누적합 -> 누적합의 개수는 같은 숫자의 원소가 몇개 있는지
			counting[i] += counting[i - 1];
		}
		
		for(int i = 0; i <= arr.length - 1; i++) {
			int value = arr[i]; //정렬하고자 하는 배열의 원소
			counting[value]--; //누적합에서 해당하는 원소의 개수 감소시킴
			result[counting[value]] = value; //결과 배열에서 해당 원소의 위치는 같은 원소의 개수 - 1. 위에서 -1했으므로 인덱스로 넣어주기. 
		}
		
		//출력
		
		for(int i = 0; i < N; i++) {
			System.out.println(result[i]);
		}
	}
	

}
