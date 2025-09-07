import com.example.Feline;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class FelineTest {

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
