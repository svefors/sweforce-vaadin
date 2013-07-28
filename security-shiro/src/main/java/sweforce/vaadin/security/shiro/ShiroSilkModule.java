package sweforce.vaadin.security.shiro;

import se.jbee.inject.bind.BinderModule;
import sweforce.vaadin.security.SecurityFacade;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/21/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShiroSilkModule extends BinderModule {

    @Override
    protected void declare() {
        bind(SecurityFacade.class).to(ShiroSecurityFacade.class);
        bind(ShiroSecurityModule.class).toConstructor();
    }
}
