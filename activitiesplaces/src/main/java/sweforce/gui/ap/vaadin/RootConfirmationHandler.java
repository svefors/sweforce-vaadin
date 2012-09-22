package sweforce.gui.ap.vaadin;


import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;
import sweforce.gui.ap.place.ConfirmationHandler;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/12
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RootConfirmationHandler implements ConfirmationHandler{

    private final UI root;

    @Inject
    public RootConfirmationHandler(UI root) {
        this.root = root;
    }

    @Override
        public void askForConfirmation(String message, final Listener listener) {
        //TODO figure out which confirmation handler is used.

//            ConfirmDialog.show(root, message, new ConfirmDialog.Listener() {
//                @Override
//                public void onClose(ConfirmDialog dialog) {
//                    if (dialog.isConfirmed()) {
//                        listener.onConfirm();
//                    } else {
//                        listener.onCancel();
//                    }
//                }
//            });
        }
}
