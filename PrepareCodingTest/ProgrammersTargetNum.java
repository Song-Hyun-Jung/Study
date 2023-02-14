
public class ProgrammersTargetNum {
    public int[] arr;
    int count = 0;

    public int solution(int[] numbers, int target) {
        arr = numbers;
        
        dfs(0, target, 0);
        int answer = count;
        return answer;
    }
    public void dfs(int start, int target, int sum){

        if(start == arr.length){
            if(sum == target){
                count++;
            }
            return;
        }
       
        dfs(start + 1, sum + arr[start], target);
        dfs(start + 1, sum - arr[start], target);
            
        
    }
}
