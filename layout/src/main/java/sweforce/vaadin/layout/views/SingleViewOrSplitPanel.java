package sweforce.vaadin.layout.views;

import com.vaadin.ui.*;
import sweforce.gui.display.Display;
import sweforce.gui.display.NullView;
import sweforce.gui.vaadin.VaadinView;
import sweforce.gui.display.View;
import sweforce.vaadin.layout.style2.Size;

/**
 *
 */
public class SingleViewOrSplitPanel extends CustomComponent {

    private final AbstractOrderedLayout orderedLayout;

    private final AbstractSplitPanel splitPanel;

    public SingleViewOrSplitPanel(AbstractOrderedLayout orderedLayout, AbstractSplitPanel splitPanel) {
        super(orderedLayout);
        this.splitPanel = splitPanel;
        this.orderedLayout = orderedLayout;
    }

    public SingleViewOrSplitPanel() {
        this(new VerticalLayout(), new HorizontalSplitPanel());
    }

    private Size filledSize;

    public Size getFilledSize() {
        return filledSize;
    }

    public void setFilledSize(Size filledSize) {
        this.filledSize = filledSize;
        refreshSize();
    }

    public boolean isEmpty() {
        return this.orderedLayout.getComponentCount() == 0;
    }

    private LayoutState layoutState = new EmptyLayoutState();

    LayoutState getLayoutState() {
        return layoutState;
    }

    private AbstractSplitPanel getSplitPanel() {
        return splitPanel;
    }

    private AbstractOrderedLayout getOrderedLayout() {
        return orderedLayout;
    }

    public void setMainComponent(Component component) {
        this.getLayoutState().setMainComponent(this, component);
    }

    public void setFirstComponent(Component component) {
        this.getLayoutState().setFirstComponent(this, component);
    }

    public void setSecondComponent(Component component) {
        this.getLayoutState().setSecondComponent(this, component);
    }

