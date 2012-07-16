package sweforce.vaadin.layout.section;

import sweforce.gui.display.View;
import sweforce.gui.event.Event;
import sweforce.gui.event.EventHandler;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SectionChangeEvent implements Event<SectionChangeEvent.Handler> {

    private final Section section;

    private final View newView;

    public SectionChangeEvent(Section section, View newView) {
        this.section = section;
        this.newView = newView;
    }

    public Section getSection() {
        return section;
    }

    public View getNewView() {
        return newView;
    }


    @Override
    public void dispatch(Handler handler) {
        handler.onSectionViewChange(section, newView);
    }

    public static interface Handler extends EventHandler {

        /**
         * invoked when a sectionview has been changed
         * @param section the section changed
         * @param newView the new view to be set
         */
        public void onSectionViewChange(Section section, View newView);

    }
}
