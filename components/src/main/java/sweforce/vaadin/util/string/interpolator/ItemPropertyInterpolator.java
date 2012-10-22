package sweforce.vaadin.util.string.interpolator;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.string.interpolator.VariableInterpolator;

import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/18/12
 * Time: 8:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemPropertyInterpolator extends VariableInterpolator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * the Item to do the introspect on
     */
    private final Item item;

    public ItemPropertyInterpolator(String string, Item item) {
        super(string);
        this.item = item;
    }

    @Override
    protected String getValue(String variableName) {
        Property p = item.getItemProperty(variableName);
        if (p != null && p.getValue() != null)
            return toString(p.getValue());
        else
            return null;
    }

    /**
     * Convert the given value to a string for interpolation.
     * <p>
     * This default implementation delegates to {@link Strings#toString(Object)}.
     *
     * @param value
     *            the value, never {@code null}
     *
     * @return string representation
     */
    protected String toString(Object value)
    {
        return Strings.toString(value);
    }
}
