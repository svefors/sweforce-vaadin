package sweforce.vaadin.layout.section;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import sweforce.gui.display.NullView;
import sweforce.gui.display.VaadinView;
import sweforce.gui.display.View;

import static sweforce.gui.display.VaadinView.Util.convertViewToComponent;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSectionChangeHandler implements SectionChangeEvent.Handler {


    @Override
    public void onSectionViewChange(Section section, View view) {
        if (isSectionHandled(section)) {
            if (NullView.getInstance().equals(view)) {
                //this view is used while trying to stop an activity.
                //so, just remove the old view. or fade out.
                //when the activity is null then the view wil be set to null.
                this.removeComponentFromSection(section);
            } else {
                if (view == null) {
                    this.hideSection(section);
                } else if (view instanceof VaadinView) {
                    this.replaceSectionComponent(section, convertViewToComponent(view));
                } else {
                    this.replaceSectionComponent(section, new Label("Error! view is not instanceof VaadinView"));
                }
            }
        }
    }

    /**
     * @param section
     * @param component
     */
    protected abstract void replaceSectionComponent(Section section, Component component);

    protected abstract boolean isSectionHandled(Section section);

    /**
     * use this method to remove the component.
     *
     * @param section
     */
    protected abstract void removeComponentFromSection(Section section);

    /**
     * use this method to clear the section. this will also maximize the other panel
     * if there is a component there
     */
    protected abstract void hideSection(Section section);
}
