package sweforce.gui.region;

import com.vaadin.ui.HasComponents;
import sweforce.gui.display.Display;


import java.util.Set;

/**
 *
 */
public interface RegionalDisplay extends HasComponents.ComponentAttachDetachNotifier {

    public Display getDisplay(Region region);

    public Set<Region> getRegions();

    @Override
    void addComponentAttachListener(HasComponents.ComponentAttachListener listener);

    @Override
    void removeComponentAttachListener(HasComponents.ComponentAttachListener listener);

    @Override
    void addComponentDetachListener(HasComponents.ComponentDetachListener listener);

    @Override
    void removeComponentDetachListener(HasComponents.ComponentDetachListener listener);

}
