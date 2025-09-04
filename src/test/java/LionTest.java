import com.example.Lion;
import com.example.Predator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LionTest {
    @Mock
    private Predator predator;

    private final String sex;
    private final boolean expectedHasMane;
    private final boolean expectedException;

    public LionTest(String sex, boolean expectedHasMane, boolean expectedException) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.expectedException = expectedException;

    }
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"Самец", true, false},
                {"Самка", false, false},
                {"Неизвестный", false, true}
        };
    }


    @Test
    public void testLionConstructor() throws Exception {

        if (expectedException) {
            try {
                new Lion(sex, predator);
                assertTrue("Должно быть исключение", false);
            } catch (Exception e) {
                assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
            }
        }else{
            Lion lion = new Lion(sex, predator);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    @Test
    public void testGetKittens() throws Exception {

        when(predator.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", predator);
        int result = lion.getKittens();

        assertEquals(3, result);
        verify(predator).getKittens();
    }
    @Test
    public void testGetFood() throws Exception{

        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predator);
        List<String> result = lion.getFood();

        assertEquals(expectedFood, result);
        verify(predator).eatMeat();
    }
    @Test
    public void testGetFoodException() throws Exception {

        when(predator.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", predator);

        try {
            lion.getFood();
            assertTrue("Должно было появиться исключение", false);
        } catch (Exception e){
            assertEquals("Ошибка получения еды", e.getMessage());
        }
        verify(predator).eatMeat();

    }
}
