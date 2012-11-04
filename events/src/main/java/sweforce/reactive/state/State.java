package sweforce.reactive.state;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class State<I> {

    public boolean update(I e) {
        return false;
    }

    protected boolean accept = false;

    public boolean accept(){
        return accept;
    }

}
