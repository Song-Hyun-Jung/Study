import java.util.*;
import java.io.*;

public class Q007_1940 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
        int N = Integer.parseInt(br.readLine()); 
        int M = Integer.parseInt(br.readLine());
        
        int[] sources = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sources[i] = Integer.parseInt(st.nextToken());
        } 
        Arrays.sort(sources);
        
        int startIdx = 0;
        int endIdx = N - 1;
        int sum = 0;
        int count = 0;                                       
        while(startIdx < endIdx){
        	sum = sources[startIdx] + sources[endIdx];
            if(sum == M){ //양쪽으로 값을 조절해줘야함. 한쪽만 조절하면 합이 M이 안되는건 당연
                count++;
                startIdx++;
                endIdx--;
            }
            else if(sum < M){
                startIdx++;    
            }
            else if(sum > M){
                endIdx--;
                
            }
        }
        System.out.println(count);    
	}

}
