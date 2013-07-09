package sweforce.gui.activity;

/**
* Created with IntelliJ IDEA.
* User: msvefors
* Date: 7/9/13
* Time: 1:08 PM
* To change this template use File | Settings | File Templates.
*/
public interface ActivityProvider<T extends Activity>{
    public T provide();


    public static class FromInstance<T extends Activity> implements ActivityProvider<T>{
        private final T instance;

        public FromInstance(T instance) {
            this.instance = instance;
        }

        @Override
        public T provide() {
            return instance;
        }

        @Override
        public String toString() {
            return "FromInstance{" +
                    "instance=" + instance +
                    '}';
        }
    }

}
