import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유클리드 호제법(최대공약수-소인수분해보다 더 간단한 방법, MOD 연산(-두값을 나눈 나머지를 구하는 연산)이 핵심)
/*
 * 1.큰수를 작은 수로 나누는 MOD 연산
 * 2.1단계에서의 작은수와 MOD연산결과값으로 다시 MOD연산
 * 3.나머지가 0이되는 순간의 작은수가 최대공약수.
 * ex)gcd(270,192)->270%192=78 -> 192%78=36->78%36=6->36%6=0=>최대공약수는 6
 */
//최대 공약수는 두 자연수의 공통된 약수 중 가장 큰수
//최소 공배수는 두 자연수의 공통된 배수 중 가장 작은수-> 최소공배수 = 두 자연수의 곱 / 최대 공약수
public class BaekJoon2609 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		//최대 공약수 구하기
		int gcdResult = gcd(A, B);
		System.out.println(gcdResult);
		/*
		//최대공약수 구하기->재귀 사용X
		while(B > 0) {
			int tmp = B;
			B = A % B;
			A = tmp;
		}
		System.out.println(A);
		*/
		
		//최소 공배수 = 두자연수의 곱 / 최대공약수
		System.out.println(A * B / gcdResult);
		
	}
	
	public static int gcd(int A, int B) { //최대공약수 구하기(재귀 사용)
		if(B == 0) { // 6 % 0 => 6
			return A;
		}
		if(A > B) {
			return gcd(B, A % B);
		}
		else {
			return gcd(A, B % A);
		}
	}
	
}
