package com.app.ce216_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    ArrayList<Type> typeList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<TreeItem<Object>> typeNodes = new ArrayList<>();
    ArrayList<TreeItem<Object>> itemNodes = new ArrayList<>();


    public TextField attributeNameInput;
    public TextField attributeValueInput;

    public TextArea attributeDescriptionInput;

    public TextArea editTypeNameInput;
    public TextArea editItemNameInput;

    public TextArea editTypeAttributeNameInput;
    public TextArea editTypeAttributeValueInput;
    public TextArea editItemAttributeInput;

    public Button addAttributeButton;
    public Button finishAttributeWindowButton;

    private Type lastCreatedType;

    @FXML
    private TreeView<Object> tree;

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
    private Pane mainAdd;

    @FXML
    private Pane itemCreateWindow;

    @FXML
    private Pane addAttributeWindow;

    @FXML
    private Pane deletePane;

    private Stage currentStage;
    private Parent currentRoot;
    private Scene currentScene;

    @FXML
    private ChoiceBox typeChoice;
    @FXML
    private ComboBox typeChoice1C;
    @FXML
    private ChoiceBox typeChoice2;

    @FXML
    private ChoiceBox editItemChoiceBox;

    @FXML
    private ChoiceBox deleteTypeChoiceBox;

    @FXML
    private ChoiceBox deleteItemChoiceBox;

    @FXML
    private TextArea textArea;

    int counter = 0;

    @FXML
    private Pane editPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<Object> treeRoot = new TreeItem<>("Types");
        System.out.println(Catalog.getCatalogInstance().getTypes().size());
        tree.setRoot(treeRoot);



        typeChoice1C.setValue("Types");
        typeChoice1C.getItems().addAll(typeList);


        deleteTypeChoiceBox.setValue("Types");
        deleteItemChoiceBox.setValue("Items");

    }

    public void choiceBoxRefresh() {
        for (Type type : typeList) {
            typeChoice.getItems().addAll(type);
        }
    }

    public void choice1BoxRefresh() {
        for (Type type : typeList) {
            typeChoice1C.getItems().addAll(type);
        }
    }


    public void ComboBoxRefresh() {
        for (Type type : typeList) {
            typeChoice1C.getItems().addAll(type);
        }
    }


    public void deleteTypeChoiceBoxRefresh() {
        for (Type type : typeList) {
            deleteTypeChoiceBox.getItems().add(type);
        }
    }

    public void deleteItemChoiceBoxRefresh() {
        for (Item type : itemList) {
            deleteItemChoiceBox.getItems().add(type);
        }
    }


    public void openDeletePane() {
        deleteTypeChoiceBoxRefresh();
        deleteItemChoiceBoxRefresh();
        deletePane.setVisible(true);
    }

    public void closeDeletePane() {
        deletePane.setVisible(false);
    }

    public void openEditPane(ActionEvent event) throws IOException {
        choice1BoxRefresh();
        editPane.setVisible(true);
    }

    public void closeEditPane(ActionEvent event) throws IOException {
        editPane.setVisible(false);
    }

    public void closeAddButton(ActionEvent event) throws IOException {
        mainAdd.setVisible(false);
    }

    public void addButtonAction(ActionEvent event) throws IOException {
        mainAdd.setVisible(true);
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
        mainAdd.setVisible(false);

        //tree.getRoot().getChildren().add(new TreeItem<>(Catalog.getCatalogInstance().createType(new Type("Book")).getName()));


    }


    public void newItemButtonAction(ActionEvent event) throws IOException {
        itemCreateWindow.setVisible(true);
        mainAdd.setVisible(false);
    }

    public void createTypeButtonAction(ActionEvent event) {

        Type t = Catalog.getCatalogInstance().createType(new Type(typeNameInput.getText()));
        Catalog.getCatalogInstance().types.add(t);
        lastCreatedType = t;
        TreeItem<Object> treeItem = new TreeItem<>(t);
        typeNodes.add(treeItem);
        tree.getRoot().getChildren().add(treeItem);
        System.out.println(tree.getRoot().getChildren().size());

        //createWindow.setVisible(false);
        addAttributeWindow.setVisible(true);
        typeList.add(t);
        choiceBoxRefresh();
        ComboBoxRefresh();
        createWindow.setVisible(false);

    }

    @FXML
    public void createItemButtonAction(ActionEvent event) throws IOException {
        Item item = Catalog.getCatalogInstance().createItem(new Item(itemNameInput.getText(), (Type) typeChoice.getValue()));
        Catalog.getCatalogInstance().items.add(item);
        item.getType().getItems().add(item);
        TreeItem<Object> treeItem = new TreeItem<>(item);
        itemNodes.add(treeItem);
        mainAdd.setVisible(false);
        itemList.add(item);

        for (int i = 0; i < typeNodes.size(); i++) {
            if (Objects.equals(typeNodes.get(i).getValue(), item.getType())) {
                typeNodes.get(i).getChildren().add(treeItem);
                break;
            }
        }

        //tree.getRoot().getChildren().add(treeItem);
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

    public void addAttributeButtonAction(ActionEvent event) {
        lastCreatedType.addAttribute(attributeNameInput.getText(), null, null);
        attributeNameInput.setText("");
    }

    public void finishAddingAttributeButtonAction(ActionEvent event) {
        addAttributeWindow.setVisible(false);
        createWindow.setVisible(false);
        System.out.print(lastCreatedType.getName() + " Attributes : \n");

        for (int i = 0; i < lastCreatedType.getDefaultAttributes().size(); i++) {
            System.out.println("* " + lastCreatedType.getDefaultAttributes().get(i).getName());
        }
    }

    public void mouseClick(MouseEvent event) {

        TreeItem<Object> item = (TreeItem<Object>) tree.getSelectionModel().getSelectedItem();

        System.out.println("Item is null : " + (item == null));

        counter++;


        if (item != tree.getRoot()) {

            System.out.println(item);

            if (item != null) {

                decider(item);

            }

        }

    }

    public void decider(TreeItem<Object> item) {
        if (item.getValue().getClass().getName().equals("com.app.ce216_project.Item")) {
            textSetter((Item) item.getValue());
        } else if (item.getValue().getClass().getName().equals("com.app.ce216_project.Type")) {
            textSetter((Type) item.getValue());
        }
    }

    public void textSetter(Type type) {
        //textArea.setText("Name of the type: "+type.getName()+"\nItems that belongs to this type: "+ type.getItems().toString() + "\nDefault attributes are: "+ type.getDefaultAttributes().toString());
        textArea.setText(type.showInformation());
    }

    public void textSetter(Item item) {
        //textArea.setText("Name of the item: "+item.getName()+"\nThe type that this item belongs to: "+item.getType().toString()+"\nThe tags of this item: "+item.getTags().toString());
        textArea.setText(item.showInformation());

    }

    public void editTypeName(ActionEvent event) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getName().equals(typeChoice1C.getValue().toString())) {
                typeList.get(i).setName(editTypeNameInput.getText());
            }
        }
    }

    public void EditTypeAttribute(ActionEvent event) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getName().equals(typeChoice1C.getValue().toString())) {
                typeList.get(i).addAttribute(editTypeAttributeNameInput.getText(), editTypeAttributeValueInput.getText(), "");
            }
        }
    }

    public void deleteType(ActionEvent event) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getName().equals(deleteTypeChoiceBox.getValue().toString())) {
                typeList.remove(i);
            }
        }
    }

    public void deleteItem(ActionEvent event) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getName().equals(deleteItemChoiceBox.getValue().toString())) {
                itemList.remove(i);
            }
        }
        tree.refresh();
    }



    public void removeAttribute(ActionEvent event) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getName().equals(typeChoice1C.getValue().toString())) {
               typeChoice2.getItems().setAll(typeList.get(i).getDefaultAttributes().toString());
            }
        }

    }
}
