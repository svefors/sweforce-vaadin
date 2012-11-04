package sweforce.reactive.calculator;

import sweforce.reactive.observer.Observer;
import sweforce.reactive.state.State;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DigitMachine extends BasicMachine {

    public BigDecimal getValue() {
        update(InputEnum.done);
        BigDecimal value = acc.getValue();
        acc.value = new BigDecimal(0);
        return value;
    }

    private final Reference<BigDecimal> acc = new Reference<BigDecimal>(new BigDecimal(0));

    {
        acc.addObserver(new Observer<BigDecimal>() {
            public void update(BigDecimal v) {
                outputEventStream.send(v);
            }
        });


        setState(new State<InputEnum>() {
            BigDecimal sign = new BigDecimal(1);

            public boolean update(InputEnum e) {

                final State start = this;
                if (e.isDigit()) {
                    acc.setValue(new BigDecimal(e.value()));
                    setState(new State<InputEnum>() {
                        {
                            accept = true;
                        }

                        public boolean update(InputEnum e) {
                            if (e.isDigit()) {
                                acc.setValue(acc.getValue().multiply(new BigDecimal(10)).add(new BigDecimal(e.value())));
                                return true;
                            } else
                                switch (e) {
                                    case point:
                                        final Reference<Integer> scale = new Reference<Integer>(1);
                                        setState(new State<InputEnum>() {
                                            {
                                                accept = true;
                                            }

                                            public boolean update(InputEnum e) {
                                                if (e.isDigit()) {
                                                    acc.setValue(
                                                            acc.getValue()
                                                                    .add(
                                                                            new BigDecimal(new BigInteger(
                                                                                    String.valueOf(e.value())),
                                                                                    scale.getValue()
                                                                            )
                                                                    )
                                                    );
                                                    scale.setValue(scale.getValue() + 1);
                                                    return true;
                                                } else
                                                    switch (e) {
                                                        case done:
                                                            acc.setValue(acc.getValue().multiply(sign));
                                                            setState(start);
                                                            return true;
                                                        default:
                                                            return false;
                                                    }
                                            }
                                        });
                                        return true;
                                    case done:
                                        acc.setValue(acc.getValue().multiply(sign));
                                        setState(start);
                                        return true;
                                    default:
                                        return false;
                                }
                        }
                    });
                    return true;
                } else
                    return false;
            }
        });
    }
}
