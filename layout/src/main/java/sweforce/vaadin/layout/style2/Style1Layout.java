package sweforce.vaadin.layout.style2;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import sweforce.gui.display.Display;
import sweforce.gui.display.View;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.HandlerRegistration;
import sweforce.vaadin.layout.section.Section;
import sweforce.vaadin.layout.section.SectionChangeEvent;

import static sweforce.gui.display.VaadinView.Util.convertViewToComponent;


/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/11/12
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Style1Layout extends VerticalLayout {


    private HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel() {
        private void show() {
            horizontalSplitPanel.setSizeFull();
        }

        private void hide() {
            horizontalSplitPanel.setWidth("0px");
            horizontalSplitPanel.setHeight("0px");
            horizontalSplitPanel.setVisible(false);
        }

        /**
         * if empty minimizes the Panel
         * @param c
         */
        @Override
        public void setFirstComponent(Component c) {
            super.setFirstComponent(c);    //To change body of overridden methods use File | Settings | File Templates.
            if (horizontalSplitPanel.getComponentCount() == 0) {
                hide();
            } else if (horizontalSplitPanel.getComponentCount() == 2) {
                show();
            } else {
                show();
            }
        }

        @Override
        public void setSecondComponent(Component c) {
            super.setSecondComponent(c);    //To change body of overridden methods use File | Settings | File Templates.
            if (horizontalSplitPanel.getComponentCount() == 0) {
                hide();
            } else if (horizontalSplitPanel.getComponentCount() == 2) {
                show();
            } else {
                show();
            }
        }
    };

    private VerticalLayout centreContainer = new VerticalLayout() {
        private void show() {
            centreContainer.setSizeFull();
        }

        private void hide() {
            centreContainer.setWidth("0px");
            centreContainer.setHeight("0px");
            centreContainer.setVisible(false);
        }

        private void updateVisibility() {
            if (centreContainer.getComponentCount() == 0) {
                hide();
            } else if (centreContainer.getComponentCount() > 0) {
                show();
            } else {
                show();
            }
        }

        @Override
        public void addComponent(Component c) {
            if (centreContainer.getComponentCount() != 0) {
                centreContainer.removeAllComponents();
            }
            super.addComponent(c);
            updateVisibility();
        }

//        @Override
//        public void removeComponent(Component c) {
//            super.removeComponent(c);
//            updateVisibility();
//        }

        {
            updateVisibility();
        }
    };

    private VerticalLayout topContainer = new VerticalLayout() {
        private void show() {
            topContainer.setWidth("100%");
            topContainer.setHeight("50px;");
        }

        private void hide() {
            topContainer.setWidth("0px");
            topContainer.setHeight("0px");
            topContainer.setVisible(false);
        }

        private void updateVisibility() {
            if (topContainer.getComponentCount() == 0) {
                this.hide();
            } else if (topContainer.getComponentCount() > 0) {
                this.show();
            } else {
                this.show();
            }
        }

        @Override
        public void addComponent(Component c) {
            if (topContainer.getComponentCount() != 0) {
                topContainer.removeAllComponents();
            }
            super.addComponent(c);
            this.updateVisibility();
        }


        {
            this.updateVisibility();
        }
    };


    public Display getTopDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                topContainer.addComponent(convertViewToComponent(view));
            }
        };
    }

    public Display getCenterDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                centreContainer.addComponent(convertViewToComponent(view));
            }
        };
    }

    public Display getLeftDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                horizontalSplitPanel.setFirstComponent(convertViewToComponent(view));
            }
        };
    }

    public Display getRightDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                horizontalSplitPanel.setSecondComponent(convertViewToComponent(view));
            }
        };
    }


    public Style1Layout() {
        buildLayout();
    }

    protected void buildLayout() {
        this.addComponent(topContainer);
        this.addComponent(horizontalSplitPanel);
        this.addComponent(centreContainer);
    }

}
