package sweforce.vaadin.table.editor.navigator;

import com.vaadin.event.Action;
import sweforce.command.AbstractCommand;
import sweforce.command.Command;
import sweforce.vaadin.keyboard.CommandActionHandler;
import sweforce.vaadin.keyboard.KeyBinding;
import sweforce.vaadin.keyboard.KeyGesture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/4/12
 * Time: 11:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridNavigationActionHandler extends CommandActionHandler {

    private GridNavigationActionHandler(Map<Action, Command> actionCommandMap) {
        super(actionCommandMap);
    }

    public static GridNavigationActionHandler create(final GridNavigationViewModel viewModel, Gestures gestures) {
        return new GridNavigationActionHandler(
                CommandActionHandler.actionCommandMap(
                new KeyBinding.DefaultImpl(gestures.upGesture(),viewModel.upCommand),
                new KeyBinding.DefaultImpl(gestures.downGesture(), viewModel.downCommand),
                new KeyBinding.DefaultImpl(gestures.leftGesture(), viewModel.leftCommand),
                new KeyBinding.DefaultImpl(gestures.rightGesture(), viewModel.rightCommand)));
    }

    public static interface Gestures {

        public KeyGesture upGesture();

        public KeyGesture downGesture();

        public KeyGesture leftGesture();

        public KeyGesture rightGesture();

    }
}
