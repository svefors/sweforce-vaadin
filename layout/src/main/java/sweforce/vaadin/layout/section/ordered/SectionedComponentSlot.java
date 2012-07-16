package sweforce.vaadin.layout.section.ordered;

import com.vaadin.ui.Component;
import sweforce.vaadin.layout.section.Section;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/10/12
* Time: 1:00 PM
* To change this template use File | Settings | File Templates.
*/
class SectionedComponentSlot<T> {

    private final int index;

    private final Section section;

    private T component;

    SectionedComponentSlot(int index, Section section) {
        this.index = index;
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public T getComponent() {
        return component;
    }

    public void setComponent(T component) {
        this.component = component;
    }

    public int getIndex() {
        return index;
    }
}
