package sweforce.gui.activity;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/24/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityFactory  {

    /**
     *
     * @param activityClass
     * @param <T>
     * @return
     */
    public <T extends Activity> T getActivity(Class<T> activityClass);

}
