import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {

    Board testBoard;
    FieldState testState;

    @BeforeEach
    public void init() {
        testBoard = new Board(3);
        testBoard.initialize();
        testBoard.fields[0][2].content = testState.CIRCLE;
        testBoard.fields[1][2].content = testState.CIRCLE;
        testBoard.fields[0][1].content = testState.CROSS;
        testBoard.fields[1][0].content = testState.CROSS;
    }

    @Test
    public void CheckNumberOfFields() {
        testBoard = new Board(5);
        assertThat(testBoard.fields.length * testBoard.fields[0].length).isEqualTo(25);
    }

    @Test
    public void CheckIfBoardContainsFields() {
        testBoard.fields[1][0].content = testState.CROSS;
        assertThat(testBoard.fields[1][0].content).isEqualTo(FieldState.CROSS);
    }

    @Test
    public void CheckDrawResult() {
        testBoard.fields[1][1].content = FieldState.CROSS;
        testBoard.fields[0][0].content = FieldState.CIRCLE;
        testBoard.fields[2][0].content = FieldState.CROSS;
        testBoard.fields[2][1].content = FieldState.CIRCLE;
        testBoard.fields[2][2].content = FieldState.CROSS;

        assertTrue(testBoard.checkDraw());
    }

    @Test
    public void CheckWinningConditionBeforeDrawResult() {
        testBoard.fields[1][1].content = FieldState.CROSS;
        testBoard.fields[0][0].content = FieldState.CIRCLE;
        testBoard.fields[2][0].content = FieldState.CROSS;
        testBoard.fields[2][1].content = FieldState.CIRCLE;
        testBoard.fields[2][2].content = FieldState.CROSS;

        assertFalse(testBoard.checkWinner(FieldState.CIRCLE));
    }

    @Test
    public void CheckWinningColumnResult() {
        testBoard.fields[2][2].content = FieldState.CIRCLE;
        testBoard.currCol = 2;
        assertTrue(testBoard.checkWinner(FieldState.CIRCLE));
    }

    @Test
    public void CheckWinningRowResult() {
        testBoard.fields[2][0].content = FieldState.CROSS;
        testBoard.fields[2][1].content = FieldState.CROSS;
        testBoard.fields[2][2].content = FieldState.CROSS;
        testBoard.currRow = 2;
        assertTrue(testBoard.checkWinner(FieldState.CROSS));
    }

    @Test
    public void CheckWinningCrosswiseResult() {
        testBoard.fields[0][0].content = FieldState.CIRCLE;
        testBoard.fields[1][1].content = FieldState.CIRCLE;
        testBoard.fields[2][2].content = FieldState.CIRCLE;
        testBoard.currRow = 2;
        assertTrue(testBoard.checkWinner(FieldState.CIRCLE));
    }

    @Test
    public void CheckWinningReverseCrosswiseResult() {
        testBoard.fields[2][0].content = FieldState.CIRCLE;
        testBoard.fields[1][1].content = FieldState.CIRCLE;
        testBoard.currRow = 1;
        assertTrue(testBoard.checkWinner(FieldState.CIRCLE));
    }

    @Test
    public void CheckIfPaintingBoardWorksProperly() {
        assertEquals(testBoard.paintBoard(), "   | X | O |\n" +
                                             "------------\n" +
                                             " X |   | O |\n" +
                                             "------------\n" +
                                             "   |   |   |\n" +
                                             "------------\n");
    }


    @AfterEach
    public void tearDown() {
        testBoard = null;
    }
}