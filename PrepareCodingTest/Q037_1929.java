import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정수론(소수) - 에라토스테네스의 체원리 -> 이중 for문을 사용해 O(n^2)같지만 실제로는 최적화 정도에 따라 O(Nlog(log N))으로 소수를 구하는 일반적인 방법
/*
 * 1. 소수를 구하고자 하는 범위까지의 배열을 만듬 ex)1~30중 소수를 구하라 -> 30까지의 배열 만듬
 * 2. 1은 삭제하고 2부터 선택하여 시작하며 배열 끝까지 탐색하며 선택원소의 배수를 모두 삭제한다. ex)2선택->30까지 2의 배수 모두 삭제. 3선택->남아있는 배열의 원소중 3의 배수 모두 삭제
 * 3. 배열 끝까지 탐색 후 남은 수를 출력한다. 
 */
public class Q037_1929 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		
		arr[1] = 0; //첫번째 원소인 1은 삭제
		for(int i = 2; i <= N; i++) { //2부터 N까지 원소 채우기
			arr[i] = i;
		}
		
		for(int i = 2; i <= N; i++) {
			if(arr[i] == 0) //이미 이전 배수 탐색에서 삭제된 원소는 건너뛰기
				continue;
			for(int j = i + i; j <= N; j = j + i) { //선택된 원소의 배수를 삭제하기. j = i+i ***
				arr[j] = 0;
			}
		}
		/* 
		for(int i = 2; i <= Math.sqrt(N); i++) { //제곱근까지만 수행함->시간 단축 가능
			if(arr[i] == 0) //이미 이전 배수 탐색에서 삭제된 원소는 건너뛰기
				continue;
			for(int j = i + i; j <= N; j = j + i) { //선택된 원소의 배수를 삭제하기
				arr[j] = 0;
			}
		}
		*/
		for(int i = M; i <= N; i++) { //M부터 N까지 원소중 삭제되지 않은 원소 출력하기
			if(arr[i] != 0)
				System.out.println(arr[i]);
		}
	
	}
}
