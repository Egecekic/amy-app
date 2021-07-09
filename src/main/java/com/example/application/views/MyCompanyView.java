package com.example.application.views;

import com.example.application.Repositories.*;
import com.example.application.models.*;
import com.example.application.services.CategoryServices;
import com.example.application.services.ProductServices;
import com.example.application.services.SystemUserService;
import com.example.application.services.UserServices;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.*;
import java.util.List;

@Route
public class MyCompanyView extends VerticalLayout {
    private final SystemUserService systemUserService;
    private final SystemUserRepository systemUserRepository;
    private final UserServices userServices;
    private final CompanyRepositories companyRepositories;
    private final ProductServices productServices;
    private final ProductRepostories productRepostories;
    private final OrderRepositories orderRepositories;
    private final CategoryServices categoryServices;
    TextField txtFilter = new TextField();

    Long loggedInSystemUserId;
    VerticalLayout vLayout = new VerticalLayout();
    HorizontalLayout hLayout =new HorizontalLayout();
    Grid<Product> productGrid=new Grid<>(Product.class);
    Set<Product> productSet=new HashSet<>();
    Dialog dialogPerson=new Dialog();
    Long compId;

    FormLayout layoutWithFormItems = new FormLayout();

    public MyCompanyView(SystemUserService systemUserService, SystemUserRepository systemUserRepository, UserServices userServices, CompanyRepositories companyRepositories, ProductServices productServices, ProductRepostories productRepostories, OrderRepositories orderRepositories,  CategoryServices categoryServices) {
        this.systemUserService = systemUserService;
        this.systemUserRepository = systemUserRepository;
        this.userServices = userServices;
        this.companyRepositories = companyRepositories;
        this.productServices = productServices;
        this.productRepostories = productRepostories;
        this.orderRepositories = orderRepositories;
        this.categoryServices = categoryServices;

        ListBox<String> listBox = new ListBox<>();
        List<Category> categories=new ArrayList<>();
        categories.addAll(categoryServices.getList());
        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId")==null){
            UI.getCurrent().getPage().setLocation("/login");
        }else {
            loggedInSystemUserId=Long.valueOf(VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId").toString());
        }
        ComboBox<Category> comboBox = new ComboBox<>();
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        TextField firstName = new TextField();
        TextField firstName2 = new TextField();
        TextField firstName3 = new TextField();
        TextField firstName4 = new TextField();
        TextField firstName5 = new TextField();
        TextField firstName6 = new TextField();
        TextField firstName7 = new TextField();
        TextField firstName8 = new TextField();

        layoutWithFormItems.addFormItem(firstName, "Produc name");
        layoutWithFormItems.addFormItem(firstName2, "Produc Description");
        layoutWithFormItems.addFormItem(firstName3, "Category");
        layoutWithFormItems.addFormItem(firstName4, "Buyyer name");
        layoutWithFormItems.addFormItem(firstName5, "Buyyer Lastname");
        layoutWithFormItems.addFormItem(firstName6, "Buyyer E-mail");
        layoutWithFormItems.addFormItem(firstName7, "Alınan miktar");
        layoutWithFormItems.addFormItem(firstName8, "Alanın Sirketi");


        layoutWithFormItems.setVisible(false);

        Button btnadd=new Button("Add", VaadinIcon.INSERT.create());
        btnadd.addClickListener(buttonClickEvent -> dialogPerson.open());
        dialogPerson.setModal(true);

        TextField txtName = new TextField("Name","Enter your name");
        TextField txtDescription = new TextField("Description","Enter your Description");
        TextField txtCategoryName = new TextField("Category Name","Category adıyla listeden seçtiğiniz category aynı olamalı");
        TextField txtPrice=new TextField("Price","Enter your company name");
        TextField txtStockNumber=new TextField("Stock Number","Enter your Stock number");
        comboBox.setLabel("Category");
        comboBox.setItemLabelGenerator(Category::getName);
        comboBox.setItems(categories);


        FormLayout formLayout=new FormLayout();
        formLayout.add(txtName,txtDescription, txtCategoryName,txtPrice,txtStockNumber,comboBox);

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
        TextField companyName = new TextField("Company name");
        TextField companyJob = new TextField("Company job");
        vLayout.add(companyJob,companyName);

        for (var a:companyList
             ) {
            companyJob.setValue(a.getCompanyJob());
            companyName.setValue(a.getCompanyName());
            compId =a.getId();
        }

        Optional<Company> company=companyRepositories.findById(compId);
        Set<Product> productSet1=new HashSet<>();
        productSet1.addAll(productRepostories.findAll());



        Button bttnClose=new Button("Close");
        bttnClose.addClickListener(event -> dialogPerson.close());
        Button btnSave = new Button("Save");
        btnSave.addClickListener(buttonClickEvent -> {
            Category category=new Category(comboBox.getLabel());
            Product product=new Product(txtName.getValue(),Long.parseLong(txtPrice.getValue()),
                    txtDescription.getValue(),txtCategoryName.getValue(),Long.valueOf(txtStockNumber.getValue()));
            System.out.println(product);
            System.out.println(company.get());
            product.setCompanies(company.get());
            product.setCategory(category);

            productServices.save(product);
        });

        for (var item:productSet1
        ) {
            if (item.getCompanies().getId().equals(compId)){
                productSet.add(item);
            }
        }
        List<OrderHis> orderHis= new ArrayList<>();


        Button bttnOrder=new Button("Order Hist1ory");
        bttnOrder.addClickListener(event -> {
            productGrid.setVisible(false);
            layoutWithFormItems.setVisible(true);
            for (var a:orderRepositories.findAll()
            ) {
                if (a.getProduct().getCompanies().getId().equals(compId)){

                    firstName.setValue(a.getProduct().getName());
                    firstName2.setValue(a.getProduct().getDescription());
                    firstName3.setValue(a.getProduct().getCategoryName());
                    firstName4.setValue(a.getUser().getName());
                    firstName5.setValue(a.getUser().getLastName());
                    firstName6.setValue(a.getUser().getMail());
                    firstName7.setValue(String.valueOf(a.getAlinanMiktar()));
                    firstName8.setValue(a.getUser().getCompanyName());
                }
            }
        });
        Button bttnOrder1=new Button("Products");
        bttnOrder1.addClickListener(event -> {
            productGrid.setVisible(true);
            layoutWithFormItems.setVisible(false);
        });

        productGrid.removeColumnByKey("category");
        productGrid.removeColumnByKey("companies");
        productGrid.removeColumnByKey("stock");
        productGrid.removeColumnByKey("id");
        productGrid.removeColumnByKey("categoryName");
        productGrid.setItems(productSet);

        horizontalLayout.add(btnSave,bttnClose);
        hLayout.add(productGrid);

        dialogPerson.add(formLayout,horizontalLayout);
        add(vLayout, productGrid,layoutWithFormItems,btnadd,bttnOrder,bttnOrder1);
    }
    private void refreshData(String filter){
        Set<Product> productSet=new HashSet<>();
        productSet.addAll(productServices.getList());
        productGrid.setItems(productSet);
    }
}
