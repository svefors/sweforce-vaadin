package sweforce.command;

import com.vaadin.data.Property;
import sweforce.event.Event;
import sweforce.event.EventHandler;
import sweforce.event.EventNotifier;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Command {

    public void execute();


    public Property.ValueChangeNotifier isExecutableNotifier();

    public boolean isExecutable();


//    public boolean isExecutable();

//    public EventNotifier<IsExecutableChangeEvent.Handler> getEventNotifier();

//    public static class IsExecutableChangeEvent implements Event<IsExecutableChangeEvent.Handler> {
//        protected final Command command;
//
//        public IsExecutableChangeEvent(Command command) {
//            this.command = command;
//        }
//
//        @Override
//        public void dispatch(Handler handler) {
//            handler.onIsExecutableChange(command);
//        }
//
//        public static interface Handler extends EventHandler {
//            public void onIsExecutableChange(Command command);
//        }
//    }

}
