/*
 * Copyright 2012 Mats Svefors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sweforce.vaadin.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import sweforce.gui.ap.web.vaadin.IsVaadinWidget;
import sweforce.gui.view.AcceptsOneWidget;
import sweforce.gui.view.IsWidget;


/**
 * TODO: rewrite this, split into
 */
public class LayoutContainer extends VerticalLayout {

    private Component north;

    private Component south;

    private MiddleRow middleRow;


    private final AcceptsOneWidget northAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setNorth(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    private final AcceptsOneWidget westAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setWest(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    private final AcceptsOneWidget centerAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setCenter(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    private final AcceptsOneWidget eastAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setEast(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    private final AcceptsOneWidget detailsAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setDetails(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    private final AcceptsOneWidget southAcceptsOneWidget = new AcceptsOneWidget() {
        @Override
        public void setWidget(IsWidget w) {
            setSouth(w == null ? null : ((IsVaadinWidget) w).asWidget());
        }
    };

    /**
     * has three cells of different size
     */
    private class MiddleRow extends HorizontalLayout {

        private Component west;

        private MiddleRowMiddleColumn middleColumn = new MiddleRowMiddleColumn();

        private Component east;

        private MiddleRow() {
            this.setSizeFull();
            LayoutContainer.this.addComponent(this);
            LayoutContainer.this.setExpandRatio(this, 1.0f);
            LayoutContainer.this.setComponentAlignment(this, Alignment.TOP_LEFT);
        }

        private class MiddleRowMiddleColumn extends VerticalLayout {

            private Component center;

            private Component details;

            private MiddleRowMiddleColumn() {
                this.setSizeFull();
                MiddleRow.this.addComponent(this);
                MiddleRow.this.setExpandRatio(this, 1.0f);
                MiddleRow.this.setComponentAlignment(this, Alignment.TOP_LEFT);
            }

            public boolean isEmpty() {
                return center == null && details == null;
            }

            public void setCenter(Component component) {
                if (center == component)
                    return;
                if (center != null)
                    this.removeComponent(center);
                if (component != null) {
                    this.center = component;
                    this.center.setWidth("100%");
                    if (this.details != null) {
                        this.center.setHeight("25%%");
                        this.details.setHeight("75%");
                    } else {
//                        this.center.setHeight("100%");
                    }
                    this.addComponent(this.center);
                    this.setComponentAlignment(this.center, Alignment.TOP_LEFT);
                } else if (this.details != null) {
                    this.details.setHeight("100%");
                }
            }

            public void setDetails(Component component) {
                if (details == component)
                    return;
                if (details != null)
                    this.removeComponent(details);
                if (component != null) {
                    this.details = component;
                    this.details.setWidth("100%");
                    if (this.center != null) {
                        this.details.setHeight("75%");
                        this.addComponent(this.details, 1);
                    } else {
                        this.details.setHeight("100%");
                        this.addComponentAsFirst(this.details);
                    }
                    this.setComponentAlignment(this.details, Alignment.BOTTOM_LEFT);
                } else if (this.center != null) {
                    this.center.setHeight("100%");
                }
            }
        }

        public void setWest(Component component) {
            if (west == component)
                return;
            if (west != null)
                this.removeComponent(west);
            if (component != null) {
                this.west = component;
                this.west.setHeight("100%");
                this.west.setWidth("100px");
                this.addComponentAsFirst(this.west);
                this.setComponentAlignment(this.west, Alignment.TOP_LEFT);
//                this.west.removeStyleName("top-layout-container-hidden");
            }
            updateMiddleHolder();
        }


        public void setEast(Component component) {
            if (east == component)
                return;
            if (east != null)
                this.removeComponent(east);
            if (component != null) {
                this.east = component;
                this.east.setWidth("150px");
                if (this.west == null && this.middleColumn.isEmpty())
                    this.addComponent(this.east);
                else if (this.west != null)
                    this.addComponent(this.east, 1);
                else
                    this.addComponentAsFirst(this.east);
                this.setComponentAlignment(this.east, Alignment.TOP_RIGHT);
            }
            this.updateMiddleHolder();
        }


        public void setCenter(Component component) {
            if (this.middleColumn == null)
                this.middleColumn = new MiddleRowMiddleColumn();
            this.middleColumn.setCenter(component);
            updateMiddleHolder();
        }

        private void updateMiddleHolder() {
            if (this.middleColumn == null)
                return;
            if (this.middleColumn.isEmpty() && this.getComponentIndex(this.middleColumn) != -1) {
                this.removeComponent(middleColumn);
                this.middleColumn = null;
            } else if (!this.middleColumn.isEmpty() && this.getComponentIndex(this.middleColumn) == -1) {
                if (this.west == null)
                    this.addComponentAsFirst(this.middleColumn);
                else if (this.east != null)
                    this.addComponent(this.middleColumn, 1);
                else
                    this.addComponent(this.middleColumn);
            }
        }


        public void setDetails(Component component) {
            if (this.middleColumn == null)
                this.middleColumn = new MiddleRowMiddleColumn();
            this.middleColumn.setDetails(component);
            updateMiddleHolder();
        }


        public boolean isEmpty() {
            return this.west == null && this.east == null && (this.middleColumn == null || this.middleColumn.isEmpty());
        }
    }

    public void setNorth(Component component) {
        if (north == component)
            return;
        if (north != null)
            this.removeComponent(north);
        if (component != null) {
            this.north = component;
            this.north.setHeight("50px");
            this.north.setWidth("100%");
            this.addComponentAsFirst(north);
            setComponentAlignment(this.north, Alignment.TOP_LEFT);
        }
    }

    public void setSouth(Component component) {
        if (south == component)
            return;
        if (south != null)
            this.removeComponent(south);
        if (component != null) {
            this.south = component;
            this.south.setHeight("25px");
            this.south.setWidth("100%");
            if (north == null && this.middleRow == null)
                this.addComponent(south);
            else if (north != null)
                this.addComponent(south, 1);
            else
                this.addComponentAsFirst(south);
            setComponentAlignment(this.south, Alignment.BOTTOM_LEFT);
        }
    }

    public void setEast(Component component) {
        if (this.middleRow == null){
            this.middleRow = new MiddleRow();
        }
        this.middleRow.setEast(component);
        if (this.middleRow.isEmpty()){
            this.removeComponent(this.middleRow);
            this.middleRow = null;
        }
    }

    public void setCenter(Component component) {
        if (this.middleRow == null){
            this.middleRow = new MiddleRow();
        }
        this.middleRow.setCenter(component);
        if (this.middleRow.isEmpty()){
            this.removeComponent(this.middleRow);
            this.middleRow = null;
        }
    }

    public void setDetails(Component component) {
        if (this.middleRow == null){
            this.middleRow = new MiddleRow();
        }
        this.middleRow.setDetails(component);
        if (this.middleRow.isEmpty()){
            this.removeComponent(this.middleRow);
            this.middleRow = null;
        }
    }

    public void setWest(Component component) {
        if (this.middleRow == null){
            this.middleRow = new MiddleRow();
        }
        this.middleRow.setWest(component);
        if (this.middleRow.isEmpty()){
            this.removeComponent(this.middleRow);
            this.middleRow = null;
        }
    }


    /**
     * sets up the parts of the layout
     */
    public void init() {
        buildLayout();
//        mainWindow.setContent(this);
    }

    public void buildLayout() {
        //TODO replace with CSS layout?
        this.setSizeFull();
        this.setMargin(true);
    }

    public AcceptsOneWidget getNorthAcceptsOneWidget() {
        return northAcceptsOneWidget;
    }

    public AcceptsOneWidget getWestAcceptsOneWidget() {
        return westAcceptsOneWidget;
    }

    public AcceptsOneWidget getCenterAcceptsOneWidget() {
        return centerAcceptsOneWidget;
    }

    public AcceptsOneWidget getEastAcceptsOneWidget() {
        return eastAcceptsOneWidget;
    }

    public AcceptsOneWidget getDetailsAcceptsOneWidget() {
        return detailsAcceptsOneWidget;
    }

    public AcceptsOneWidget getSouthAcceptsOneWidget() {
        return southAcceptsOneWidget;
    }
}
