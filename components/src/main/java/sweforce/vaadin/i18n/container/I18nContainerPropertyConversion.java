package sweforce.vaadin.i18n.container;

import com.vaadin.ui.Component;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/18/12
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class I18nContainerPropertyConversion {

    private final Object containerPropertyId;

    private final Component owner;

    public I18nContainerPropertyConversion(Object containerPropertyId, Component owner) {
        this.containerPropertyId = containerPropertyId;
        this.owner = owner;
    }


}
