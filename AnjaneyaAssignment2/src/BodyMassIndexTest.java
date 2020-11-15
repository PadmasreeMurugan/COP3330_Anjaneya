import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest
{
    /*BMI Score*/

    /*BMI Score = 703 * 120 / (80 * 80)
                = 84360 / 6400
                = 13.181 (< 18.5, Underweight) */
    @Test
    public void testBMICalculator()
    {
        BodyMassIndex BMI = new BodyMassIndex(80, 120);
        assertEquals(13.2, BMI.BMI_Calculator());
    }

    /*BMI Category*/

    /*BMI Score = 703 * 120 / (80 * 80)
                = 84360 / 6400
                = 13.181 (< 18.5, Underweight) */
    @Test
    public void testCategoryUnderweight()
    {
        BodyMassIndex underWeight = new BodyMassIndex(80, 120);
        assertEquals("underweight", underWeight.BMI_CategoryUnderweight());
    }

    /*BMI Score = 703 * 226 / (80 * 80)
                =  158878 / 6400
                = 24.824 (>= 18.5 && <= 24.9, Normal weight) */
    @Test
    public void testCategoryNormalweight()
    {
        BodyMassIndex normalWeight = new BodyMassIndex(80, 226);
        assertEquals("normal weight", normalWeight.BMI_CategoryNormalweight());
    }

    /*BMI Score = 703 * 240 / (80 * 80)
                = 168720 / 6400
                = 26.362 (>= 25 && <= 29.9, Over weight) */
    @Test
    public void testCategoryOverweight()
    {
        BodyMassIndex overWeight = new BodyMassIndex(80, 240);
        assertEquals("overweight", overWeight.BMI_CategoryOverweight());
    }

    /*/*BMI Score = 703 * 186 / (50 * 50)
                  = 130758 / 2500
                  = 52.303 (>= 30, obese) */
    @Test
    public void testCategoryObese()
    {
        BodyMassIndex obese = new BodyMassIndex(50, 186);
        assertEquals("obese", obese.BMI_CategoryObesity());
    }

    /*BMI Score = 703 * 240 / (80 * 80)
                = 168720 / 6400
                = 26.362 */
    @Test
    public void testRoundOneDecimal()
    {
        BodyMassIndex round = new BodyMassIndex(80, 240);
        assertEquals(26.4, round.RoundOneDecimal(round.BMI_Calculator()));
    }
}