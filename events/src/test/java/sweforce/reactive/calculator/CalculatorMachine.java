package sweforce.reactive.calculator;




import sweforce.reactive.observer.Observer;
import sweforce.reactive.state.State;
import sweforce.reactive.stream.IdentityEventStreamFilter;
import sweforce.reactive.stream.InputEventStream;

import java.math.BigDecimal;

public class CalculatorMachine extends BasicMachine {

    private final int calculatorDigitDisplayWidth;

    public CalculatorMachine(int calculatorDigitDisplayWidth) {
        this.calculatorDigitDisplayWidth = calculatorDigitDisplayWidth;
    }

    private Reference<BigDecimal> acc = new Reference<BigDecimal>(new BigDecimal(0));
    private final DigitMachine dm = new DigitMachine();

    public BigDecimal getValue() {
        return acc.getValue();
    }

    public void initialize() {
        acc.setValue(new BigDecimal(0));
    }

    {
        acc.addObserver(new Observer<BigDecimal>() {
            public void update(BigDecimal v) {
                outputEventStream.send(v);
            }
        });

        InputEventStream<BigDecimal> in = new InputEventStream<BigDecimal>();
        in.addObserver(new Observer<BigDecimal>() {
            public void update(BigDecimal v) {
                outputEventStream.send(v);
            }
        });
        dm.setOutputEventStream(in.bind(new IdentityEventStreamFilter<BigDecimal>()));

        setState(
                new State<InputEnum>() {
                    InputEnum binOp = null;
                    BigDecimal lastNum = new BigDecimal(0);

                    public boolean update(InputEnum e) {

                        if (!dm.update(e)) {
                            boolean gotANumber = dm.accept();
                            if (gotANumber)
                                lastNum = dm.getValue();

                            switch (e) {
                                case plus:
                                case minus:
                                case divide:
                                case mult:
                                    if (binOp != null && gotANumber)
                                        acc.setValue(
                                                binOp.eval(acc.getValue(), lastNum, calculatorDigitDisplayWidth)
                                        );
                                    else if (gotANumber) {
                                        acc.setValue(lastNum);
                                    }
                                    binOp = e;
                                    return true;
                                case equals:
                                    if (binOp != null)
                                        acc.setValue(
                                                binOp.eval(acc.getValue(), lastNum, calculatorDigitDisplayWidth));
                                    else acc.setValue(lastNum);

                                    return true;
                                case clear:
                                    binOp = null;
                                    lastNum = new BigDecimal(0);
                                    acc.setValue(lastNum);
                                    dm.update(InputEnum.done);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                        return true;
                    }
                }
        );
    }
}

