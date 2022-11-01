import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//확장 유클리드 호제법->방정식의 해를 구한다. 구현은 재귀로 <-> 유클리드 호제법(최대공약수와 최소공배수 구할때 사용)
//ax + by = c	이때 c는 a와 b의 최대공약수의 배수이다. 
//1. a와 b의 최대공약수를 구한다. gcd(a, b) = n이라 하면
//2. ax + by = n으로 변경한다.
//3. 다시 유클리드 호제법으로 gcd(a, b)하면서 몫과 나머지를 저장한다.
//4. x = y' & y = x' - y' * q(몫) 으로 거꾸로 계산해서 올라간다. 첫시작은 x' = 1 y' = 0 로 시작한다.
//5. 그렇게 최종 x와 최종 y를 구해나간다.
//6. c / gcd(a, b)해서 나온것을 k라 하면 k * 최종x & k * 최종y가 방정식의 해가 된다. 
//cf) ax + by = c에서 c가 a,b최대공약수의 배수가 아니라면 정수의 범위가 아님. ->먼저 배수가 맞는지 확인해야함! 아니라면 -1

/*
 * ex) 5x + 9y = 2 -> gcd(5, 9) = 1 -> 5x + 9y = 1로 시작. 2는 1의 배수
 * 유클리드로 나머지와 몫을 구해나간다. 
 * 25 % 9 = 5 ->나머지 5 몫 0
 *		9 % 5 = 4 -> 나머지 4 몫 1
 *			5 % 4 = 1 -> 나머지 1 몫 1
 *				4 % 1 = 0 -> 나머지 0 몫 4
 *
 *5 0	x = y' = 2	y = x' - y' * 몫 = -1 -2 * 0 = -1	=>최종x = 2 최종y = -1
 *4 1	x = y' = -1 y = x' - y' * 몫 = 1 -(-1 * 1) = 2
 *1 1 	x = y' = 1  y = x' - y' * 몫 = 0 - 1 * 1 = -1
 *0 4	x = y' = 0	y = x' - y' * 몫 = 1 - 0 * 4 = 1
 *	(시작) x' = 1 y' = 0
 *
 *c / gcd(5, 9) = 2 -> 방정식의 해 x = 2*2 = 4 , y = 2*-1 = -2
 *
 */
//백준 채점 불가
public class Q045_21568 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		
		long gcdResult = gcd(a, b);
		if(c % gcdResult != 0){
			System.out.println("-1");
		}
		else {
			long k = c / gcdResult;
			long[] ret = equation(a, b);
			System.out.println(k * ret[0] + " " + k * ret[1]);
		}
		
	}
	public static long[] equation(long a, long b) {
		long[] ret = new long[2];
		if(b == 0) {
			ret[0] = 1;
			ret[1] = 0; //x'은 1 y'은 0으로 시작
			return ret;
		}
		long q = a / b; //몫 구하기	->나머지는 필요가 없다. ret에 x, y만 신경쓰면 됨
		long[] primeResult = equation(b, a % b); //몫을 구해야함. 이전 값들을 가져오기 위해  gcd 구하듯 매개변수전달은 이렇게 해야함.
		ret[0] = primeResult[1]; //x = y'
		ret[1] = primeResult[0] - primeResult[1] * q; // y = x' - y' * q
		
		return ret;
	}
	public static long gcd(long a, long b) {
		if(b == 0) {
			return a;
		}
		else {
			if(b > a) {
				return gcd(a, b % a);
			}
			else{
				return gcd(b, a % b);
			}
		}
	}
}
