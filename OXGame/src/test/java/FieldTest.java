import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class FieldTest {

    Field testField;

    Field crossField;
    Field circleField;


    @BeforeEach
    public void initialize(){
        testField = new Field(0, 0);

        crossField = new Field (0 ,1);
        crossField.content = FieldState.CROSS;
        circleField = new Field (0, 2);
        circleField.content = FieldState.CIRCLE;
    }

    @Test
    public void CheckIfFieldIsEmpty() {
        assertThat(testField.content, is(FieldState.EMPTY));
    }

    @Test
    public void CheckEmptyFieldDrawing(){
        assertThat(testField.drawBoard(), is("   "));
    }

    @Test
    public void CheckCircleFieldDrawing(){
        assertThat(crossField.drawBoard(), is(" X "));
    }

    @Test
    public void CheckCrossFieldDrawing(){
        assertThat(circleField.drawBoard(), is(" O "));
    }

    @Test
    public void CheckDrawingWithJoke(){
        testField.content = FieldState.PSIKUS;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            testField.drawBoard();});

        assertEquals("Grzebałeś w programie. Nieładnie!", exception.getMessage() );
    }


}
