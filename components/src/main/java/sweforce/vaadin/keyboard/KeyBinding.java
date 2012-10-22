package sweforce.vaadin.keyboard;

import sweforce.command.Command;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/20/12
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public interface KeyBinding {

    public KeyGesture getKeyGesture();

    public Command getCommand();

    public static class DefaultImpl implements KeyBinding {
        private final KeyGesture keyGesture;
        private final Command command;

        public DefaultImpl(KeyGesture keyGesture, Command command) {
            this.keyGesture = keyGesture;
            this.command = command;
        }

        public KeyGesture getKeyGesture() {
            return keyGesture;
        }

        public Command getCommand() {
            return command;
        }
    }
}
