import java.util.*;
public class Q003_11659 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        int dataNum = sc.nextInt();
        int queryNum = sc.nextInt();
        
        int[] data = new int[dataNum + 1];
        int[] sumData = new int[dataNum + 1];
        data[0] = 0;
        for(int i = 1; i <= dataNum; i++){
            data[i] = sc.nextInt();
            if(i == 0)
                sumData[i] = data[i];
            else
                sumData[i] = sumData[i - 1] + data[i];
        }
        
        for(int i = 0; i < queryNum; i++){
            int startIndex = sc.nextInt();
            int endIndex = sc.nextInt();
            System.out.println(sumData[endIndex] - sumData[startIndex - 1]);
        }
	}

}
