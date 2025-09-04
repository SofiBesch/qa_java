import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineTest {
    private final String animalKind;
    private final List<String> expectedFood;
    private final boolean expectedException;

    public FelineTest(String animalKind, List<String> expectedFood, boolean expectedException){
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
        this.expectedException = expectedException;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"Хищник", Arrays.asList("Животные", "Птицы", "Рыба"), false},
                {"Травоядное", Arrays.asList("Трава", "Различные растения"), false},
                {"Неизвестный", null, true}
        };
    }
    @Test
    public void testGetFoodWithParameter() throws Exception{
        Feline feline = new Feline();
        if (expectedException){
            try {
                feline.getFood(animalKind);
                assertTrue("Должно быть исключение", false);
            } catch (Exception e){
                assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
            }
        }   else{

            List<String> result = feline.getFood(animalKind);
            assertEquals(expectedFood, result);

        }
    }


    @Test
    public void testGetFamily(){
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensWithoutParameter() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }
    @Test
    public void testGetKittensWithParameter() {
        Feline feline = new Feline();
        assertEquals(5, feline.getKittens(5));
    }
    @Test
    public void testEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> expected = Arrays.asList("Животные", "Птицы", "Рыба");
        assertEquals(expected, feline.eatMeat());
    }
}
