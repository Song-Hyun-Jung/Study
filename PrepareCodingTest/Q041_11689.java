import java.io.BufferedReader;
import java.io.InputStreamReader;

//오일러 피함수(1부터 N까지의 범위에서 N과 서로소인 자연수의 개수)-원리는 에라토스테네스의 체원리와 비슷하다. 
/*
 * 1.에라토스테네스의 체원리와 같이 배열을 초기화한다.
 * 2.인덱스와 해당 인덱스의 배열값이 같다면 소수이다. 소수가 아니라면 값변경으로 인해 인덱스와 해당 인덱스의 배열값이 다르다.
 * 3.소수인 경우 이 수의 배수에 해당하는 인덱스의 배열의 값을 P[i] = P[i] - P[i] / k 로 바꾼다. (i는 k의 배수)
 	ex)1~20일때 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20으로 초기화 -> 소수 2 선택 -> 1 1 3 2 5 3 7 4 9 5 11 6 13 7 15
 * 4. 배열의 값은 결국 서로소가 될수있는 후보의 개수. 연산을 통해 후보를 줄여나가는 것이다. ex)f(6)=6 -> 2의 배수로 6 - (6/2) = 3 -> 3의 배수로 3 - (3/3) = 2 =>6이하의 숫자들 중 서로소가 되는것은 1과 5로 2개. 
 * 업데이트 된 배열값에서 이어서 계산해야 중복값 삭제를 막을 수 있다. 
 * GCD(n, k) = 1이 오일러 피함수의 정의를 묻는것
 */
//이 문제에서는 숫자 하나에 해당하는 서로수만 구하면 됨->배열 사용안하고 result 변수로 업데이트
public class Q041_11689 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		long result = N;
		for(long i = 2; i <= Math.sqrt(N); i++) { //제곱근까지만 봐도 됨. N까지로 반복하면 시간초과. 단, <= 주의! <아님
			if(N % i == 0) {
				while(N % i == 0) { //N갱신하기. 중복값 삭제 막기 위해서. 
					N /= i;
				}
				result = result - result / i; //개수 갱신				
			}
		}
		if(N > 1) {
			result = result - result / N; 
		}
		
		System.out.println(result);
	}
}
