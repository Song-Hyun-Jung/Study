import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정수론(제곱)-에라토스테네스의 체원리 *****
public class Q040_1016 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		long[] arr = new long[(int)(max - min + 1)];
		/*
		 *굳이 값 넣을 필요 없다. 
		long tmp = min;
		for(int i = 0; i < arr.length; i++) {
			arr[i] = tmp;
			tmp++;
		}
		*/
		//제곱수 도출
		for(long i = 2; i * i <= max; i++) { 
			long pow = i * i; //제곱수
			long startIdx = min / pow; //제곱ㄴㄴ수시작(어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱ㄴㄴ수라고 한다.) 
			//ex)17-1000 일때 i=2라면 startIdx = 17 / 4 = 4, 나머지1 -> startIdx++해야 startIdx = 5 -> min보다 큰 제곱수인 25부터 시작하게 됨. 
			if(min % pow != 0) {//나누어 떨어지지 않음. 나머지가 있으면 1을 더해야 min보다 큰 제곱수에서 시작됨. 없으면 런타임 에러남
				startIdx++;
			}
			for(long j = startIdx; pow * j <= max; j++) { //j = 5 -> 4 * 5 = 20 -> arr[3] = -1; ->제곱수 ㄴㄴ
				arr[(int)((j * pow) - min)] = -1;
			}
		}
		
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != -1) {
				count++;
			}
		}
		System.out.println(count);
	}
}
