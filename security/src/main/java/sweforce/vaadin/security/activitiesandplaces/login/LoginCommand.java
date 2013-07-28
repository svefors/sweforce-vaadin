package sweforce.vaadin.security.activitiesandplaces.login;

import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import sweforce.command.AbstractCommand;
import sweforce.event.EventBus;
import sweforce.event.SystemThrowableEvent;
import sweforce.vaadin.security.SecurityFacade;

/**

 */
public class LoginCommand extends AbstractCommand {

    private final SecurityFacade securityFacade;

    private final EventBus eventBus;

    private final Field<String> username;

    private final Field<String> password;

    public LoginCommand(SecurityFacade securityFacade, EventBus eventBus, Field<String> username, Field<String> password) {
        this.securityFacade = securityFacade;
        this.eventBus = eventBus;
        this.username = username;
        this.password = password;
        this.updateIsExecutable();
        username.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                updateIsExecutable();
            }
        });
        password.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                updateIsExecutable();
            }
        });
    }

    private void updateIsExecutable() {
        this.setExecutable(username.getValue() != null && password.getValue() != null);
    }


    @Override
    protected void internalExecute() {
        try {
            securityFacade.getSubject().login(username.getValue(), password.getValue());
            eventBus.fireEvent(new UserLoginSuccessEvent());
        } catch (SecurityFacade.UnknownAccountException uae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.IncorrectCredentialsException ice) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.LockedAccountException lae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.ExcessiveAttemptsException eae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.AuthenticationException ae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (Exception ex) {
            ex.printStackTrace();
            eventBus.fireEvent(new SystemThrowableEvent(ex));
        }
    }

    public void bind(EventBus eventBus) {

    }
}
