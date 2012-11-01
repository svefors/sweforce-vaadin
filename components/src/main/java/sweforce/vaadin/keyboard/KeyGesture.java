package sweforce.vaadin.keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/20/12
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface KeyGesture {

    public String getCaption();

    public int[] getModifierKeys();

    public int getShortcutKey();

    public static class DefaultImpl implements KeyGesture {

        private final String caption;

        private final int shortcutKey;

        private final int[] modifierKeys;

        public DefaultImpl(String caption, int shortcutKey, int... modifierKeys) {
            this.caption = caption;
            this.shortcutKey = shortcutKey;
            this.modifierKeys = modifierKeys;
        }

        public DefaultImpl(String caption, int shortcutKey) {
            this(caption, shortcutKey, new int[]{});
        }

        public String getCaption() {
            return caption;
        }

        public int getShortcutKey() {
            return shortcutKey;
        }

        public int[] getModifierKeys() {
            return modifierKeys;
        }
    }
}
