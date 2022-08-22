import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//퀵소트
public class QuickSort {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		quickSort(arr, 0, N - 1);
		
		for(int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	
	public static void quickSort(int[] arr, int p, int r) {
		int q;
		if(p < r) {
			q = partition(arr, p, r); //새로 만든 파티션 위치. 맨끝의 피봇이 이동한 결과 인덱스
			quickSort(arr, p, q - 1); //시작부터 파티션 전까지 정렬 재귀
			quickSort(arr, q + 1, r); //파티션 다음부터 끝까지 정렬 재귀
			
		}
	}
	public static int partition(int[] arr, int p, int r) { //p는 시작 인덱스, r은 마지막 인덱스
		int i  = p - 1; //i는 j보다 하나 앞에서 -> p - 1
		int j = p; //j는 p부터
		int pivot = arr[r];
		
		for(j = p; j <= r; j++) {
			if(arr[j] < pivot) { //arr[i]가 피봇보다 작으면 i증가시키고 swap한뒤 j++
				i++;
				swap(arr, i, j);
			}
			else if(j == r) { //마지막. j가 끝에 다다르면 i증가시키고 피봇과 위치 바꿈
				i++;
				swap(arr, i, r);
			}
		}
		return i; //바뀐 피봇의 위치가 곧 파티션
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
