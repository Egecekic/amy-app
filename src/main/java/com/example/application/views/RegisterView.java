package com.example.application.views;

import com.example.application.services.SystemUserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route
public class RegisterView extends Composite {

    private final SystemUserService systemUserService;

    public RegisterView(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    protected Component initContent(){
        TextField username = new TextField("Username");
        TextField companyName= new TextField("companyName");
        TextField companyJop = new TextField("companyJop");
        EmailField emailField = new EmailField("Email");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm password");
        emailField.setClearButtonVisible(true);


        return new VerticalLayout(
                new H2("Register"),
                username,
                password1,
                password2,
                emailField,
                companyName,
                companyJop,
                new Button("Send", event -> register(
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue(),
                        emailField.getValue(),
                        companyName.getValue(),
                        companyJop.getValue()
                ))
        );
    }
    private void register(String email,String username,String password1,String password2,String companyName,String companyJop){
        if (username.trim().isEmpty()) {
            Notification.show("Enter a username");
        } else if (password1.isEmpty()) {
            Notification.show("Enter a password");
        } else if (password1.equals(password2)) {
            Notification.show("Passwords don't match");
        } else if (email.trim().isEmpty()){
            Notification.show("Enter a email");
        }else if (companyName.trim().isEmpty()){
            Notification.show("Enter a companyName");
        }else if (companyJop.trim().isEmpty()){
            Notification.show("Enter a companyJop");
        }
        else {
            systemUserService.register(email, password1,username,"",companyName,companyJop);
            Notification.show("Check your email.");
        }

    }
}
