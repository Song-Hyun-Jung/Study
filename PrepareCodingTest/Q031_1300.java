import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//이진탐색*
public class Q031_1300 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		long K = Long.parseLong(br.readLine());
		
		/*
		 //메모리 초과
		int[] newArr = new int[N*N];
		int k = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				newArr[k] = i * j;
				k++;
			}
		}
	
		Arrays.sort(newArr);
		System.out.println(newArr[K - 1]);
		*/
		
		//N = 3이면 1 2 3 / 2 4 6 / 3 6 9 이렇게 각 행이 배수로 저장됨
		long start = 1;
		long end = K;
		long answer = 0;
		while(start <= end) {
			long mid = (start + end) / 2;
			long cnt = 0; //중앙값 보다 작은 수가 몇개인지->K번째 수 ex)7번째 수라면 작은수가 6개가 되어야함
			for(int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N); // 중앙값4->min(4/1, 3) + min(4/2, 3) + min(4/3, 3) = 6
			}
			
			if(cnt < K) { //6 < 7
				start = mid + 1;
			}
			else{
				answer = mid; //현재 정답에 중앙값 저장
				end = mid - 1;

			}
			
		}
		System.out.println(answer);
	}
}
