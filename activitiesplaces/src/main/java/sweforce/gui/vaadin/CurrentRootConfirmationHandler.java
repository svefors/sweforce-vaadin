package sweforce.gui.vaadin;


import sweforce.gui.place.PlaceController;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/2/12
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentRootConfirmationHandler implements PlaceController.ConfirmationHandler {

    @Override
    public void askForConfirmation(String message, final Listener listener) {
        //TODO wait for next version of confirm dialog. OR use your own javascript
//        ConfirmDialog.show(UI.getCurrent(), message, new ConfirmDialog.Listener() {
//            @Override
//            public void onClose(ConfirmDialog dialog) {
//                if (dialog.isConfirmed()) {
//                    listener.onConfirm();
//                } else {
//                    listener.onCancel();
//                }
//            }
//        });
    }
}
