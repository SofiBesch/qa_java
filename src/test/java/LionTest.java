import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.Before;
import org.junit.Test;

import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class LionTest {
    @Mock
    private Predator predator;
    private Feline felineMock;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        felineMock = mock(Feline.class);
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
    public void testGetKittensReturnsCorrectValue() throws Exception {
        //для проверки возвращаемого значения
        when(felineMock.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", felineMock);
        int result = lion.getKittens();

        assertEquals(1, result);
    }

    @Test
    public void testGetKittensCallsGetKittens() throws Exception {
        // для проверки вызова метода
        when(felineMock.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", felineMock);
        lion.getKittens();

        verify(felineMock).getKittens();
    }
    @Test
    public void testGetFoodReturnsCorrectValue() throws Exception {
        //для проверки возвращаемого значения
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predator);
        List<String> result = lion.getFood();

        assertEquals(expectedFood, result);
    }

    @Test
    public void testGetFoodCallsEatMeat() throws Exception {
        // для проверки вызова метода
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predator);
        lion.getFood();

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
    @Test
    public void testGetKittensWhenNotFeline() throws Exception {
        Lion lion = new Lion("Самец", predator);
        int result = lion.getKittens();
        assertEquals(0, result);
    }
}
