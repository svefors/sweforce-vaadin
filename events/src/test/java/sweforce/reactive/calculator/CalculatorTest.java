package sweforce.reactive.calculator;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/3/12
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */

import junit.framework.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public  void runCalculator() {
        CalculatorMachine c = new CalculatorMachine(32);
        String input = "12.3+3.5+1.1*2=";

        for (int i = 0; i < input.length(); ++i) {
            c.update(InputEnum.map(input.charAt(i)));
        }

        Assert.assertEquals("33.8", c.getValue().toString());
    }

}
