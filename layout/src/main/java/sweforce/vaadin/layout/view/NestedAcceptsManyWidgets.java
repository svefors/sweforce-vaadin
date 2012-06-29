package sweforce.vaadin.layout.view;

import sweforce.gui.view.AcceptsOneWidget;
import sweforce.gui.view.IsWidget;
import sweforce.vaadin.layout.LayoutSection;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class NestedAcceptsManyWidgets implements AcceptsManyWidgets{

    private AcceptsManyWidgets inner;

    public NestedAcceptsManyWidgets(AcceptsManyWidgets inner) {
        this.inner = inner;
    }

    @Override
    public Iterable<LayoutSection> getLayoutSections() {
        return inner.getLayoutSections();
    }

    @Override
    public AcceptsOneWidget getAcceptsOneWidget(LayoutSection layoutSection) {
        return inner.getAcceptsOneWidget(layoutSection);
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }
}
