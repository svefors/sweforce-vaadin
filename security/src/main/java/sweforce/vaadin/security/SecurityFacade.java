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
package sweforce.vaadin.security;


import sweforce.gui.ap.place.Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/31/12
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SecurityFacade {

    public Subject getSubject() ;

    public boolean isAuthenticationRequired(Place place);


    public class UnknownAccountException extends RuntimeException {
        public UnknownAccountException() {
        }

        public UnknownAccountException(String s) {
            super(s);
        }

        public UnknownAccountException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public UnknownAccountException(Throwable throwable) {
            super(throwable);
        }
    }

    public class IncorrectCredentialsException extends RuntimeException {
        public IncorrectCredentialsException(String s) {
            super(s);
        }

        public IncorrectCredentialsException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public IncorrectCredentialsException(Throwable throwable) {
            super(throwable);
        }
    }

    public class LockedAccountException extends RuntimeException {
        public LockedAccountException() {
        }

        public LockedAccountException(String s) {
            super(s);
        }

        public LockedAccountException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public LockedAccountException(Throwable throwable) {
            super(throwable);
        }
    }

    public class ExcessiveAttemptsException extends RuntimeException {
        public ExcessiveAttemptsException() {
        }

        public ExcessiveAttemptsException(String s) {
            super(s);
        }

        public ExcessiveAttemptsException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public ExcessiveAttemptsException(Throwable throwable) {
            super(throwable);
        }
    }

    public class AuthenticationException extends RuntimeException {
        public AuthenticationException() {
        }

        public AuthenticationException(String s) {
            super(s);
        }

        public AuthenticationException(String s, Throwable throwable) {
            super(s, throwable);
        }

        public AuthenticationException(Throwable throwable) {
            super(throwable);
        }
    }
}
