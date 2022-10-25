import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//유클리드 호제법(최대공약수)
public class Q043_1850 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//1로만 구성된 숫자인 A와 B의 최대공약수는 1로만 구성되어있으며 A와 B자릿수의 최대공약수의 길이와 같다. 
		//ex)3 6입력->111 111111->최대공약수: 111 = 3과 6의 최대공약수인 3. 3자리이다. 
		long ALen = Long.parseLong(st.nextToken());
		long BLen = Long.parseLong(st.nextToken());
		
		long result = gcd(ALen, BLen);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i = 0; i < result; i++) {
			bw.write("1");
		}
		bw.flush();
		bw.close();
		
	}
	public static long gcd(long A, long B) {
		if(B == 0)
			return A;
		else {
			if(A > B)
				return gcd(B, A % B);
			else
				return gcd(A, B % A);
		}
	}
}
