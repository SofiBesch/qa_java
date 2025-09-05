import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FelineParamTest {
    private final String animalKind;
    private final List<String> expectedFood;
    private final boolean expectedException;

    public FelineParamTest(String animalKind, List<String> expectedFood, boolean expectedException) {
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
}
