import com.sun.deploy.util.ArrayUtil;
import javafx.scene.Parent;
import sun.rmi.runtime.Log;


public class Logic {
    private static int[][] board;
    private static int size;

    // 判断黑棋和白棋的获胜方式
    // 行

    Logic(int[][] board,int size){
        this.size = size;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    public static int check(int[] data){
        String text = arrToString(data);
        String blackWin = "22222";
        String whiteWin = "11111";
        if (text.contains(blackWin)){
            return 2;
        }else if (text.contains(whiteWin)){
            return 1;
        }else{
            return 0;
        }
    }


    public int checkRow(){
        for (int i = 0; i < size; i++) {
            int[] curRow = board[i];
            int result = check(curRow);
            if (result != 0){
                return result;
            }
        }
        return 0;
    }

    public int checkColumn(){
        int[] curColumn = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curColumn[j] = board[j][i];
            }
            int result = check(curColumn);
            if (result != 0){
                return result;
            }
        }
        return 0;
    }

    public int checkDiagonal(){
        int[] curDiagonal = new int[size];
        int startX=size-1,startY=0,endX=size-1,endY=0;
        for (int i = 0; i < size-1; i++) {
            int x = startX;
            int y = startY;
            int count = 0;
            while (x <= endX){
                curDiagonal[count] = board[x][y];
                x++;
                y++;
                count++;
            }
            int result = check(curDiagonal);
            if (result != 0){
                return result;
            }
            startX--;
            endY++;
        }

        for (int i = 0; i < size; i++) {
            int x = startX;
            int y = startY;
            int count = 0;
            while (x <= endX){
                curDiagonal[count] = board[x][y];
                x++;
                y++;
                count++;
            }
            int result = check(curDiagonal);
            if (result != 0){
                return result;
            }
            startY++;
            endX--;
        }
        return 0;
    }

    public int checkBackDiagonal(){
        int[] curBackDiagonal = new int[size];
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int i = 0; i < size-1; i++) {
            int x = startX;
            int y = startY;
            int count = 0;
            while (y <= endY){
                curBackDiagonal[count] = board[x][y];
                x--;
                y++;
                count++;
            }
            int result = check(curBackDiagonal);
            if (result != 0){
                return result;
            }
            startX++;
            endY++;
        }
        for (int i = 0; i < size; i++) {
            int x = startX;
            int y = startY;
            int count = 0;
            while (y <= endY){
                curBackDiagonal[count] = board[x][y];
                x--;
                y++;
                count++;
            }
            int result = check(curBackDiagonal);
            if (result != 0){
                return result;
            }
            startY++;
            endX++;
        }
        return 0;
    }

    public int checkGame(){

        int result = checkRow();
        if (result == 0){
            result = checkColumn();
        }
        if (result == 0){
            result = checkDiagonal();
        }
        if (result == 0){
            result = checkBackDiagonal();
        }

        return result;
    }

    public static String arrToString(int[]data){
        StringBuffer str5 = new StringBuffer();
        for (int s : data) {
            str5.append(s);
        }
        return str5.toString();
    }

    public static void main(String[] args) {
        int size = 19;
        int[][] arr = new int[size][size];


        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 2;
                count++;
            }
        }
        Logic a = new Logic(arr,size);

        System.out.println(a.checkColumn());


    }
}
