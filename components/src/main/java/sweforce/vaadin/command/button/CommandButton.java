package sweforce.vaadin.command.button;

import com.vaadin.ui.Button;
import sweforce.command.Command;
import sweforce.event.HandlerRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 9:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandButton extends Button {

    private HandlerRegistration reg;

    private ClickListener clickListener;

    public CommandButton() {
    }

    public CommandButton(Command command) {
        setCommand(command);
    }

    public CommandButton(String caption) {
        super(caption);
    }

    public CommandButton(String caption, Command command) {
        super(caption);
        setCommand(command);
    }

    public CommandButton(String caption, ClickListener listener, Command command) {
        super(caption, listener);
        setCommand(command);
    }

    public CommandButton(String caption, ClickListener listener) {
        super(caption, listener);
    }

    public void setCommand(final Command command) {
        if (this.reg != null)
            reg.removeHandler();
        setEnabled(command.isExecutable());
        reg = command.getEventNotifier().addHandler(new Command.IsExecutableChangeEvent.Handler() {
            @Override
            public void onIsExecutableChange(Command command) {
                if (command.isExecutable()){
                    CommandButton.this.setEnabled(true);
                }else{
                    CommandButton.this.setEnabled(false);
                }
            }
        });
        if (this.clickListener != null){
            this.removeClickListener(this.clickListener);
        }
        this.clickListener = new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                command.execute();
            }
        };
        this.addClickListener(this.clickListener);
    }
}
