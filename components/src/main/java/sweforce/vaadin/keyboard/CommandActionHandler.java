package sweforce.vaadin.keyboard;

import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import sweforce.command.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/20/12
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandActionHandler implements Action.Handler {

//    private final Iterable<KeyBinding> keyBindings;

    private final Map<Action, Command> actionCommandMap;// = new HashMap<Action, Command>();

    public CommandActionHandler(Map<Action, Command> actionCommandMap) {
        this.actionCommandMap = actionCommandMap;
    }

    @Override
    public Action[] getActions(Object target, Object sender) {
        return actionCommandMap.keySet().toArray(new Action[0]);
    }

    @Override
    public void handleAction(Action action, Object sender, Object target) {
        Command command = actionCommandMap.get(action);
        if (command != null)
            command.execute();
    }

    public static Map<Action, Command> actionCommandMap(Iterable<KeyBinding> keyBindings) {
        Map<Action, Command> actionCommandMap = new HashMap<Action, Command>();
        for (KeyBinding binding : keyBindings) {
            ShortcutAction shortcutAction = new ShortcutAction(
                    binding.getKeyGesture().getCaption(),
                    binding.getKeyGesture().getShortcutKey(),
                    binding.getKeyGesture().getModifierKeys()
            );
            actionCommandMap.put(shortcutAction, binding.getCommand());
        }
        return actionCommandMap;
    }

    public static Map<Action, Command> actionCommandMap(KeyBinding... keyBindings) {
        List<KeyBinding> bindings = new ArrayList<KeyBinding>();
        for (KeyBinding binding : keyBindings) {
            bindings.add(binding);
        }
        return actionCommandMap(bindings);
    }

    public static CommandActionHandler create(Iterable<KeyBinding> keyBindings) {
        return new CommandActionHandler(actionCommandMap(keyBindings));
    }

    public static CommandActionHandler create(KeyBinding... keyBindings) {
        return new CommandActionHandler(actionCommandMap(keyBindings));
    }


}
