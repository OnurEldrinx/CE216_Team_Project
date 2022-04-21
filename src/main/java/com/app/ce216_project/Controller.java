package com.app.ce216_project;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    ObservableList<Type> typeList2 = FXCollections.observableArrayList();
    ArrayList<Type> typeList = new ArrayList<>();



    public TextField attributeNameInput;
    public TextField attributeValueInput;

    public TextArea attributeDescriptionInput;

    public Button addAttributeButton;
    public Button finishAttributeWindowButton;

    private Type lastCreatedType;
    private Item lastCreatedItem;

    @FXML
    private TreeView<String> tree;

    @FXML
    Button newTypeButton;
    @FXML
    Button createTypeButton;
    @FXML
    TextField typeNameInput;

    @FXML
    private TextField itemNameInput;

    @FXML
    private Pane createWindow;

    @FXML
    private Pane itemCreateWindow;

    @FXML
    private Pane addAttributeWindow;

    private Stage currentStage;
    private Parent currentRoot;
    private Scene currentScene;

    @FXML
    private ChoiceBox typeChoice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> treeRoot = new TreeItem<>("Types");
        System.out.println(Catalog.getCatalogInstance().getTypes().size());
        tree.setRoot(treeRoot);


        typeChoice.setValue("Types");
        typeChoice.getItems().addAll(typeList);

    }

    public void choiceBoxRefresh(){
        typeChoice.getItems().addAll(typeList);
    }




    public void newTypeButtonAction(ActionEvent event) throws IOException {

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddTypeScene.fxml"));
        currentStage = new Stage();
        currentRoot = fxmlLoader.load();
        currentScene = new Scene(currentRoot);
        currentStage.setScene(currentScene);
        currentStage.setTitle("Create New Type");
        currentStage.show();*/

        createWindow.setVisible(true);

        //tree.getRoot().getChildren().add(new TreeItem<>(Catalog.getCatalogInstance().createType(new Type("Book")).getName()));


    }

    public void newItemButtonAction(ActionEvent event) throws IOException{
        itemCreateWindow.setVisible(true);
    }

    public void createTypeButtonAction(ActionEvent event){

        Type t = Catalog.getCatalogInstance().createType(new Type(typeNameInput.getText()));
        Catalog.getCatalogInstance().types.add(t);
        lastCreatedType = t;
        TreeItem<String> treeItem = new TreeItem<>(t.getName());
        tree.getRoot().getChildren().add(treeItem);
        System.out.println(tree.getRoot().getChildren().size());

        //createWindow.setVisible(false);
        addAttributeWindow.setVisible(true);
        typeList.add(t);
        choiceBoxRefresh();

    }

    @FXML
    public void createItemButtonAction(ActionEvent event) throws IOException{
        Item item = Catalog.getCatalogInstance().createItem(new Item(itemNameInput.getText(), (Type) typeChoice.getValue()));
        Catalog.getCatalogInstance().items.add(item);
        lastCreatedItem = item;
        TreeItem<String> treeItem = new TreeItem<>(item.getName());
        tree.getRoot().getChildren().add(treeItem);
        System.out.println(tree.getRoot().getChildren().size());
        //createWindow.setVisible(false);
        itemCreateWindow.setVisible(false);
    }



    public void createTypeCancelButtonAction(ActionEvent event) {

        createWindow.setVisible(false);

    }

    public void createItemCancelButtonAction(ActionEvent event) {

        itemCreateWindow.setVisible(false);

    }


    public void addAttributeButtonAction(ActionEvent event){

        lastCreatedType.addAttribute(attributeNameInput.getText(),null,null);
        attributeNameInput.setText("");
    }

    public void finishAddingAttributeButtonAction(ActionEvent event){

        addAttributeWindow.setVisible(false);
        createWindow.setVisible(false);
        System.out.print(lastCreatedType.getName()  + " Attributes : \n");
        for (int i=0;i<lastCreatedType.getDefaultAttributes().size();i++){

            System.out.println("* " + lastCreatedType.getDefaultAttributes().get(i).getName());

        }

    }



}
