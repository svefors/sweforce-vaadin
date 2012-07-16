package sweforce.vaadin.layout.section.ordered;

import org.junit.Test;
import sweforce.vaadin.layout.section.Section;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/10/12
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentComponentsTest {

    private enum TestSection implements Section {
        zero, one, two, three, four, five

    }

    @Test
    public void testGetIndexOfNotNullComponentNextTo_before_not_empty() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());

        currentComponents.put(TestSection.two, 2);

        Integer component = currentComponents.getClosestNotNullComponentAfter(TestSection.three);
        assertNull(component);
        component = currentComponents.getClosestNotNullComponentBefore(TestSection.three);
        assertEquals(2, component.intValue());
    }

    @Test
    public void testGetIndexOfNotNullComponentNextTo_after_not_empty() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());

        currentComponents.put(TestSection.four, 4);

        Integer component = currentComponents.getClosestNotNullComponentAfter(TestSection.three);
        assertEquals(4, component.intValue());
        component = currentComponents.getClosestNotNullComponentBefore(TestSection.three);
        assertNull(component);
    }

    @Test
    public void testGetIndexOfNotNullComponentNextTo_before_not_empty_edge_case() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());

        currentComponents.put(TestSection.four, 4);

        Integer component = currentComponents.getClosestNotNullComponentAfter(TestSection.five);
        assertNull(component);
        component = currentComponents.getClosestNotNullComponentBefore(TestSection.five);
        assertEquals(4, component.intValue());
    }

    @Test
    public void testGetIndexOfNotNullComponentNextTo_empty() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());

        Integer component = currentComponents.getClosestNotNullComponentAfter(TestSection.four);
        assertNull(component);
        component = currentComponents.getClosestNotNullComponentBefore(TestSection.four);
        assertNull(component);
    }

    @Test
    public void testGetIndexOfNotNullComponentNextTo_empty_but_for_section_itself() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());

        currentComponents.put(TestSection.three, 3);

        Integer component = currentComponents.getClosestNotNullComponentAfter(TestSection.three);
        assertNull(component);
        component = currentComponents.getClosestNotNullComponentBefore(TestSection.three);
        assertNull(component);
    }

    @Test
    public void testGetIndexOfSection() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());
        assertEquals(3, currentComponents.getIndexOf(TestSection.three));

    }

    @Test
    public void testGetIndexOfSection_notexisting() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());
        Section otherSection = new Section() {
        };
        assertEquals(-1, currentComponents.getIndexOf(otherSection));

    }

    @Test
    public void testPut() {
        CurrentComponents<Integer> currentComponents = new CurrentComponents<Integer>(TestSection.values());
        SectionedComponentSlot<Integer> sectionedComponent = currentComponents.put(TestSection.three, 123);
        assertEquals(TestSection.three, sectionedComponent.getSection());
        assertEquals(new Integer(123), sectionedComponent.getComponent());
        assertEquals(3, sectionedComponent.getIndex());

    }
}
