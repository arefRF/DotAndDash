package noghtekhat_client;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JToggleButton;

public class Map {
    Button[][] lines_v;
    Button[][] lines_h;
    LinkedList<Button> Lines;
    Box[][] boxes;
    int size;
    Color playerColor;
    int score;
    
    public Map(int size, Color color){
        lines_v = new Button[size-1][size];
        lines_h = new Button[size][size-1];
        Lines = new LinkedList<>();
        boxes = new Box[size-1][size-1];
        score = 0;
        
        this.playerColor = color;
        this.size = size;
    }
    
    
    public LinkedList<Point> CheckBoxCreated(){
        LinkedList<Point> list = new LinkedList<>();
        for(int i=0; i<size-1; i++){
            for(int j=0; j<size-1; j++){
                if(!boxes[i][j].isFilled && boxes[i][j].v1.isSelected && boxes[i][j].v2.isSelected && boxes[i][j].h1.isSelected && boxes[i][j].h2.isSelected){
                    boxes[i][j].isFilled = true;
                    list.add(new Point(i,j));
                }
            }
        }
        
        return list;
    }
   
    public boolean isFinished(){
        for(int i=0; i<size-1; i++){
            for(int j=0; j<size-1; j++){
                if(!boxes[i][j].isFilled)
                    return false;
            }
        }
        return true;
    }
}

class Box{
    Button v1,v2,h1,h2;
    boolean isFilled;
    public Box(Button v1, Button v2, Button h1, Button h2){
        this.v1 = v1;
        this.v2 = v2;
        this.h1 = h1;
        this.h2 = h2;
        isFilled = false;
    }
}
