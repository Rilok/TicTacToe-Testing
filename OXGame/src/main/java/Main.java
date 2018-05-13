import java.util.Scanner;

public class Main {
    public Board board;
    public State currState;
    public FieldState currPlayer;

    public static Scanner input = new Scanner(System.in);

    public Main(int size) {
        board = new Board(size);
        createGame();
    }

    public void createGame() {
        board.initialize();
        currPlayer = FieldState.CIRCLE;
        currState = State.PLAYING;
    }

    public FieldState makePlayerMove(int x, int y) {
        board.fields[x][y].content = currPlayer;
        board.currRow = x;
        board.currCol = y;

        return board.fields[board.currRow][board.currCol].content;
    }

    public State update(FieldState currMove) {
        if (board.checkWinner(currMove)){
            if (currMove == FieldState.CIRCLE)
                currState = State.CIRCLE_WON;
            else
                currState = State.CROSS_WON;
        }
        else if (board.checkDraw())
            currState = State.DRAW;
        return currState;
    }

    public String ShowCondition(){
        if (currState == State.CIRCLE_WON)
            return "'O' wygrał! Nara!";
        else if (currState == State.CROSS_WON)
            return "'X' zaorał! Bywaj!";
        else if (currState == State.DRAW)
            return "Remis! Jesteście siebie warci. Strzała!";
        return "";
    }

    public FieldState queue(){
        return currPlayer = (currPlayer == FieldState.CROSS) ? FieldState.CIRCLE : FieldState.CROSS;
    }

    public void GameMech(int x, int y){
        makePlayerMove(x, y);
        update(currPlayer);
        queue();
    }

    public static void main(String[] args) throws InterruptedException {
        Main game = new Main(3);

        do {
            if (game.currPlayer == FieldState.CROSS)
                System.out.print("Graczu 'X', ruszaj! (row[1-" + game.board.size + "]" +
                        " column[1-" + game.board.size + "]): ");
            else
                System.out.print("Graczu 'O', ruszaj! (row[1-" + game.board.size + "]" +
                        " column[1-" + game.board.size + ")]): ");

            int x = input.nextInt() - 1;
            int y = input.nextInt() - 1;

            if (x >= 0 && x < game.board.size && y >= 0 && y < game.board.size
                    && game.board.fields[x][y].content == FieldState.EMPTY){
                game.GameMech(x, y);
                System.out.println(game.board.paintBoard());
                System.out.print(game.ShowCondition());
            }
            else {
                System.out.println("Co to ma być za ruch?! (" + (x + 1) + "," + (y + 1) + ") Jeszcze raz!");
                Thread.sleep(1000);
            }
        } while (game.currState == State.PLAYING);

    }
}
