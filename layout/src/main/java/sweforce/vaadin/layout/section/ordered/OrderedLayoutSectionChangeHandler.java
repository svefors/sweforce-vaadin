package sweforce.vaadin.layout.section.ordered;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import sweforce.vaadin.layout.section.AbstractSectionChangeHandler;
import sweforce.vaadin.layout.section.Section;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderedLayoutSectionChangeHandler extends AbstractSectionChangeHandler {

    private CurrentComponents<Component> currentComponents;

    private AbstractOrderedLayout abstractOrderedLayout;

    public OrderedLayoutSectionChangeHandler(AbstractOrderedLayout abstractOrderedLayout, Section... sections) {
        this.abstractOrderedLayout = abstractOrderedLayout;
        this.currentComponents = new CurrentComponents<Component>(sections);
    }

    @Override
    protected boolean isSectionHandled(Section section) {
        //TODO change current components. this method call is confusing.
        return currentComponents.getIndexOf(section) != -1;
    }

    @Override
    protected void replaceSectionComponent(Section section, Component component) {
        SectionedComponentSlot<Component> sectionedComponentSlot =
                currentComponents.getSectionedComponentSlot(section);
        Component closestComponent = currentComponents.getClosestNotNullComponentAfter(section);
        if (closestComponent != null) {
            int insertOnIndex = abstractOrderedLayout.getComponentIndex(closestComponent);
            //we want to shift all of the components after the component to the right (or down)
            abstractOrderedLayout.addComponent(component, insertOnIndex);
        } else  {
            abstractOrderedLayout.addComponent(component);
        }
        sectionedComponentSlot.setComponent(component);
    }


    @Override
    protected void removeComponentFromSection(Section section) {
        SectionedComponentSlot<Component> sectionedComponentSlot =
                currentComponents.getSectionedComponentSlot(section);
        //potientially animate
        abstractOrderedLayout.removeComponent(sectionedComponentSlot.getComponent());
        sectionedComponentSlot.setComponent(null);
    }

    @Override
    protected void hideSection(Section section) {
        SectionedComponentSlot<Component> sectionedComponentSlot =
                currentComponents.getSectionedComponentSlot(section);
        //potientially animate
        abstractOrderedLayout.removeComponent(sectionedComponentSlot.getComponent());
        sectionedComponentSlot.setComponent(null);
    }

    /*
    @Override
            public void setView(View view) {
                SectionedComponentSlot<Component> sectionedComponentSlot =
                        currentComponents.getSectionedComponentSlot(section);
                //TODO decide what to do about animations on closing
                if (NullView.getInstance().equals(view)) {

                } else {
                    if (view == null) {
                        if (sectionedComponentSlot.getComponent() != null) {
                            abstractOrderedLayout.removeComponent(sectionedComponentSlot.getComponent());
                            sectionedComponentSlot.setComponent(null);
                        }
                    } else if (view instanceof VaadinView) {
                        if (sectionedComponentSlot.getComponent() != null) {
                            abstractOrderedLayout.replaceComponent(sectionedComponentSlot.getComponent(), convertViewToComponent(view));
                            sectionedComponentSlot.setComponent(convertViewToComponent(view));
                        } else {
                            Component closestComponent = currentComponents.getClosestNotNullComponentAfter(section);
                            if (closestComponent != null) {
                                int insertOnIndex = abstractOrderedLayout.getComponentIndex(closestComponent);
                                //we want to shift all of the components to the right (or down)
                                abstractOrderedLayout.addComponent((Component) view, insertOnIndex);
                            } else if ((closestComponent = currentComponents.getClosestNotNullComponentAfter(section)) != null) {
                                int insertOnIndex = abstractOrderedLayout.getComponentIndex(closestComponent);
                                //check if we just want to add it last
                                if (insertOnIndex + 1 == abstractOrderedLayout.getComponentCount()) {
                                    //just add the component (it will be to the right or below)
                                    abstractOrderedLayout.addComponent(convertViewToComponent(view));
                                } else {
                                    abstractOrderedLayout.addComponent(convertViewToComponent(view), insertOnIndex);
                                }
                            } else {
                                abstractOrderedLayout.addComponentAsFirst(convertViewToComponent(view));
                            }


                        }
                    } else {
                        //it is not null and it is not a vaadin component!
                    }
                }
            }
        }
     */
}
