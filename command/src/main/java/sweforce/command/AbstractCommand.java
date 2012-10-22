package sweforce.command;

import sweforce.event.AbstractEventNotifier;
import sweforce.event.EventNotifier;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCommand implements Command {

    private boolean executable = true;

    protected void setExecutable(boolean executable) {
        if (this.isExecutable() != executable) {
            this.executable = executable;
            isExecutableChangeNotifier.fireEvent();
        }
    }

    @Override
    public void execute() {
        if (isExecutable())
            internalExecute();
    }

    protected abstract void internalExecute();

    @Override
    public boolean isExecutable() {
        return executable;
    }

    private final class IsExecutableChangeEventNotifier extends
            AbstractEventNotifier<IsExecutableChangeEvent.Handler, IsExecutableChangeEvent> {

        protected void fireEvent() {
            fireEvent(new IsExecutableChangeEvent(AbstractCommand.this));
        }

    }

    protected final IsExecutableChangeEventNotifier isExecutableChangeNotifier = new IsExecutableChangeEventNotifier();

    @Override
    public EventNotifier<IsExecutableChangeEvent.Handler> getEventNotifier() {
        return isExecutableChangeNotifier;
    }
}
