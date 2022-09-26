import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//이진탐색-데이터가 정렬되어있는 상태에서 원하는 값을 찾아냄. 중앙값과 찾는 값을 비교. O(logn)
//여기서는 n개의 데이터를 찾음. O(nlogn)
public class Q029_1920 {
	public static int[] arr;
	public static int[] findList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int toFind = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		findList = new int[toFind]; //찾을 원소 리스트 
		for(int i = 0; i < toFind; i++) {
			findList[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); //이진 탐색을 하려면 정렬이 되어있어야 함. 
		
		for(int k = 0; k < toFind; k++) {
		int start = 0;
		int end = arr.length - 1;
		boolean isFind = false;
		while(start <= end) {
			int midIdx = (start + end) / 2;
			int mid = arr[midIdx];
			if(mid == findList[k]) { //찾은 경우. 
				isFind = true;
				break;
			}
			else if(findList[k] < mid) { //중간값보다 작은 범위만 탐색
				end = midIdx - 1;
			}
			else { //중간값보다 큰 범위만 탐색
				start = midIdx + 1;
			}
		}
		
		if(isFind == true) //찾으면 1 출력
			System.out.println(1);
		else
			System.out.println(0);
		}
	}
	
	

}
