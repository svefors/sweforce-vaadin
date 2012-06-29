package sweforce.gui.view;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/22/12
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NullWidget implements IsWidget<Object> {
    private static NullWidget ourInstance = new NullWidget();

    public static NullWidget getInstance() {
        return ourInstance;
    }

    private NullWidget() {
    }

    public Object asWidget(){
        return null;
    }

}
