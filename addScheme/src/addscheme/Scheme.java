package addscheme;

public class Scheme {
    private int id, difficulty;
    private Box boxes[][];
    private boolean isEmpty;

    public Scheme(int id, int difficulty, Box boxes[][]){
        this.id=id;
        this.difficulty=difficulty;
        this.boxes =boxes;
        this.isEmpty=true;
    }

    public Box[][] getboxes() {
        return boxes;
    }

    public boolean checkFirst(Box box, Dice dice){
        int row=box.getX();
        int column=box.getY();

        if(row==0 || row==3 || column==0 || column==4){
            return (checkBox(box,dice));
        }
        else
            return false;
    }

    public boolean checkColor (Color color1, Color color2) {
        return (color1==color2);
    }

    public boolean checkShade (int shade1, int shade2) {
        return (shade1==shade2);
    }

    public boolean checkBox(Box box, Dice dice){
        return ((checkColor(box.getColor(),Color.WHITE) ||
                checkColor(box.getColor(),dice.getDiceColor())) && (checkShade(box.getShade(),0) || checkShade(box.getShade(),dice.getNumFacciaUp())));
    }

    public boolean checkDiceAdjacent(Box box, Dice dice){
        int row=box.getX();
        int column=box.getY();
        Boolean right, left, up, down, upRight, upLeft, downRight, downLeft;

        if (row==3)
            down=false;
        else
            down=boxes[row+1][column].isFull();

        if (row==0)
            up=false;
        else
            up=boxes[row-1][column].isFull();

        if (column==0)
            left=false;
        else
            left=boxes[row][column-1].isFull();

        if (column==4)
            right=false;
        else
            right=boxes[row][column+1].isFull();

        if (column==4 || row==0)
            upRight=false;
        else
            upRight=boxes[row-1][column+1].isFull();

        if (column==4 || row==3)
            downRight=false;
        else
            downRight=boxes[row+1][column+1].isFull();

        if (column==0 || row==0)
            upLeft=false;
        else
            upLeft=boxes[row-1][column-1].isFull();

        if (column==0 || row==3)
            downLeft=false;
        else
            downLeft=boxes[row+1][column-1].isFull();

        if (right || left || up || down || upRight || upLeft || downRight || downLeft) {
            if (right)
                right = checkOrthogonal(row, column+1, dice);
            if (right&&left)     // metto tutte queste condizioni nell'if così entra solo se già ha passato tutti i controlli precedenti
                left = checkOrthogonal(row, column-1, dice);
            if (right&&left&&up)
                up = checkOrthogonal(row-1, column, dice);
            if (right&&left&&up&&down) {
                down = checkOrthogonal(row+1, column, dice);
                return down;
            }

        }

        return false;

    }

    public Boolean checkOrthogonal(int i, int y, Dice dice) {
        if (checkColor(boxes[i][y].getColor(),dice.getDiceColor()) || checkColor(boxes[i][y].getDice().getDiceColor(),dice.getDiceColor()))
            return false;
        else if (checkShade(boxes[i][y].getShade(),dice.getNumFacciaUp()) || checkShade(boxes[i][y].getDice().getNumFacciaUp(),dice.getNumFacciaUp()))
            return false;
        return true;
    }

    public void setboxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }

    public void setNotEmpty(){
        this.isEmpty=false;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public int getId() {
        return id;
    }
    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }
    public void setId(short id) {
        this.id = id;
    }


}