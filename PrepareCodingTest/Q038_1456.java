import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정수론(소수)-에라토스테네스 체원리, 이항정리 *****
//범위 지정이 중요했음
public class Q038_1456 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long M = Long.parseLong(st.nextToken());
		long N = Long.parseLong(st.nextToken()); //입력값이 10^14까지로 int범위를 넘음 int->2*10^9까지. 배열의 최대 크기는 int범위까지
		
		//배열 초기화-10^14의 제곱근인 10^7까지만. 10^7 의 제곱은 10^14 이므로 10^7 보다 큰 수들은 소수 여부를 판별하지 않아도 된다.
				int[] arr = new int[10000001]; 
				for(int i = 2; i < arr.length; i++) {
					arr[i] = i;
				}
				
				for(int i = 2; i <= Math.sqrt(arr.length); i++) { //소수인것만 고르기-에라토스테네스 체원리
					if(arr[i] == 0)
						continue;
					for(int j = i + i; j < arr.length; j = j + i) {
						arr[j] = 0;
					}
				}
				
				/*
				int cnt = 0;
				for(int i = M; i <= N; i++) {
					if(arr[i] != 0) {
						int k = 2;
						while(Math.pow(arr[i], k) <= N) { //int범위를 벗어남 오류날수있음!
							if(Math.pow(arr[i], k) >= M)
								cnt++;
							k++;
						}
					}
				}
				
				System.out.println(cnt);
				*/
				
				int cnt = 0;
				for(int i = 2; i < 10000001; i++) {
					if(arr[i] != 0) {
						long tmp = arr[i]; //ex)tmp = 2
						//long은 정수형 double은 실수형! -> 나눗셈할때 long쓰면 틀림...
						while((double)arr[i] <= (double) N / (double) tmp) { //이항정리->(arr[i])^k vs B 비교 -> 양변을 (arr[i])^(k-1)로 나눔 -> arr[i] vs B / (arr[i])^(k-1)
							if((double)arr[i] >= (double) M / (double) tmp) // 2 <= 30 / 2, 2 >= 1 / 2 -> arr[i]가 2, 4, 8일때 cnt++ -> tmp는 4, 8, 16 16일때 조건 불만족. arr[i] = 3
								cnt++; 
							tmp = tmp * arr[i]; //제곱  
						}
						
					}
				}
				
				System.out.println(cnt);
			}
}
