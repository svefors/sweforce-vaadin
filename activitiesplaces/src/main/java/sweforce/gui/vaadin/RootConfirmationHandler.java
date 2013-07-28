package sweforce.gui.vaadin;


import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;
import sweforce.gui.place.PlaceController;



/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/12
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RootConfirmationHandler implements PlaceController.ConfirmationHandler {

    private final UI root;


    public RootConfirmationHandler(UI root) {
        this.root = root;
    }

    @Override
        public void askForConfirmation(String message, final Listener listener) {
            ConfirmDialog.show(root, message, new ConfirmDialog.Listener() {
                @Override
                public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        listener.onConfirm();
                    } else {
                        listener.onCancel();
                    }
                }
            });
        }
}
