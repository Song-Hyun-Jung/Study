import java.io.*;
import java.util.*;
//DFS-재귀
public class Q024_2023 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //자리수
		
		DFS(2, 1);
		DFS(3, 1);
		DFS(5, 1);
		DFS(7, 1);
	}
	
	public static void DFS(int s, int n) { //시작숫자와 현재 자리수
		if(n == N) { //현재 자리수가 N이 되고 소수이면 출력
			if(check(s) == true)
				System.out.println(s);
			return;
		}
		for(int i = 1; i <= 9; i++) { //맨마지막 자리의 수가 홀수이고 결과가 소수이면 다음 자리수 탐색
			if(i % 2 == 1 && check(s * 10 + i) == true)
				DFS(s * 10 + i, n + 1);
		}
	}
	//소수인지 확인하는 함수
	public static boolean check(int num) {
		for(int i = 2; i <= num / 2; i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
}
