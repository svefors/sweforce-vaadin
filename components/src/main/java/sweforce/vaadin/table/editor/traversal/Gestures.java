package sweforce.vaadin.table.editor.traversal;

import com.vaadin.event.ShortcutAction;
import sweforce.vaadin.keyboard.KeyGesture;

/**
* Created with IntelliJ IDEA.
* User: SERVICE-NOW\mats.svefors
* Date: 09/12/2012
* Time: 00:00
* To change this template use File | Settings | File Templates.
*/
public interface Gestures {

    public KeyGesture upGesture();

    public KeyGesture downGesture();

    public KeyGesture leftGesture();

    public KeyGesture rightGesture();

    public static class Default implements Gestures {
        @Override
        public KeyGesture upGesture() {
            return new KeyGesture.DefaultImpl("Up", ShortcutAction.KeyCode.ARROW_UP, ShortcutAction.ModifierKey.CTRL);
        }

        @Override
        public KeyGesture downGesture() {
            return new KeyGesture.DefaultImpl("Down", ShortcutAction.KeyCode.ARROW_DOWN, ShortcutAction.ModifierKey.CTRL);
        }

        @Override
        public KeyGesture leftGesture() {
            return new KeyGesture.DefaultImpl("Left", ShortcutAction.KeyCode.TAB, ShortcutAction.ModifierKey.SHIFT);
        }

        @Override
        public KeyGesture rightGesture() {
            return new KeyGesture.DefaultImpl("Right", ShortcutAction.KeyCode.TAB);
        }
    }
}
