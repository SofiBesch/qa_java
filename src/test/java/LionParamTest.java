
import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class LionParamTest {
    @Mock
    private Feline feline;
    private final String sex;
    private final boolean expectedHasMane;
    private final boolean expectedException;

    public LionParamTest(String sex, boolean expectedHasMane, boolean expectedException) {
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
                new Lion(sex, feline);
                assertTrue("Должно быть исключение", false);
            } catch (Exception e) {
                assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
            }
        }else{
            Lion lion = new Lion(sex, feline);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }
}
