public class Board {

    public static int size;

    Field[][] fields;
    int currRow, currCol;

    public Board(int size) {
        this.size = size;
        fields = new Field[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) fields[x][y] = new Field(x, y);
        }
    }

    public void initialize() {
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++) fields[x][y].emptyFill();
        }
    }

    public boolean checkDraw() {
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if(fields[x][y].content == FieldState.EMPTY) return false;
            }
        }
        return true;
    }

    public boolean checkWinner(FieldState gfs) {

        int number = 0;

        for (int x = 0; x < size; x++){
            if (fields[x][currCol].content == gfs)
                number++;
            if (number == size)
                return true;
        }

        number = 0;

        for (int y = 0; y < size; y++){
            if (fields[currRow][y].content == gfs)
                number++;
            if (number == size)
                return true;
        }

        number = 0;

        for (int xy = 0; xy < size; xy++){
            if (fields[xy][xy].content == gfs)
                number++;
            if (number == size)
                return true;
        }

        number = 0;

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if (x + y == size - 1) {
                    if (fields[x][y].content == gfs)
                        number++;
                    if (number == size)
                        return true;
                }
            }
        }

        return false;
    }

    public String paintBoard() {
        String map = "";
        String crossLine = "";

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                map += (fields[x][y].drawBoard());
                if (x < size) map += ("|");
                crossLine += "----";
            }
            map += ("\n" + crossLine + "\n");
            //System.out.println();
            //System.out.println(crossLine);
            crossLine = "";
        }
        return map;
    }
}
