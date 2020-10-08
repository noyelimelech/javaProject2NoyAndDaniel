package uiComponents.OrderItemChoiceGui;

import SDM.Item;
import SDM.SDMEngine;
import SDM.StoreItem;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderItemChoiceController {

    @FXML private TableView<StoreItemAdapter> itemTableView;
    @FXML private TableColumn<StoreItemAdapter, Integer> iDCol;
    @FXML private TableColumn<StoreItemAdapter, String> nameCol;
    @FXML private TableColumn<StoreItemAdapter, Item.ItemType> typeCol;
    @FXML private TableColumn<StoreItemAdapter, Integer> priceCol;
    @FXML private Button addItemsButton;
    @FXML private Button finishOrderButton;
    @FXML private TextField itemsCounterTextField;

    private SDMEngine sdmEngine;
    private SimpleBooleanProperty buyWiseOrder = new SimpleBooleanProperty(true);

    @FXML
    public void initialize() {
        iDCol.setCellValueFactory(new PropertyValueFactory<StoreItemAdapter, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<StoreItemAdapter, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<StoreItemAdapter, Item.ItemType>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<StoreItemAdapter, Integer>("price"));
        priceCol.visibleProperty().bind(buyWiseOrder);
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        itemTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            itemsCounterTextField.disableProperty().set(newSelection == null);
            addItemsButton.disableProperty().set(newSelection == null);
        });
    }

    public SDMEngine getSdmEngine() {
        return sdmEngine;
    }

    public void setSdmEngine(SDMEngine sdmEngine) {
        this.sdmEngine = sdmEngine;
        populateTableViewWithItems();
    }

    public boolean getBuyWiseOrder() {
        return buyWiseOrder.get();
    }

    public SimpleBooleanProperty buyWiseOrderProperty() {
        return buyWiseOrder;
    }

    public void setBuyWiseOrder(boolean buyWiseOrder) {
        this.buyWiseOrder.set(buyWiseOrder);
    }

    @FXML
    void addItemButtonAction() {
        //TODO adding item to current cart in sdmEngine
    }

    @FXML
    void finishOrderButtonAction() {
        //TODO finishOrder in sdmEngine

    }

    private void populateTableViewWithItems() {
        /*
        if(!getBuyWiseOrder()) {
            for(StoreItem storeItem : sdmEngine.getCurrentOrder().getStoreOrderMadeFrom().getItemsThatSellInThisStore().values()) {
                itemTableView.getItems().add(new StoreItemAdapter(storeItem));
            }
        }
        else {
            for(Item item : sdmEngine.getAllItems()) {
                itemTableView.getItems().add(new StoreItemAdapter(item));
            }
        }

         */
    }
}