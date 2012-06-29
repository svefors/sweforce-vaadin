package sweforce.vaadin.layout.activity;

import sweforce.gui.ap.activity.ActivityManager;
import sweforce.gui.event.EventBus;
import sweforce.vaadin.layout.LayoutSection;

/**
 * A Convenience class
 */
public class LayoutSectionActivityManager<LS extends LayoutSection> extends ActivityManager {

    public LayoutSectionActivityManager(LayoutSectionActivityMapper<LS> mapper, EventBus eventBus) {
        super(mapper, eventBus);
    }
}
