import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainTest {

    Main testProgram;
    FieldState testFieldState;
    State testState;

    @BeforeEach
    public void init() {
        testProgram = new Main(4);

        for (int x = 0; x < 3; x++){
            testProgram.board.fields[x][0].content =
                    testProgram.board.fields[x][1].content
                            = testFieldState.CROSS;
            testProgram.board.fields[x][2].content =
                    testProgram.board.fields[x][3].content
                            = testFieldState.CIRCLE;
        }
    }

    @Test
    public void TestCreationOfGame_CreateFields() {
        assertNotNull(testProgram.board.fields);
    }

    @Test
    public void CheckConditionOnUpdate_CircleWins(){

        testProgram.board.fields[3][2].content = testFieldState.CIRCLE;
        testProgram.board.currRow = 3;
        testProgram.board.currCol = 2;
        testProgram.update(testFieldState.CIRCLE);
        assertTrue(testState.CIRCLE_WON.equals(testProgram.currState));
    }

    @Test
    public void CheckConditionOnUpdate_CrossWins(){
        testProgram.board.fields[3][0].content = testFieldState.CROSS;
        testProgram.board.currRow = 3;
        testProgram.board.currCol = 0;
        testProgram.update(testFieldState.CROSS);
        assertTrue(testState.CROSS_WON.equals(testProgram.currState));
    }

    @Test
    public void CheckConditionOnUpdate_Draw(){
        testProgram.board.fields[3][0].content =
                testProgram.board.fields[3][1].content =
                        testFieldState.CIRCLE;
        testProgram.board.fields[3][2].content =
                testProgram.board.fields[3][3].content =
                        testFieldState.CROSS;
        testProgram.update(testFieldState.CROSS);
        assertTrue(testState.DRAW.equals(testProgram.currState));
    }

    @Test
    public void TestShowCondition_CircleWins() {
        testProgram.board.fields[3][2].content = testFieldState.CIRCLE;
        testProgram.board.currRow = 3;
        testProgram.board.currCol = 2;
        testProgram.update(testFieldState.CIRCLE);
        assertEquals(testProgram.ShowCondition(),"'O' wygrał! Nara!");
    }

    @Test
    public void TestShowCondition_CrossWins() {
        testProgram.board.fields[3][0].content = testFieldState.CROSS;
        testProgram.board.currRow = 3;
        testProgram.board.currCol = 0;
        testProgram.update(testFieldState.CROSS);
        assertEquals(testProgram.ShowCondition(),"'X' zaorał! Bywaj!");
    }

    @Test
    public void TestShowCondition_Draw() {
        testProgram.board.fields[3][0].content =
                testProgram.board.fields[3][1].content =
                        testFieldState.CIRCLE;
        testProgram.board.fields[3][2].content =
                testProgram.board.fields[3][3].content =
                        testFieldState.CROSS;
        testProgram.update(testFieldState.CROSS);
        assertEquals(testProgram.ShowCondition(), "Remis! Jesteście siebie warci. Strzała!");
    }

    @Test
    public void TestShowCondition_Continue(){
        assertEquals(testProgram.ShowCondition(), "");
    }


    @Test
    public void TestPlayerMove() {
        testProgram.currPlayer = testFieldState.CIRCLE;
        testProgram.makePlayerMove(1, 2);
        assertEquals(testProgram.board.fields[1][2].content, testProgram.currPlayer);
    }

    @Test
    public void TestQueueFunction() {
        testProgram.currPlayer = testFieldState.CIRCLE;
        testProgram.queue();
        assertTrue(testProgram.currPlayer.equals((testFieldState.CROSS)));
    }

    @Test
    public void TestGameMech_ChangingPlayers() {
        testProgram.currPlayer = testFieldState.CIRCLE;
        testProgram.GameMech(3, 0);
        assertFalse(testProgram.currPlayer.equals(testFieldState.CIRCLE));
    }

    @Test
    public void mainTest() throws IOException {
        Main game = new Main(4);
    }

}