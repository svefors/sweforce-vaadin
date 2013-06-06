package sweforce.gui.place;

import sweforce.gui.place.PlaceController;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockConfirmationHandler implements PlaceController.ConfirmationHandler {

    String message = null;
    boolean confirm = false;

    @Override
    public void askForConfirmation(String message, Listener listener) {
        this.message = message;
        if(confirm){
            listener.onConfirm();
        }else{
            listener.onCancel();
        }
    }
}
