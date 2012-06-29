package sweforce.vaadin.layout.view;

import sweforce.gui.view.AcceptsOneWidget;
import sweforce.gui.view.IsWidget;
import sweforce.vaadin.layout.LayoutSection;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutSectionAcceptsOneWidget implements AcceptsOneWidget{

    private final LayoutSection layoutSection;

    private final AcceptsManyWidgets acceptsManyWidgets;


    public LayoutSectionAcceptsOneWidget(LayoutSection layoutSection, AcceptsManyWidgets acceptsManyWidgets) {
        this.layoutSection = layoutSection;
        this.acceptsManyWidgets = acceptsManyWidgets;
    }

    @Override
    public void setWidget(IsWidget<?> w) {
        acceptsManyWidgets.getAcceptsOneWidget(this.layoutSection).setWidget(w);
    }
}
