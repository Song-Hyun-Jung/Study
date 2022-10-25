import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//유클리드 호제법(최대공약수-소인수분해보다 더 간단한 방법, MOD 연산(-두값을 나눈 나머지를 구하는 연산)이 핵심)
//BaekJoon2609참고
//BufferedWriter사용
public class Q042_1934 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testNum = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		int[] result = new int[testNum];
		
		for(int i = 0; i < testNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			result[i] = A * B / gcd(A, B); //최소공배수 = 두 수의 곱 / 최대공약수
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < result.length; i++){
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	//최대공약수 구하기
	public static int gcd(int A, int B) {
		if(B == 0) {
			return A;
		}
		else {
			if(A > B) {
				return gcd(B, A % B);
			}
			else {
				return gcd(A, B % A);
			}
		}
	}
}
