package sweforce.vaadin.layout.view;

import sweforce.gui.view.AcceptsOneWidget;
import sweforce.vaadin.layout.LayoutSection;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AcceptsManyWidgets /*extends AcceptsOneWidget*/{

    public Iterable<LayoutSection> getLayoutSections();

    public AcceptsOneWidget getAcceptsOneWidget(LayoutSection layoutSection);

    public boolean isEmpty();

}
