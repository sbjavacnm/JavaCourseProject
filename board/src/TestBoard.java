public class TestBoard {
    public static void main(String[]args){
        Board board = new Board();
        board.show();
        for(int i = 0 ; true ; i++){
            board.move(i);
        }
    }
}
