package sweforce.gui.display;

/**
 * A display shall contain the view that the user is looking and interacting with.
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
