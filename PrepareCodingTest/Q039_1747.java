import java.io.BufferedReader;
import java.io.InputStreamReader;

//정수론(소수)-에라토스테네스의 체원리, palindrome
public class Q039_1747 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//초기화
		int arr[] = new int[10000001];
		for(int i = 2; i < arr.length; i++) {
			arr[i] = i;
		}
		
		//에라토스테네스의 체
		for(int i = 2; i < arr.length; i++) { //for(int i = 2; i < Math.sqrt(arr.length); i++)
			if(arr[i] == 0)
				continue;
			for(int j = i + i; j < arr.length; j = j + i) {
				arr[j] = 0;
			}
		}
		
		//N보다 크고, 소수이면서, 팰린드롬인것중 가장 작은것 출력
		for(int i = N; i < arr.length; i++) {
			if(arr[i] != 0){
				if(isPalindrome(arr[i])) {
					System.out.println(arr[i]);
					break;
				}
			}
		}
		
	}
	
	//팰린드롬 확인 함수
	public static boolean isPalindrome(int n) {
		String num = String.valueOf(n);
		int endIdx = num.length() - 1;
		int startIdx = 0;
		while(startIdx < endIdx) {
			if(num.charAt(startIdx) != num.charAt(endIdx)) { //처음과 끝인덱스 문자 하나씩 비교
				return false;
			}
			startIdx++;
			endIdx--;
		}
		return true;
	}
}
