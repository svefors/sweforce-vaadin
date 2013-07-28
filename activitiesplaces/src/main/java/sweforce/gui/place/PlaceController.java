package sweforce.gui.place;

/**

 */
public interface PlaceController {

    public Place getWhere();

    public void goTo(final Place newPlace);


    /**
     * Optional delegate in charge of Window-related events. Provides nice
     * isolation for unit testing, and allows customization of confirmation
     * handling.
     */
    interface ConfirmationHandler {

        void askForConfirmation(String message, Listener listener);

    //    /**
    //     * Adds a {@link ClosingHandler} to the Delegate.
    //     *
    //     * @param handler a {@link ClosingHandler} instance
    //     * @return a {@link HandlerRegistration} instance
    //     */
    //    HandlerRegistration addWindowClosingHandler(ClosingHandler handler);
    //TODO: check if Vaadin has support for closing window event.
    //    /**
    //     * Called to confirm a window closing event.
    //     *
    //     * @param message a warning message
    //     * @return true to allow the window closing
    //     */
    //    boolean confirm(String message);

        public static interface Listener {

            public void onConfirm();

            public void onCancel();
        }
    }
}
