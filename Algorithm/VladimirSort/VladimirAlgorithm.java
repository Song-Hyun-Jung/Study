import java.util.Random;

public class VladimirAlgorithm {
	
	public static void swap(int[] A, int a, int b)
	{
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	public static void insertionSort(int[] A, int p, int r)
	{
		int i, j, m, k;
		for(i = p; i <= r; i++)
		{
			for(j = p; j < i; j++)
				if(A[j] > A[i]) 
					break;
			m = A[i];
			
			for(k = i; k > j; k--)
				A[k] = A[k - 1];
			A[j] = m;
		}
	}
	public static void vladimirSort(int[] A, int p, int r, int n){
		
		int pivot1, pivot2;
		int L, K, G;
		
		if(p > r) return;
		
		if (r - p <= n) {
    		insertionSort(A, p, r);
    		return;
		}
		
		//가장 왼쪽이 작은 pivot, 오른쪽이 큰 pivot 
		if(A[p] > A[r])
			swap(A, p, r);
		
		pivot1 = A[p];
		pivot2 = A[r];
		
		L = p + 1;
		K = p + 1;
		G = r - 1;
		
		for(K = p + 1; K <= G; K++)
		{
			if(A[K] <= pivot1) 
			{
				swap(A, K, L);
				L++;
			}
			else if(A[K] >= pivot2) 
			{
				while(A[G] > pivot2 && K < G)
					G--;
				swap(A, K, G);
				G--;
				
				if(A[K] <= pivot1)
				{
					swap(A, K, L);
					L++;
				}
			}
		}
		
		L--;
		G++;
		swap(A, p, L);
		swap(A, r, G);
		
		vladimirSort(A, p, L - 1, n);
		vladimirSort(A, L + 1, G - 1, n);
		vladimirSort(A, G + 1, r, n);
	}

	public static void main(String args[]) {
		int[] arraySize = {10, 100, 200, 400, 800, 1600, 3200, 6400};
		int[] n = {17, 33, 65, 129, 257, 513};
		int i, j, k;
		Random random = new Random();
		
		for(k = 0; k < n.length; k++) {
			for(i = 0; i < arraySize.length; i++)
			{
				//초기화
				int A[] = new int[arraySize[i] * 1000];
				for(j = 0; j < A.length; j++)
				{
					A[j] = random.nextInt(10000);
					//System.out.print(A[j] + " ");
				}
		
				//시간측정
				long startTime = System.currentTimeMillis();
				vladimirSort(A, 0, A.length - 1, n[k]);
				//for(j = 0; j < A.length; j++) System.out.print(A[j] + " ");
				long endTime = System.currentTimeMillis();
				System.out.println("n-" + n[k] + " & array크기-" + arraySize[i] + "k 일때: " + (endTime - startTime) + "ms");
				
			}
		}
	}

}
