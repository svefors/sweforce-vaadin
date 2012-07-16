package sweforce.gui.display;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/9/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NullView implements View{

    private static NullView ourInstance = new NullView();

    public static NullView getInstance() {
        return ourInstance;
    }

    private NullView() {
    }


}
