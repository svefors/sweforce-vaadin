package sweforce.vaadin.table.editor.fieldfactory;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;

/**
 *
 */
public class DiscriminateTableFieldFactoryWrapper implements TableFieldFactory {

    private final TableFieldFactory tableFieldFactory;

    private FieldCreationDiscriminator fieldCreationDiscriminator;

    public DiscriminateTableFieldFactoryWrapper(TableFieldFactory tableFieldFactory) {
        this.tableFieldFactory = tableFieldFactory;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        if (fieldCreationDiscriminator.passesDiscrimination(container, itemId, propertyId, uiContext)){
            return tableFieldFactory.createField(container, itemId, propertyId, uiContext);
        }
        return null;
    }


}
