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
    public void testGetFood() throws Exception{
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(feline);
        List<String> result = cat.getFood();

        assertEquals(expectedFood, result);
        verify(feline).eatMeat();
    }
    @Test
    public void testGetFoodExсeption() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка питания"));

        Cat cat =new Cat(feline);
        try {
            cat.getFood();
            assertTrue("Должно было появится исключение", false);
        } catch (Exception e) {
            assertEquals("Ошибка питания", e.getMessage());
        }
        verify(feline).eatMeat();
    }

}
