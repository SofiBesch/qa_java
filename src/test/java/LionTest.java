import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class LionTest {
    @Mock
    private Feline feline;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void testGetKittensReturnsCorrectValue() throws Exception {
        //для проверки возвращаемого значения
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", feline);
        int result = lion.getKittens();

        assertEquals(1, result);
    }

    @Test
    public void testGetKittensCallsGetKittens() throws Exception {
        // для проверки вызова метода
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", feline);
        lion.getKittens();

        verify(feline).getKittens();
    }
    @Test
    public void testGetFoodReturnsCorrectValue() throws Exception {
        //для проверки возвращаемого значения
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", feline);
        List<String> result = lion.getFood();

        assertEquals(expectedFood, result);
    }

    @Test
    public void testGetFoodCallsEatMeat() throws Exception {
        // для проверки вызова метода
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", feline);
        lion.getFood();

        verify(feline).eatMeat();
    }
    @Test
    public void testGetFoodExceptionMessage() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", feline);

        try {
            lion.getFood();
            assertTrue("Должно было появиться исключение", false);
        } catch (Exception e){
            assertEquals("Ошибка получения еды", e.getMessage());
        }
    }


    @Test
    public void testGetFoodExceptionCallsEatMeat() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", feline);

        try {
            lion.getFood();
        } catch (Exception e){
            // проверяем только вызов метода
        }
        verify(feline).eatMeat();
    }
    @Test
    public void testGetKittensWhenNotFeline() throws Exception {
        Lion lion = new Lion("Самец", feline);
        int result = lion.getKittens();
        assertEquals(0, result);
    }
}
