import java.awt.*;
public class Board {
    private int size ;
    //0 stands for empty, 1 stands for white, 2 stands for black.

    private int[][] boardSet ; //used to keep the set of tools;

    //draw the board
    public void drawBoard(){
        StdDraw.picture(0,0,"pic.board2.jpg",50*size,50*size);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.filledCircle(0,0,5);
        StdDraw.line(-25*size,25*size,25*size,25*size);
        StdDraw.line(-25*size,-25*size,25*size,-25*size);
        StdDraw.line(-25*size,25*size,-25*size,-25*size);
        StdDraw.line(25*size,25*size,25*size,-25*size);
        StdDraw.setPenRadius(0.002);
        for(int i = 0 ; i < size ; i++) StdDraw.line(-25*size+(double)50*size/(size-1)*i,25*size,-25*size+(double)50*size/(size-1)*i,-25*size);
        for(int i = 0 ; i < size ; i++) StdDraw.line(-25*size,25*size-(double)50*size/(size-1)*i,25*size,25*size-(double)50*size/(size-1)*i);
    }

    //draw the board with the setBoard
    public void drawBoard(int[][] colors){
        drawBoard();
        for(int j = 0 ; j <size ; j++){
            for(int i = 0 ; i<15 ; i++){
                if(colors[j][i]==2) setTool(-25*size+(double)50*size/(size-1)*i,25*size-(double)50*size/(size-1)*j,StdDraw.BLACK);
                if(colors[j][i]==1) setTool(-25*size+(double)50*size/(size-1)*i,25*size-(double)50*size/(size-1)*j,StdDraw.WHITE);
            }
        }
    }

    //default constructor
    public Board() {
        size = 15;
        boardSet = new int[size][size];
        StdDraw.setCanvasSize(1500, 900);
        StdDraw.setXscale(-50 * size, 50 * size);
        StdDraw.setYscale(-30 * size, 30 * size);
        drawBoard();
    }

    //overload constructor, size only 15 17 19 are permitted
    public Board(int s){
        this.size = s;
        boardSet = new int[size][size];
        StdDraw.setCanvasSize(1500,900);
        StdDraw.setXscale(-50*size, 50*size);
        StdDraw.setYscale(-30*size,30*size);
        drawBoard();

    }

    //show the board
    public void show(){
        StdDraw.show();
    }

    //set the tools, used in this class
    public void setTool(double x , double y,Color color){
        if(x>=-25*size && x<=25*size && y>=-25*size && y<=25*size){
           int i;
           int j;
           StdDraw.setPenColor(color);
           i = (int)Math.round((x+25*size)/(double)((50*size)/(size-1)));
           j = (int)Math.round((25*size-y)/(double)((50*size)/(size-1)));
           StdDraw.filledCircle(-25*size+(double)50*size/(size-1)*i,25*size-(double)50*size/(size-1)*j,20);
        }


    }

    //set the animation of moving tools, used outside the class, even for black, odd for white.
    public void move(int color){
        Color c1 ;
        int value = 0;
        if(color%2 == 0) {
            c1 = StdDraw.BLACK;
            value = 2;
        } else {
            c1 = StdDraw.WHITE;
            value = 1;
        }
        StdDraw.enableDoubleBuffering();
        int boardX;//用来给boardSet做参数
        int boardY;
        double x;
        double y;
        for(double t = 0.0 ; true ; t+=0.02){
             x = StdDraw.mouseX();
             y = StdDraw.mouseY();
            StdDraw.clear();
            drawBoard(boardSet);
            setTool(x,y,StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.show();
            StdDraw.pause(10);
            if(StdDraw.isMousePressed()){
                setTool(x,y,c1);
                boardX = (int)Math.round((x+25*size)/(double)((50*size)/(size-1)));
                boardY = (int)Math.round((25*size-y)/(double)((50*size)/(size-1)));
                boardSet[boardY][boardX] = value;
                Logic logic = new Logic(boardSet,size);
                if (logic.checkGame() == 0){
                    drawBoard(boardSet);
                }else{
                    System.out.println("game over");
                    System.exit(0);
                }
                break;
            }

        }
    }

    public int[][] getBoardSet(){
        return boardSet;
    }
}
