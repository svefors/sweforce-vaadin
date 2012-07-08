package sweforce.gui.ap.vaadin;

import com.vaadin.ui.Root;
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

    private final Root root;

    @Inject
    public RootConfirmationHandler(Root root) {
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
