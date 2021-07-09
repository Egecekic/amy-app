package com.example.application.views;

import com.example.application.models.Category;
import com.example.application.models.Product;
import com.example.application.services.CategoryServices;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route
public class CategoryView extends VerticalLayout {
    private final CategoryServices categoryServices;
    Grid<Category> categoryGrid=new Grid<>(Category.class);
    TextField txtFilter = new TextField();
    Long loggedInSystemUserId;
    public CategoryView(CategoryServices categoryServices){
        categoryGrid.removeColumnByKey("products");
        this.categoryServices = categoryServices;
        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId")==null){
            UI.getCurrent().getPage().setLocation("/login");
        }else {
            System.out.println("Logedin User ID");
            System.out.println(VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId").toString());
            loggedInSystemUserId=Long.valueOf(VaadinSession.getCurrent().getSession().getAttribute("LoggedInSystemUserId").toString());
        }
        List<Category> categories = new ArrayList<>(categoryServices.getList());
        categoryGrid.setItems(categories);
        TextField firstName = new TextField("Category Name");

        Button bttnsave=new Button("Save");
        bttnsave.addClickListener(event -> {
            Category category=new Category(firstName.getValue());
            categoryServices.save(category);
            refreshData(txtFilter.getValue().toString());
        });
        add(firstName,bttnsave,categoryGrid);

    }
    private void refreshData(String filter){
        Set<Category> categories=new HashSet<>();
        categories.addAll(categoryServices.getList());
        categoryGrid.setItems(categories);
    }

}
