package com.app.ce216_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {


    public Pane editTypePane;
    public ChoiceBox editTypeChoiceBox;
    public Pane editItemPane;
    public TextField editTypeNameInput;
    public TextField addTypeAttributeNameInput;
    public ChoiceBox defaultTypeAttributesChoiceBox;
    public ChoiceBox itemTagsChoiceBox;
    public TextField tagInput;
    public TextField newAttributeValueOnEdit;
    public TextField newItemAttributeNameOnEdit;
    public TextField editItemAttributeValueInput;
    public ChoiceBox defaultItemAttributesChoiceBox;
    public TextField editItemNameInput;
    public ChoiceBox itemAttributesChoiceBox;
    @FXML
    private ChoiceBox<String> typeOrItemChoiceBox;

    ArrayList<Type> typeList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Tag> tagList = new ArrayList<>();

    ArrayList<TreeItem<Object>> typeNodes = new ArrayList<>();
    ArrayList<TreeItem<Object>> itemNodes = new ArrayList<>();

    private Type editTargetType;

    private Item editTargetItem;

    public TextField attributeNameInput;
    public TextField attributeValueInput;
    public TextArea attributeDescriptionInput;


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

    @FXML
    private ChoiceBox typeChoice;

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

    @FXML
    private TextField searchBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(Catalog.getCatalogInstance().getTypes().size());
        treeMaker();

        typeChoice.setValue("Types");
        typeChoice.getItems().addAll(typeList);


        typeOrItemChoiceBox.setValue("Edit Type/Item");
        typeOrItemChoiceBox.getItems().add("Type");
        typeOrItemChoiceBox.getItems().add("Item");

        defaultTypeAttributesChoiceBox.setValue("Default Attributes");

        deleteTypeChoiceBox.setValue("Types");
        deleteItemChoiceBox.setValue("Items");

    }

    public void treeMaker() {
        TreeItem<Object> treeRoot = new TreeItem<>("Types");
        tree.setRoot(treeRoot);
        tree.setShowRoot(true);
        for (Type type : typeList) {
            TreeItem treeTypeItem = new TreeItem<>(type);
            treeRoot.getChildren().add(treeTypeItem);
            treeTypeItem.setExpanded(true);
            for (Item item : type.getItems()) {
                treeTypeItem.getChildren().add(new TreeItem<>(item));
            }
        }
    }

    private TreeItem root = new TreeItem();

    public void treeMaker2(String s) {

        tree.setRoot(root);
        tree.setShowRoot(false);
        root.getChildren().clear();
        for (Type type : typeList) {
            if (type.getName().contains(s)) {
                TreeItem treeTypeItem = new TreeItem<>(type);
                root.getChildren().add(treeTypeItem);
            }
            for (Item item : type.getItems()) {
                if (item.getName().contains(s)) {
                    TreeItem treeTypeItem = new TreeItem<>(item);
                    root.getChildren().add(treeTypeItem);
                }
            }
            for(Tag tag : tagList ){
                if(tag.getName().contains(s)){
                    TreeItem treeTypeItem = new TreeItem<>(tag);
                    root.getChildren().add(treeTypeItem);
                }
            }
        }
    }

    public void addToTypeChoiceBoxes(Type t) {

        typeChoice.getItems().add(t);
        editTypeChoiceBox.getItems().add(t);

    }

    public void removeFromTypeChoiceBoxes(Type t) {

        typeChoice.getItems().remove(t);
        editTypeChoiceBox.getItems().remove(t);

    }

    public void addToItemChoiceBoxes(Item i) {

        editItemChoiceBox.getItems().add(i);

    }

    public void removeFromItemChoiceBoxes(Item i) {

        editItemChoiceBox.getItems().remove(i);

    }


    public void deleteTypeChoiceBoxRefresh() {
        deleteTypeChoiceBox.getItems().clear();
        deleteTypeChoiceBox.getItems().addAll(typeList);
    }

    public void deleteItemChoiceBoxRefresh() {
        deleteItemChoiceBox.getItems().clear();
        deleteItemChoiceBox.getItems().addAll(itemList);
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

        createWindow.setVisible(true);
        mainAdd.setVisible(false);


    }


    public void newItemButtonAction(ActionEvent event) throws IOException {
        itemCreateWindow.setVisible(true);
        mainAdd.setVisible(false);
    }

    public void createTypeButtonAction(ActionEvent event) {
        boolean checker = false;

        if (typeList.size() != 0) {

            for (Type type : typeList) {
                if (typeNameInput.getText().equals(type.getName())) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("A type with the same name is already defined.");
                    a.show();
                    break;
                } else
                    checker = true;
            }
        }
        if (typeList.size() == 0 || checker == true) {
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
            addToTypeChoiceBoxes(t);
            createWindow.setVisible(false);
        }

    }


    @FXML
    public void createItemButtonAction(ActionEvent event) throws IOException {
        boolean checker = false;
        if (itemList.size() != 0) {
            for (Item type : itemList) {
                if (itemNameInput.getText().equals(type.getName())) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("An item with the same name is already defined.");
                    a.show();
                    break;
                } else
                    checker = true;
            }
        }
        if (itemList.size() == 0 || checker == true) {
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
            addToItemChoiceBoxes(item);
            itemCreateWindow.setVisible(false);
        }
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

        TreeItem<Object> item = tree.getSelectionModel().getSelectedItem();

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


    public void chooseEditable(ActionEvent event) {

        if (typeOrItemChoiceBox.getValue().equals("Type")) {

            editItemPane.setVisible(false);
            editTypePane.setVisible(true);

        } else {

            editTypePane.setVisible(false);
            editItemPane.setVisible(true);
        }
    }

    public Type chooseATypeToEdit() {

        editTargetType = (Type) editTypeChoiceBox.getValue();
        defaultTypeAttributesChoiceBox.getItems().clear();
        defaultTypeAttributesChoiceBox.getItems().addAll(editTargetType.getDefaultAttributes());

        return editTargetType;
    }

    public Item chooseAItemToEdit() {

        editTargetItem = (Item) editItemChoiceBox.getValue();
        defaultItemAttributesChoiceBox.getItems().clear();
        defaultItemAttributesChoiceBox.getItems().addAll(editTargetItem.getType().getDefaultAttributes());

        return editTargetItem;

    }


    public void editTypeName() {

        editTargetType = chooseATypeToEdit();

        for (Type type : typeList) {

            if (type.getName().equals(editTargetType.getName())) {

                type.setName(editTypeNameInput.getText());
                typeNodes.get(typeList.indexOf(type)).setValue(type);
                tree.refresh();
                break;

            }

        }

    }

    public void editItemName() {

        editTargetItem = chooseAItemToEdit();

        for (Item item : itemList) {

            if (item.getName().equals(editTargetItem.getName())) {

                item.setName(editItemNameInput.getText());
                itemNodes.get(itemList.indexOf(item)).setValue(item);
                tree.refresh();
                break;

            }

        }


    }

    public void editDefaultItemAttribute() {

        for (Attribute attribute : editTargetItem.getType().getDefaultAttributes()) {

            if (attribute.getName().equals(defaultItemAttributesChoiceBox.getValue().toString())) {

                attribute.setValue(editItemAttributeValueInput.getText());
                System.out.println(attribute.getName() + "  " + attribute.getValue());
                break;

            }

        }

    }

    public void addNewItemAttribute() {

        editTargetItem.getType().getDefaultAttributes().add(new Attribute(newItemAttributeNameOnEdit.getText(), newAttributeValueOnEdit.getText()));
        defaultItemAttributesChoiceBox.getItems().clear();
        defaultItemAttributesChoiceBox.getItems().addAll(editTargetItem.getType().getDefaultAttributes());

        itemAttributesChoiceBox.getItems().clear();
        itemAttributesChoiceBox.getItems().addAll(editTargetItem.getType().getDefaultAttributes());
    }


    public void addTypeAttributeOnEdit() {

        editTargetType.getDefaultAttributes().add(new Attribute(addTypeAttributeNameInput.getText()));
        defaultTypeAttributesChoiceBox.getItems().clear();
        defaultTypeAttributesChoiceBox.getItems().addAll(editTargetType.getDefaultAttributes());

    }

    public void removeFromTypeDefaultAttributes() {

        editTargetType.getDefaultAttributes().remove((Attribute) defaultTypeAttributesChoiceBox.getValue());
        defaultTypeAttributesChoiceBox.getItems().clear();
        defaultTypeAttributesChoiceBox.getItems().addAll(editTargetType.getDefaultAttributes());

    }

    public void removeFromItemAttributes() {

        editTargetItem.getType().getDefaultAttributes().remove((Attribute) itemAttributesChoiceBox.getValue());

        defaultItemAttributesChoiceBox.getItems().clear();
        defaultItemAttributesChoiceBox.getItems().addAll(editTargetItem.getType().getDefaultAttributes());

        itemAttributesChoiceBox.getItems().clear();
        itemAttributesChoiceBox.getItems().addAll(editTargetItem.getType().getDefaultAttributes());

    }

    public void addItemTag() {

        editTargetItem = chooseAItemToEdit();

        editTargetItem.addTag((tagInput.getText()));
        itemTagsChoiceBox.getItems().clear();
        itemTagsChoiceBox.getItems().addAll(editTargetItem.getTags());
        tagList.add(new Tag(tagInput.getText()));

    }

    public void deleteTag() {

        editTargetItem = chooseAItemToEdit();

        editTargetItem.removeTag(itemTagsChoiceBox.getValue());
        itemTagsChoiceBox.getItems().clear();
        itemTagsChoiceBox.getItems().addAll(editTargetItem.getTags());
        tagList.remove(itemTagsChoiceBox.getValue());
    }


    public void closeEditTypePane() {

        editTypePane.setVisible(false);

    }

    public void closeEditItemPane() {

        editItemPane.setVisible(false);

    }


    public void deleteType(ActionEvent event) {

        TreeItem c = null;

        for (int i = 0; i < typeNodes.size(); i++) {
            if (typeNodes.get(i).getValue().toString().equals(deleteTypeChoiceBox.getValue().toString())) {

                c = typeNodes.get(i);
                break;
            }
        }

        for (int k = 0; k < typeList.size(); k++) {

            if (typeList.get(k).getName().equals(c.getValue().toString())) {

                removeFromTypeChoiceBoxes(typeList.get(k));
                deleteItemChoiceBox.getItems().removeAll(typeList.get(k).getItems());
                editItemChoiceBox.getItems().removeAll(typeList.get(k).getItems());
                typeList.remove(k);
                break;
            }

        }

        for (int k = 0; k < c.getChildren().size(); k++) {

            for (int l = 0; l < itemList.size(); l++) {


                if (itemList.get(l).getName().equals(c.getChildren().get(k).toString())) {

                    removeFromItemChoiceBoxes(itemList.get(l));
                    itemList.remove(l);
                    itemNodes.remove(l);

                }

            }

        }


        c.getChildren().clear();

        c.getParent().getChildren().remove(c);

        typeNodes.remove(c);

        typeChoice.getItems().clear();
        typeChoice.getItems().addAll(typeList);


        deleteTypeChoiceBoxRefresh();

        tree.refresh();
    }

    public void deleteItem(ActionEvent event) {

        TreeItem c = null;

        for (int i = 0; i < itemNodes.size(); i++) {
            if (itemNodes.get(i).getValue().toString().equals(deleteItemChoiceBox.getValue().toString())) {

                c = itemNodes.get(i);
                break;
            }
        }

        for (int k = 0; k < itemList.size(); k++) {

            if (itemList.get(k).getName().equals(c.getValue().toString())) {

                removeFromItemChoiceBoxes(itemList.get(k));

                itemList.remove(k);

                break;
            }

        }

        itemNodes.remove(c);

        assert c != null;
        c.getParent().getChildren().remove(c);


        deleteItemChoiceBoxRefresh();


        tree.refresh();
    }

    public void search(KeyEvent event) {

        if(searchBar.getText().isBlank()){
            treeMaker();
        }
        else {
            String search = searchBar.getText();

            treeMaker2(search);
        }
    }
}


