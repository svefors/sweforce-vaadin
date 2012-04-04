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


import sweforce.gui.ap.activity.Activity;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.gui.ap.place.Place;
import sweforce.vaadin.layout.places.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/17/12
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractLayoutPlaceActivityMapper<P extends LayoutPlace> implements ActivityMapper {

    private final Class<P> clazz;

    protected AbstractLayoutPlaceActivityMapper(Class<P> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place != null && clazz.isAssignableFrom(place.getClass())) {
            return getInternalActivity((P) place);
        }
        return null;
    }

    /**
     * Override this method if you wish to add activities
     *
     * @param place
     * @return
     */
    protected Activity getInternalActivity(P place){ return null;}

    public abstract static class North extends AbstractLayoutPlaceActivityMapper<NorthPlace> {
        protected North() {
            super(NorthPlace.class);
        }
    }

    public abstract static class West extends AbstractLayoutPlaceActivityMapper<WestPlace> {
        protected West() {
            super(WestPlace.class);
        }
    }

    public abstract static class Center extends AbstractLayoutPlaceActivityMapper<CenterPlace> {
        protected Center() {
            super(CenterPlace.class);
        }
    }

    public abstract static class East extends AbstractLayoutPlaceActivityMapper<EastPlace> {
        protected East() {
            super(EastPlace.class);
        }
    }

    public abstract static class Main extends AbstractLayoutPlaceActivityMapper<MainPlace> {
        protected Main() {
            super(MainPlace.class);
        }
    }

    public abstract static class South extends AbstractLayoutPlaceActivityMapper<SouthPlace> {
        protected South() {
            super(SouthPlace.class);
        }
    }



}
