package sweforce.vaadin.layout.style1;


import com.vaadin.ui.VerticalLayout;
import sweforce.gui.display.Display;
import sweforce.gui.display.NullView;
import sweforce.gui.region.Region;
import sweforce.gui.region.RegionalDisplay;

import sweforce.gui.vaadin.VaadinView;
import sweforce.gui.display.View;

import sweforce.vaadin.layout.views.SingleViewOrSplitPanel;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This layout has a TOP toolbar, a horizontal split with LEFT and RIGHT , and a MAIN content.
 * region. The Split displays and MAIN display are mutually exclusive and can't viewed at the same time
 */
public class Style1Layout extends VerticalLayout implements RegionalDisplay {

    private SingleViewOrSplitPanel singleViewOrSplitPanel = new SingleViewOrSplitPanel();

    private VerticalLayout toolbarContainer = new VerticalLayout();


    public Style1Layout() {
        this.addComponent(toolbarContainer);
        this.addComponent(singleViewOrSplitPanel);
    }


    public Display getDisplay(Region region) {
        if (!(region instanceof MyRegion))
            throw new IllegalArgumentException("wrong type of region");
        switch ((MyRegion) region) {
            case SPLIT_LEFT:
                return singleViewOrSplitPanel.getFirstComponentDisplay();
            case SPLIT_RIGHT:
                return singleViewOrSplitPanel.getSecondComponentDisplay();
            case MAIN:
                return singleViewOrSplitPanel.getMainComponentDisplay();
            case TOOLBAR:
                return getTopDisplay();
            default:
                throw new IllegalArgumentException("unknown region");
        }
    }

    public Display getTopDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                if (view != null && view != NullView.getInstance()) {
                    if (toolbarContainer.getComponentCount() == 1) {
                        //there is only one component, and that is the split thingy. do nothing
                        toolbarContainer.replaceComponent(getComponent(0), ((VaadinView) view).asComponent());
                    } else if (toolbarContainer.getComponentCount() == 0) {
                        toolbarContainer.addComponent(((VaadinView) view).asComponent());
                    } else {
                        throw new IllegalStateException("illegal number of components");
                    }
                } else {
                    toolbarContainer.removeAllComponents();
                }
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    @Override
    public Set<Region> getRegions() {
        HashSet<Region> regions =
                new HashSet<Region>();
        regions.addAll(Arrays.asList(MyRegion.values()));
        return regions;
    }

    public static enum MyRegion implements sweforce.gui.region.Region {

        TOOLBAR, SPLIT_LEFT, SPLIT_RIGHT, MAIN


    }

}
