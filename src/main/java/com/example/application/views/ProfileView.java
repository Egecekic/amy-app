package com.example.application.views;

import com.example.application.Repositories.CompanyRepositories;
import com.example.application.Repositories.OrderRepositories;
import com.example.application.models.Company;
import com.example.application.models.OrderHis;
import com.example.application.models.SystemUser;
import com.example.application.models.User;
import com.example.application.services.SystemUserService;
import com.example.application.services.UserServices;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

@Route
public class ProfileView extends VerticalLayout {

    Long loggedInSystemUserId;
    private final SystemUserService systemUserService;
    private final UserServices userServices;
    private final CompanyRepositories companyRepositories;
    private final OrderRepositories orderRepositories;
    VerticalLayout vLayout = new VerticalLayout();
    VerticalLayout vLayout1 = new VerticalLayout();
    FormLayout layoutWithFormItems = new FormLayout();

    public ProfileView(SystemUserService systemUserService, UserServices userServices, CompanyRepositories companyRepositories, OrderRepositories orderRepositories){

        this.systemUserService = systemUserService;
        this.userServices = userServices;
        this.companyRepositories = companyRepositories;
        this.orderRepositories = orderRepositories;
        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId")==null){
            UI.getCurrent().getPage().setLocation("/login");
        }else {
            loggedInSystemUserId=Long.valueOf(VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId").toString());
        }

        TextField firstName = new TextField();
        TextField firstName2 = new TextField();
        TextField firstName3 = new TextField();
        TextField firstName4 = new TextField();
        TextField firstName5 = new TextField();
        TextField firstName6 = new TextField();
        TextField firstName7 = new TextField();

        layoutWithFormItems.addFormItem(firstName, "Produc name");
        layoutWithFormItems.addFormItem(firstName2, "Produc Description");
        layoutWithFormItems.addFormItem(firstName3, "First Category");
        layoutWithFormItems.addFormItem(firstName4, "Buyyer name");
        layoutWithFormItems.addFormItem(firstName5, "Buyyer Lastname");
        layoutWithFormItems.addFormItem(firstName6, "Buyyer E-mail");
        layoutWithFormItems.addFormItem(firstName7, "Alınan miktar");

        layoutWithFormItems.setVisible(false);

        Tab tabUser=new Tab("User");
        Tab tabCompany=new Tab("Company");
        Tabs tabs=new Tabs(tabUser,tabCompany);

        List<SystemUser> systemUserList=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        List<Company> companyList=new ArrayList<>();
        systemUserList.addAll(systemUserService.findById(loggedInSystemUserId));

        for (var a:systemUserList
             ) {
            userList.addAll(userServices.getNMail(a.getEmail()));
        }
        for (var a:userList
             ) {
            companyList.addAll(companyRepositories.findByCompanyName(a.getCompanyName()));
        }
        Button bttnSave=new Button("Save");
        bttnSave.addClickListener(buttonClickEvent -> {

        });
        Text newText = new Text("");
        Text oldText = new Text("");
        Button bttnHistory=new Button("Order history");

        Button bttmCSave =new Button("Değisikleri kaydet");
        TextField companyName = new TextField("Company name");
        TextField companyJob = new TextField("Company job");

        TextField Name = new TextField("Name");
        TextField lastName = new TextField("Lastname");
        TextField mail = new TextField("E-mail");

        vLayout.add(Name,lastName,mail,bttnSave,bttnHistory);
        vLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        vLayout.setVisible(true);
        vLayout1.add(companyName,companyJob, bttmCSave);
        vLayout1.setVisible(false);
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.add(tabs,vLayout,vLayout1);
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.add(verticalLayout,layoutWithFormItems);
        bttnHistory.addClickListener(event -> {
            for (var a:orderRepositories.findAll()
            ) {
                firstName.setValue(a.getProduct().getName());
                firstName2.setValue(a.getProduct().getDescription());
                firstName3.setValue(a.getProduct().getCategoryName());
                firstName4.setValue(a.getUser().getName());
                firstName5.setValue(a.getUser().getLastName());
                firstName6.setValue(a.getUser().getMail());
                firstName7.setValue(String.valueOf(a.getAlinanMiktar()));
            }
            layoutWithFormItems.setVisible(true);
        });
        add(horizontalLayout);


        for (var a:userList
             ) {
            Name.setValue(a.getName());
            lastName.setValue(a.getLastName());
            mail.setValue(a.getMail());
        }
        for (var a:companyList
             ) {
            companyName.setValue(a.getCompanyName());
            companyJob.setValue(a.getCompanyJob());
        }

        tabs.addSelectedChangeListener(selectedChangeEvent -> {
            if (selectedChangeEvent.getSelectedTab().getLabel().toString().equals("Company")){
                vLayout.setVisible(false);
                vLayout1.setVisible(true);
            }
            else {
                vLayout.setVisible(true);
                vLayout1.setVisible(false);
            }
        });
    }
}
