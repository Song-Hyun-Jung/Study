import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//퀵정렬-Partition코드 유의
public class Q019_11004 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		quickSort(A, 0, N - 1, K - 1);
		System.out.println(A[K - 1]);
	}
	
	public static void quickSort(int[] A, int S, int E, int K) {
		if (S < E) {
			int pivot = partition(A, S, E);
			if (pivot == K) return;
			else if (K < pivot)
				quickSort(A, S, pivot - 1, K);
			else
				quickSort(A, pivot + 1, E, K);
		}
	}
	
	public static int partition(int[] A, int S, int E) {
		if (S + 1 == E) { //2-1. [1, 2] 넘어옴. 
			if (A[S] > A[E]) //2-1. 일어나지 않음. 정렬 종료
				swap(A, S, E);
			return E;
		}

		int M = (S + E) / 2; //1. 4 1 2 3 5 에서 pivot 2			2-2.[4, 3, 5]넘어옴 피봇은 3
		swap(A, S, M); //1. 2 1 4 3 5 		2-2.[3, 4, 5]가 됨
		int pivot = A[S];
		int i = S + 1, j = E; //1. i는 1 j는 5	2-2.i는 4 j는 5가 됨
		while (i <= j) {
			while (pivot < A[j] && j > 0) { //1. j의 위치가 1로 옴		2-2.j는 3까지 내려옴
				j--;
			}
				
			while (pivot > A[i] && i < A.length - 1) { //1. i의 위치가 4로 감 	2-2.i는 변함 없음. 그대로 4
				i++;
			}

			if (i <= j) { //1. i가 j보다 크므로 엇갈려서 실행되지 않음		2-2.i가 j보다 크므로 엇갈려서 실행되지 않음.  		cf)예제가 5 4 6 7 2 9 1에 pivot이 7 인경우 실행됨 
				swap(A, i++, j--);
			}
			
		}
		
		A[S] = A[j]; //1. A[j]는 1. 1 1 4 3 5	2-2.j도 3의 위치 S도 3의 위치이므로 실행되도 의미 없음. 
		A[j] = pivot; //1. 1 2 4 3 5
		return j; //1. 인덱스 1 return.
	}
	
	public static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

}
