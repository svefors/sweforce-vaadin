package sweforce.vaadin.layout.section.ordered;

import sweforce.vaadin.layout.section.Section;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/10/12
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentComponents<T> {

    private SectionedComponentSlot<T>[] sectionedComponents;

    public CurrentComponents(Section... sections) {
        this.sectionedComponents = new SectionedComponentSlot[sections.length];
        for (int i = 0; i < sections.length; i++) {
            this.sectionedComponents[i] = new SectionedComponentSlot<T>(i, sections[i]);
        }
    }

    /**
     * or -1 if section is not present
     *
     * @param section
     * @return
     */
    public int getIndexOf(Section section) {
        for (SectionedComponentSlot sectionedComponent : sectionedComponents) {
            if (sectionedComponent.getSection().equals(section)) {
                return sectionedComponent.getIndex();
            }
        }
        return -1;
    }

    public SectionedComponentSlot<T> getSectionedComponentSlot(Section section) {
        for (SectionedComponentSlot<T> sectionedComponent : sectionedComponents) {
            if (sectionedComponent.getSection().equals(section)) {
                return sectionedComponent;
            }
        }
        return null;
    }

//    public int getIndexOfClosestNotNullComponentAfter(Section section) {
//        int sectionIndex = getIndexOf(section);
//        for (int i = sectionIndex + 1; i < sectionedComponents.length; i++) {
//            if (sectionedComponents[i].getComponent() != null) {
//                return sectionedComponents[i].getIndex();
//            }
//        }
//        return -1;
//    }

    public T getClosestNotNullComponentAfter(Section section) {
        int sectionIndex = getIndexOf(section);
        for (int i = sectionIndex + 1; i < sectionedComponents.length; i++) {
            if (sectionedComponents[i].getComponent() != null) {
                return sectionedComponents[i].getComponent();
            }
        }
        return null;
    }

    public T getClosestNotNullComponentBefore(Section section) {
        int sectionIndex = getIndexOf(section);
        for (int i = sectionIndex - 1; i >= 0; i--) {
            if (sectionedComponents[i].getComponent() != null) {
                return sectionedComponents[i].getComponent();
            }
        }
        return null;
    }

//    public int getIndexOfClosestNotNullComponentBefore(Section section) {
//        int sectionIndex = getIndexOf(section);
//        for (int i = sectionIndex - 1; i >= 0; i--) {
//            if (sectionedComponents[i].getComponent() != null) {
//                return sectionedComponents[i].getIndex();
//            }
//        }
//        return -1;
//    }

//    public int getIndexOfNotNullComponentNextTo(Section section) {
//        int sectionIndex = getIndexOf(section);
//        for (int i = sectionIndex + 1; i < sectionedComponents.length; i++) {
//            if (sectionedComponents[i].getComponent() != null) {
//                return sectionedComponents[i].getIndex();
//            }
//        }
//        for (int i = sectionIndex - 1; i >= 0; i--) {
//            if (sectionedComponents[i].getComponent() != null) {
//                return sectionedComponents[i].getIndex();
//            }
//        }
//        return -1;
//    }

    public SectionedComponentSlot put(Section section, T component) {
        for (SectionedComponentSlot sectionedComponent : sectionedComponents) {
            if (sectionedComponent.getSection().equals(section)) {
                sectionedComponent.setComponent(component);
                return sectionedComponent;
            }
        }
        return null;
    }


}
