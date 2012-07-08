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
package sweforce.vaadin.security.login;

import com.vaadin.ui.*;
import sweforce.gui.ap.vaadin.IsVaadinWidget;


/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 2/23/12
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginViewImpl implements LoginView, IsVaadinWidget, LoginForm.LoginListener {

    private Presenter presenter;

    private final VerticalLayout mainLayout = new VerticalLayout();

    private final Panel loginPanel;

    public LoginViewImpl() {
        mainLayout.setSizeFull();
        loginPanel = new Panel("Login");
        mainLayout.addComponent(loginPanel);
        mainLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        loginPanel.setSizeUndefined();

        LoginForm loginForm;
		loginForm = new LoginForm();
        loginPanel.addComponent(loginForm);
		loginForm.setPasswordCaption("Password"); //i18n?
		loginForm.setUsernameCaption("User");
		loginForm.setLoginButtonCaption("Login");
//        loginForm.setHeight("100px");
        loginForm.addListener(this);
    }

    @Override
    public void onLogin(LoginForm.LoginEvent loginEvent) {
        this.presenter.login(loginEvent.getLoginParameter("username"), loginEvent.getLoginParameter("password"));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Component asWidget() {
        return mainLayout;
    }

}
