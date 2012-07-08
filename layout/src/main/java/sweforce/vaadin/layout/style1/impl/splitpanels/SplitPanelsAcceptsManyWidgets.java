package sweforce.vaadin.layout.style1.impl.splitpanels;

import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import sweforce.gui.ap.vaadin.IsVaadinWidget;
import sweforce.gui.view.AcceptsOneWidget;
import sweforce.gui.view.IsWidget;
import sweforce.vaadin.layout.LayoutSection;
import sweforce.vaadin.layout.view.AcceptsManyWidgets;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplitPanelsAcceptsManyWidgets implements AcceptsManyWidgets{

    private LayoutSection section1;

    private LayoutSection section2;

    private Component component1;

    private Component component2;

    private Layout rootLayout;

    private AbstractSplitPanel abstractSplitPanel;

    private int lastKnownNotZeroSplitPosition;


    public SplitPanelsAcceptsManyWidgets(LayoutSection section1, LayoutSection section2, AbstractSplitPanel abstractSplitPanel) {
        this.section1 = section1;
        this.section2 = section2;
        this.abstractSplitPanel = abstractSplitPanel;
    }

    @Override
    public Iterable<LayoutSection> getLayoutSections() {
        return Arrays.asList(new LayoutSection[]{section1, section2});
    }

    @Override
    public AcceptsOneWidget getAcceptsOneWidget(LayoutSection layoutSection) {
        if (section1.equals(layoutSection)){
            return new AcceptsOneWidget() {
                @Override
                public void setWidget(IsWidget<?> w) {
                    component1 = ((IsVaadinWidget) w).asWidget();
                    onComponentChange();
                }
            };
        }else if (section2.equals(layoutSection)){
            return new AcceptsOneWidget() {
                @Override
                public void setWidget(IsWidget<?> w) {
                    component2 = ((IsVaadinWidget) w).asWidget();
                    onComponentChange();
                }
            };
        }
        return null;
    }


    private void onComponentChange(){
        if (component1 == null && component2 == null){
            abstractSplitPanel.setVisible(false);
        }else if (component1 != null && component2 == null){
            abstractSplitPanel.setStyleName("hide-split");
            abstractSplitPanel.setSplitPosition(0);
        }else if (component2 != null && component1 == null){
            abstractSplitPanel.setStyleName("hide-split");
            abstractSplitPanel.setSplitPosition(0, true);
        }else{

        }

    }

    @Override
    public boolean isEmpty() {
        return component1 == null && component2 == null;
    }
}
