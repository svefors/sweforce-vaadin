package sweforce.vaadin.layout.section;

import sweforce.gui.display.Display;
import sweforce.gui.display.View;
import sweforce.gui.event.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSectionDisplay implements Display {

    private final Section section;

    private final EventBus eventBus;

    public SimpleSectionDisplay(Section section, EventBus eventBus) {
        this.section = section;
        this.eventBus = eventBus;
    }

    @Override
    public void setView(View view) {
        eventBus.fireEvent(new SectionChangeEvent(section, view));
    }


}
