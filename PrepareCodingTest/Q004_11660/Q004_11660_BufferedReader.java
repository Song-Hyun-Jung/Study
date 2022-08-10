import java.io.*;
import java.util.*;
public class Q004_11660 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int arraySize = Integer.parseInt(st.nextToken());
		int queryNum = Integer.parseInt(st.nextToken());
		
		int j;
		int i;
		
		//원본배열 저장
		int[][] array = new int[arraySize + 1][arraySize + 1];
		for(i = 1; i <= arraySize; i++) {
			st = new StringTokenizer(br.readLine());
			for(j = 1; j <= arraySize; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//printArray(array, arraySize);
		
		//구간합 구하기 방법1)
		int[][] sumArray = new int[arraySize + 1][arraySize + 1];

		for(i = 1; i <= arraySize; i++) {
			for(j = 1; j <= arraySize; j++) {
				sumArray[i][j] = sumArray[i][j - 1] + sumArray[i - 1][j] - sumArray[i - 1][j - 1] + array[i][j];
			}
		}
		
		//printArray(sumArray, arraySize);
		
		/*
		//구간합 구하기 방법2)-시간초과
		//첫행, 열 배치 방법1
		j = 1;
		for(i = 1; i <= arraySize; i++) { 
			if(i == 1) 
				sumArray[i][j] = array[i][j];
			else {
				sumArray[i][j] = sumArray[i - 1][j] + array[i][j]; //세로(행) (0,0)(1,0)(2,0)...
				sumArray[j][i] = sumArray[j][i - 1] + array[j][i]; //가로(열) (0,0)(0,1)(0,2)...
			}
		}
		
		//첫행, 열 배치 방법2
		j = 1;
		for(i = 1; i <= arraySize; i++) { //세로(행) (0,0)(1,0)(2,0)...
			if(i == 1) 
				sumArray[i][j] = array[i][j];
			else
				sumArray[i][j] = sumArray[i - 1][j] + array[i][j];
			
		}
		i = 1;
		for(j = 2; j <= arraySize; j++) { //가로(열) (0,1)(0,2)(0,3)..
			sumArray[i][j] = sumArray[i][j - 1] + array[i][j];	
		}
	
		
		//중간 구간합 구하기
		i = 1; 
		j = 1;
		for(i = 2; i <= arraySize; i++) {
			for(j = 2; j <= arraySize; j++) {
				sumArray[i][j] = sumArray[i - 1][j] + sumArray[i][j - 1] - sumArray[i - 1][j - 1] + array[i][j];
			}
		}
		
		//printArray(sumArray, arraySize);
		*/
	
		
		//쿼리 개수만큼 반복 
		for(i = 0; i < queryNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = 
					sumArray[x2][y2] - sumArray[x1 - 1][y2] - sumArray[x2][y1 - 1] + sumArray[x1 - 1][y1 - 1];
			System.out.println(result);
		}
	}
	
	public static void printArray(int[][] a, int arraySize) {
		for(int i = 1; i <= arraySize; i++) {
			for(int j = 1; j <= arraySize; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
