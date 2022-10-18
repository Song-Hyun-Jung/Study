import java.io.BufferedReader;
import java.io.InputStreamReader;

//그리디
public class Q036_1541 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputFormula = br.readLine(); //ex)100-40+50+74-30+29-45+43+11
		
		String[] splitByMinus = inputFormula.split("-"); //ex)(100)-(40+50+74)-(30+29)-(45+43+11) - 기준 split -> -발견부터 다음 -전까지 합을 괄호로 (그리디)
		
		int[] sumByMinusPart = new int[splitByMinus.length];
		for(int i = 0; i < splitByMinus.length; i++) {
			//String[] splitByPlus = splitByMinus[i].split("+"); // Dangling meta character '+' near index 0 오류 발생!
			String[] splitByPlus = splitByMinus[i].split("\\+"); //(40+50+74)->40 + 50 + 74
			int tmpSum = 0;
			for(int j = 0; j < splitByPlus.length; j++) {
				tmpSum += Integer.parseInt(splitByPlus[j]);
			}
			sumByMinusPart[i] = tmpSum;
		}
		
		int sum = sumByMinusPart[0]; //(100) - (164) - (59) - (99) = -222
		for(int i = 1; i < splitByMinus.length; i++) {
			sum -= sumByMinusPart[i];
		}
		
		System.out.println(sum);
	}
}
