package sweforce.vaadin.layout.style1;


import com.vaadin.ui.VerticalLayout;
import sweforce.gui.display.Display;
import sweforce.gui.display.VaadinView;
import sweforce.gui.display.View;
import sweforce.vaadin.layout.display.region.RegionalDisplay;
import sweforce.vaadin.layout.display.region.RegionalDisplayActivityManager;
import sweforce.vaadin.layout.display.SingleViewOrSplitPanel;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This layout has a Top toolbar, a horizontal split with a left menubar and right content, and finally, a center content
 * region. The Split view and Center view are mutually exclusive and can't viewed at the same time
 */
@Named("style-1")
public class Style1Layout extends VerticalLayout implements RegionalDisplay {

    private SingleViewOrSplitPanel singleViewOrSplitPanel = new SingleViewOrSplitPanel();

    private VerticalLayout toolbarContainer = new VerticalLayout();

    /**
     *
     * @param regionalPresenter presenter responsible for setDisplay(region, display)
     */
    @Inject
    public Style1Layout(RegionalDisplayActivityManager regionalPresenter) {
        this.addComponent(singleViewOrSplitPanel);
        regionalPresenter.addRegionalDisplay(this);
    }




    public Display getDisplay(sweforce.vaadin.layout.display.region.Region region) {
        if (!(region instanceof Region))
            throw new IllegalArgumentException("wrong type of region");
        switch ((Region) region) {
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

    /**
     * DO NOT ADD OR REMOVE COMPONENTS TO THE CONTAINER RETURNED!
     * @return
     */
    public VerticalLayout getToolbarContainer() {
        return toolbarContainer;
    }

    /**
     * DO NOT ADD OR REMOVE COMPONENTS TO THE CONTAINER RETURNED!
     * @return
     */
    public SingleViewOrSplitPanel getSingleViewOrSplitPanel() {
        return singleViewOrSplitPanel;
    }

    public Display getTopDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                if (view != null) {
                    if (toolbarContainer.getComponentCount() == 1) {
                        //there is only one component, and that is the split thingy. do nothing
                        toolbarContainer.replaceComponent(getComponent(0), ((VaadinView) view).asComponent());
                    } else if (toolbarContainer.getComponentCount() == 0){
                        toolbarContainer.addComponent(((VaadinView) view).asComponent());
                    } else{
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
    public Set<sweforce.vaadin.layout.display.region.Region> getRegions() {
        HashSet<sweforce.vaadin.layout.display.region.Region> regions =
                new HashSet<sweforce.vaadin.layout.display.region.Region>();
        regions.addAll(Arrays.asList(Region.values()));
        return regions;
    }

    public static enum Region implements sweforce.vaadin.layout.display.region.Region {

        TOOLBAR, SPLIT_LEFT, SPLIT_RIGHT, MAIN


    }

}
