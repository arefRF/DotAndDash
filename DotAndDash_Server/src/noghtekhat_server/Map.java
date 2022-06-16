package noghtekhat_server;

import java.awt.Color;
import java.util.LinkedList;

public class Map {
    int playerCount;
    int turn;
    int size;
    int[] color;
    private Line[][] lines_v;
    private Line[][] lines_h;
    LinkedList<Line> Lines;
    private int limit;
    private int counter;
    Box[][] boxes;
    public Map(int playerCount, int size){
        
        boxes = new Box[size-1][size-1];
        
        
        
        color = new int[playerCount];
        this.playerCount = playerCount;
        turn = -1;
        this.size = size;
        lines_v = new Line[size-1][size];
        lines_h = new Line[size][size-1];
        Lines = new LinkedList<>();
        for(int i=0; i<size-1; i++)
            for(int j=0; j<size; j++){
                lines_v[i][j] = new Line();
                Lines.add(lines_v[i][j]);
            }
                
        for(int j=0; j<size-1; j++)
            for(int i=0; i<size; i++){
                lines_h[i][j] = new Line();
                Lines.add(lines_h[i][j]);
            }
        
        
        for(int i=0; i<size-1; i++){
            for(int j=0; j<size-1; j++){
                boxes[i][j] = new Box(lines_v[i][j], lines_v[i][j+1], lines_h[i][j], lines_h[i+1][j]);
            }
        }
        
        limit = (size)*(size-1)*2;
        counter = 0;
    }
    
    public void add(int i, int player){
        Lines.get(i).owner = player;
        counter++;
    }
    
    public boolean isFinished(){
        if(counter == limit)
            return true;
        return false;
    }
    
    public boolean CheckBoxCreated(){
        boolean flag = false;
        for(int i=0; i<size-1; i++){
            for(int j=0; j<size-1; j++){
                if(!boxes[i][j].isFilled && boxes[i][j].v1.owner != -1 && boxes[i][j].v2.owner != -1 && boxes[i][j].h1.owner != -1 && boxes[i][j].h2.owner != -1){
                    boxes[i][j].isFilled = true;
                    flag = true;
                }
            }
        }
        return flag;
    }
}

class Line{
    int owner;
    public Line(){
        owner = -1;
    }
}

class Box{
    Line v1,v2,h1,h2;
    boolean isFilled;
    public Box(Line v1, Line v2, Line h1, Line h2){
        this.v1 = v1;
        this.v2 = v2;
        this.h1 = h1;
        this.h2 = h2;
        isFilled = false;
    }
}
