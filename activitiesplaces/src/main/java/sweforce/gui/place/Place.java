/*
 * Copyright 2010 Google Inc.
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
package sweforce.gui.place;

/**
 * Represents a location in an app. Implementations are expected to
 * provide correct {@link Object#equals(Object)} and {@link Object#hashCode()}
 * methods.
 */
public abstract class Place {

    /**
     * The null place.
     */
    public static final Place NOWHERE = new Place(){};

//    public static class NOWHERE extends Place {
//        private static final NOWHERE instance = new NOWHERE();
//
//        private NOWHERE() {
//        }
//
//        private static NOWHERE getInstance() {
//            return instance;
//        }
//    }
}
