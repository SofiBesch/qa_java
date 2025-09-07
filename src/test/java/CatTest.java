import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatTest {
    @Mock
    private Feline feline;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSound() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }
    @Test
    public void testGetFoodReturnsCorrectValue() throws Exception {
        //для проверки возвращаемого значения
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(feline);
        List<String> result = cat.getFood();

        assertEquals(expectedFood, result);
    }

    @Test
    public void testGetFoodCallsEatMeat() throws Exception {
        //для проверки вызова метода
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(feline);
        cat.getFood();

        verify(feline).eatMeat();
    }
    @Test
    public void testGetFoodExceptionMessage() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка питания"));

        Cat cat =new Cat(feline);
        try {
            cat.getFood();
            assertTrue("Должно было появится исключение", false);
        } catch (Exception e) {
            assertEquals("Ошибка питания", e.getMessage());
        }
    }


    @Test
    public void testGetFoodExceptionCallsEatMeat() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка питания"));

        Cat cat =new Cat(feline);
        try {
            cat.getFood();
        } catch (Exception e) {
            // проверяем только вызов метода
        }
        verify(feline).eatMeat();
    }

}
