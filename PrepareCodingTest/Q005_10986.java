import java.util.*;
import java.io.*;
//부분합
public class Q005_10986 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
    //BufferedReader 사용. 속도 Scanner의 1/3정도로 빠름
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		long[] sumData = new long[N];
		for(int i = 0; i < N; i++) {
			if(i == 0) 
				sumData[i] = Integer.parseInt(st.nextToken());
			else
				sumData[i] = sumData[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		long count = 0;
		long[] C = new long[M]; //나머지가 같은 인덱스의 개수 카운팅 ex)M이 3이면 0, 1, 2 
		for(int i = 0; i < N; i++) {
			int remainder = (int)(sumData[i] % M);
			if(remainder == 0) 
				count++;
			
			C[remainder]++; //나머지별 개수 증가
		}
		
		for(int i = 0; i < M; i++) {
			if(C[i] > 1) {
				count = count + (C[i] * (C[i] - 1) / 2); //2개 뽑기
			}
		}
		
		System.out.println(count);
    }
		*/
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		long[] sumData = new long[N];

		for(int i = 0; i < N; i++) {
            if(i == 0) 
            	sumData[i] = sc.nextInt();
            else
			    sumData[i] = sumData[i - 1] + sc.nextInt();
		}

		long[] C = new long[M]; //나머지가 같은 인덱스의 개수 카운팅 ex)M이 3이면 0, 1, 2 
	    long count = 0;
		for(int i = 0; i < N; i++) {
			int remainder = (int)(sumData[i] % M);
			if(remainder == 0) 
				count++;
            
			C[remainder]++; //나머지별 개수 증가
		}
		
		for(int i = 0; i < M; i++) {
			if(C[i] > 1) {
				count = count + (C[i] * (C[i] - 1) / 2); //2개 뽑기
			}
		}
		
		System.out.println(count);
    }
}
