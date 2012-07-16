package sweforce.gui.display;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/9/12
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Display {

    /**
       * Set the only View of the receiver, replacing the previous
       * View if there was one.
       *
       * @param view the View, or <code>null</code> to remove the View
       *
       */
    public void setView(View view);

}
