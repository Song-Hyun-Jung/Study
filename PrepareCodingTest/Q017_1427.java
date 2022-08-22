import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//내림차순 정렬하기-선택정렬 O(n^2)
public class Q017_1427 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String nums = br.readLine();
		int N = nums.length();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(nums.substring(i, i + 1)); //Integer.parseInt는 String 타입을 숫자형으로 바꿔주는것. char는 안된다. 
			//substring은 beginIndex부터 endIndex - 1까지 반환
		}
		
		for(int i = 0; i < N; i++) { //마지막 요소는 자동으로 정렬된다. 
			int max = Integer.MIN_VALUE;
			int maxIndex = 0;
			for(int j = i + 1; j < N; j++) { //현재 원소 다음부터 최대값 찾기
				if(arr[j] > max) {
					max = arr[j];
					maxIndex = j;
				}
			}
			if(arr[i] < max) //만약 현재원소가 최대값보다 작다면 최대값이 앞으로 오도록 자리 바꾸기
				swap(arr, i, maxIndex);
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(arr[i]);
		}
		
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	

}
