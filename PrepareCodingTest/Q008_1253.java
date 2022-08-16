import java.io.*;
import java.util.*;
public class Q008_1253{
    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Long[] arr = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){ //마이너스 값도 받는듯하다.
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        long count = 0;
        int i, j;
        for(int k = 0; k < N; k++){
            long target = arr[k];
            i = 0;
            j = N - 1;
            while(i < j){
                if(arr[i] + arr[j] == target){
                	if(i != k && j != k) {
	                    count++;
	                    break;
                	}
                	else if(i == k) {
                		i++;
                	}
                	else if(j == k) {
                		j--;
                	}
                }
                else if(arr[i] + arr[j] < target){
                    i++;
                }
                else if(arr[i] + arr[j] > target){
                    j--;
                }
            }
        }
        System.out.println(count);
    }
}
