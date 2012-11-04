package sweforce.reactive.calculator;

//import swing.machine.Machine;
//import swing.machine.pattern.observer.interfaces.Observer;
//import swing.machine.event.OutputEventStream;
//import swing.machine.event.InputEventStream;

import sweforce.reactive.observer.Observer;
import sweforce.reactive.state.Machine;
import sweforce.reactive.stream.InputEventStream;
import sweforce.reactive.stream.OutputEventStream;

import java.math.BigDecimal;

public class BasicMachine extends Machine<InputEnum, BigDecimal> {
    {
        super.inputEventStream = new InputEventStream<InputEnum>();
        super.outputEventStream = new OutputEventStream<BigDecimal>();

        super.inputEventStream.addObserver(new Observer<InputEnum>(){

            public void update(InputEnum v) {
                BasicMachine.this.update(v);
            }
        });
    }

}
