package sweforce.reactive.state;

import sweforce.reactive.state.State;
import sweforce.reactive.stream.InputEventStream;
import sweforce.reactive.stream.OutputEventStream;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Machine<I, O> {

    protected InputEventStream<I> inputEventStream;

    public void setOutputEventStream(OutputEventStream<O> outputEventStream) {
        this.outputEventStream = outputEventStream;
    }

    protected OutputEventStream<O> outputEventStream;

    private State<I> state;

    public void setState(State<I> h) {
        this.state = h;
    }

    public boolean update(I e) {
        return state.update(e);
    }

    public boolean accept(){
        return state.accept();
    }
    public InputEventStream<I> getInputEventStream() {
        return inputEventStream;
    }

    public OutputEventStream<O> getOutputEventStream() {
        return outputEventStream;
    }
}