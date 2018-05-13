public class Field {
    FieldState content;
    int x, y;

    public Field(int x, int y){
        this.x = x;
        this.y = y;
        emptyFill();
    }

    public void emptyFill() {
        content = FieldState.EMPTY;
    }

    public String drawBoard() throws IllegalArgumentException {

        if (content == FieldState.EMPTY)
            return "   ";
        else if (content == FieldState.CROSS)
            return " X ";
        else if (content == FieldState.CIRCLE)
            return " O ";
        else
            throw new IllegalArgumentException("Grzebałeś w programie. Nieładnie!");
    }
}