    public Display getMainComponentDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                if (view != NullView.getInstance())
                    setMainComponent(((VaadinView) view).asComponent());
                else
                    setMainComponent(null);
            }
        };
    }

    public Display getFirstComponentDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                if (view != NullView.getInstance())
                    setFirstComponent(((VaadinView) view).asComponent());
                else
                    setFirstComponent(null);
            }
        };
    }

    public Display getSecondComponentDisplay() {
        return new Display() {
            @Override
            public void setView(View view) {
                if (view != NullView.getInstance())
                    setSecondComponent(((VaadinView) view).asComponent());
                else
                    setSecondComponent(null);
            }
        };
    }

    public void refreshSize() {
        if (layoutState instanceof EmptyLayoutState) {
            if (filledSize != null) {
                this.setWidth(filledSize.getWidth(), filledSize.getWidthUnit());
                this.setHeight(filledSize.getHeight(), filledSize.getHeightUnit());
            }
        }
    }

    public void setLayoutState(LayoutState layoutState) {
        this.layoutState = layoutState;
        refreshSize();
    }


    private static interface LayoutState {

        public void setMainComponent(SingleViewOrSplitPanel layoutStateContext, Component newMainComponent);

        public void setFirstComponent(SingleViewOrSplitPanel layoutStateContext, Component newFirstComponent);

        public void setSecondComponent(SingleViewOrSplitPanel layoutStateContext, Component newSecondComponent);

    }

    private static class MainLayoutState implements LayoutState {

        @Override
        public void setMainComponent(SingleViewOrSplitPanel layoutStateContext, Component newMainComponent) {
            if (newMainComponent == null) {
                layoutStateContext.getOrderedLayout().removeAllComponents();
                layoutStateContext.setLayoutState(new EmptyLayoutState());
            } else {
                Component oldComponent = layoutStateContext.getOrderedLayout().getComponent(0);
                layoutStateContext.getOrderedLayout().replaceComponent(oldComponent, newMainComponent);
                layoutStateContext.setLayoutState(this);
            }
        }

        @Override
        public void setFirstComponent(SingleViewOrSplitPanel layoutStateContext, Component newFirstComponent) {
            if (newFirstComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                Component oldComponent = layoutStateContext.getOrderedLayout().getComponent(0);
                layoutStateContext.getOrderedLayout().replaceComponent(oldComponent,
                        layoutStateContext.getSplitPanel());
                layoutStateContext.getSplitPanel().setFirstComponent(newFirstComponent);
                layoutStateContext.setLayoutState(new SplitLayoutState());
            }
        }

        @Override
        public void setSecondComponent(SingleViewOrSplitPanel layoutStateContext, Component newSecondComponent) {
            if (newSecondComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                Component oldComponent = layoutStateContext.getOrderedLayout().getComponent(0);
                layoutStateContext.getOrderedLayout().replaceComponent(oldComponent,
                        layoutStateContext.getSplitPanel());
                layoutStateContext.getSplitPanel().setSecondComponent(newSecondComponent);
                layoutStateContext.setLayoutState(new SplitLayoutState());
            }
        }
    }

    private static class SplitLayoutState implements LayoutState {
        @Override
        public void setMainComponent(SingleViewOrSplitPanel layoutStateContext, Component newMainComponent) {
            if (newMainComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                layoutStateContext.getOrderedLayout().replaceComponent(
                        layoutStateContext.getSplitPanel(),
                        newMainComponent);
                layoutStateContext.getSplitPanel().removeAllComponents();
                layoutStateContext.setLayoutState(new MainLayoutState());
            }
        }

        @Override
        public void setFirstComponent(SingleViewOrSplitPanel layoutStateContext, Component newFirstComponent) {
            if (newFirstComponent == null) {
                layoutStateContext.getSplitPanel().setFirstComponent(null);
                if (layoutStateContext.getSplitPanel().getComponentCount() == 0) {
                    layoutStateContext.setLayoutState(new EmptyLayoutState());
                } else {
                    layoutStateContext.setLayoutState(this);
                }
            } else {
                layoutStateContext.getSplitPanel().setFirstComponent(newFirstComponent);
                layoutStateContext.setLayoutState(this);
            }
        }

        @Override
        public void setSecondComponent(SingleViewOrSplitPanel layoutStateContext, Component newSecondComponent) {
            if (newSecondComponent == null) {
                layoutStateContext.getSplitPanel().setSecondComponent(null);
                if (layoutStateContext.getSplitPanel().getComponentCount() == 0) {
                    layoutStateContext.setLayoutState(new EmptyLayoutState());
                } else {
                    layoutStateContext.setLayoutState(this);
                }
            } else {
                layoutStateContext.getSplitPanel().setSecondComponent(newSecondComponent);
                layoutStateContext.setLayoutState(this);
            }
        }
    }

    private static class EmptyLayoutState implements LayoutState {

        @Override
        public void setMainComponent(SingleViewOrSplitPanel layoutStateContext, Component newMainComponent) {
            if (newMainComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                layoutStateContext.getOrderedLayout().addComponent(newMainComponent);
                layoutStateContext.setLayoutState(new MainLayoutState());
            }
        }

        @Override
        public void setFirstComponent(SingleViewOrSplitPanel layoutStateContext, Component newFirstComponent) {
            if (newFirstComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                layoutStateContext.getOrderedLayout().addComponent(layoutStateContext.getSplitPanel());
                layoutStateContext.getSplitPanel().setFirstComponent(newFirstComponent);
                layoutStateContext.setLayoutState(new SplitLayoutState());
            }
        }

        @Override
        public void setSecondComponent(SingleViewOrSplitPanel layoutStateContext, Component newSecondComponent) {
            if (newSecondComponent == null) {
                layoutStateContext.setLayoutState(this);
            } else {
                layoutStateContext.getOrderedLayout().addComponent(layoutStateContext.splitPanel);
                layoutStateContext.getSplitPanel().setSecondComponent(newSecondComponent);
                layoutStateContext.setLayoutState(new SplitLayoutState());
            }
        }
    }

}
