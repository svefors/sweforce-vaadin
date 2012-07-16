package sweforce.vaadin.layout.section.split;

import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Component;
import sweforce.vaadin.layout.section.AbstractSectionChangeHandler;
import sweforce.vaadin.layout.section.Section;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplitPanelSectionChangeHandler extends AbstractSectionChangeHandler {

    private final Section sectionOne;

    private final Section sectionTwo;

    private final AbstractSplitPanel abstractSplitPanel;

    public SplitPanelSectionChangeHandler(Section sectionOne, Section sectionTwo, AbstractSplitPanel abstractSplitPanel) {
        this.sectionOne = sectionOne;
        this.sectionTwo = sectionTwo;
        this.abstractSplitPanel = abstractSplitPanel;
    }

    protected boolean isSectionHandled(Section section) {
        return sectionOne.equals(section) || (sectionTwo.equals(section));
    }

    /**
     * use this method to remove the component.
     *
     * @param section
     */
    protected void removeComponentFromSection(Section section) {
        if (sectionOne.equals(section)) {
            abstractSplitPanel.removeComponent(this.abstractSplitPanel.getFirstComponent());
        } else if (sectionTwo.equals(section)) {
            abstractSplitPanel.removeComponent(this.abstractSplitPanel.getSecondComponent());
        }

    }

    /**
     * use this method to clear the section. this will also maximize the other panel
     * if there is a component there
     */
    protected void hideSection(Section section) {
        if (sectionOne.equals(section)) {
            if (this.abstractSplitPanel.getFirstComponent() != null) {
                this.abstractSplitPanel.removeComponent(this.abstractSplitPanel.getFirstComponent());
            }
        } else if (sectionOne.equals(section)) {
            if (this.abstractSplitPanel.getSecondComponent() != null) {
                this.abstractSplitPanel.removeComponent(this.abstractSplitPanel.getSecondComponent());
            }
        }
    }

    /**
     * @param section
     * @param component
     */
    protected void replaceSectionComponent(Section section, Component component) {
        if (sectionOne.equals(section)) {
            abstractSplitPanel.replaceComponent(this.abstractSplitPanel.getFirstComponent(), component);
        } else if (sectionTwo.equals(section)) {
            abstractSplitPanel.replaceComponent(this.abstractSplitPanel.getSecondComponent(), component);
        }
    }
}
