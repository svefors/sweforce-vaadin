package sweforce.command;

import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import sweforce.event.SimpleEventNotifier;
import sweforce.event.EventNotifier;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCommand implements Command {
//    private boolean executable = true;
//
//    protected void setExecutable(boolean executable) {
//        if (this.isExecutable() != executable) {
//            this.executable = executable;
//            isExecutableChangeNotifier.fireEvent();
//        }
//    }

    private Property.ValueChangeNotifier executableNotifier;

    private Property<Boolean> executable;

    protected AbstractCommand() {
        this(new ObjectProperty<Boolean>(Boolean.TRUE));
    }

    protected AbstractCommand(Property<Boolean> executable) {
        if (executable instanceof Property.ValueChangeNotifier)
            this.executableNotifier = (Property.ValueChangeNotifier) executable;
        this.executable = executable;
    }

    @Override
    public void execute() {
        if (executable.getValue())
            internalExecute();
    }

    @Override
    public Property.ValueChangeNotifier isExecutableNotifier() {
        return executableNotifier;
    }

    protected void setExecutable(boolean executable){
        this.executable.setValue(executable);
    }

    @Override
    public boolean isExecutable() {
        return this.executable.getValue();
    }

    protected abstract void internalExecute();

//    @Override
//    public Property.ValueChangeNotifier isExecutableNotifier() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void valueChange(Property.ValueChangeEvent event) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    //    @Override
//    public boolean isExecutable() {
//        return executable;
//    }
//
//    private final class IsExecutableChangeEventNotifier extends
//            SimpleEventNotifier<IsExecutableChangeEvent.Handler, IsExecutableChangeEvent> {
//
//        protected void fireEvent() {
//            fireEvent(new IsExecutableChangeEvent(AbstractCommand.this));
//        }
//
//    }
//
//    protected final IsExecutableChangeEventNotifier isExecutableChangeNotifier = new IsExecutableChangeEventNotifier();
//
//    @Override
//    public EventNotifier<IsExecutableChangeEvent.Handler> getEventNotifier() {
//        return isExecutableChangeNotifier;
//    }
}
