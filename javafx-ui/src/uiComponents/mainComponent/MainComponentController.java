package uiComponents.mainComponent;

import SDM.Exception.*;
import SDM.Item;
import SDM.SDMEngine;
import SDM.Store;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import uiComponents.FXMLLoaderProxy;
import uiComponents.itemComponent.itemUIController;
import uiComponents.storeGUI.StoreGUIController;
import uiComponents.xmlLoadingGUI.XmlLoadingController;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainComponentController {

    @FXML private Button loadXMLButton;
    @FXML private Button showStoresButton;
    @FXML private Button showItemsButton;
    @FXML private Button showOrderButton;
    @FXML private Button oneStoreButton;
    @FXML private Button buyWiseOrderButton;
    @FXML private FlowPane dynamicAreaFlowPane;
    @FXML private ScrollPane innerScrollPane;

    private Stage primaryStage;
    private SDMEngine sdmEngine;

    private SimpleBooleanProperty isXMLFileLoaded;
    private SimpleBooleanProperty isAnyOrderMade;

    public MainComponentController() {
        isXMLFileLoaded = new SimpleBooleanProperty(false);
        isAnyOrderMade = new SimpleBooleanProperty(false);
    }

    public SimpleBooleanProperty isXMLFileLoadedProperty() {
        return isXMLFileLoaded;
    }

    public SimpleBooleanProperty isAnyOrderMadeProperty() {
        return isAnyOrderMade;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }


    public void setSdmEngine(SDMEngine sdmEngine) {
        this.sdmEngine=sdmEngine;
    }

    @FXML
    public void initialize() {
        showItemsButton.disableProperty().bind(isXMLFileLoaded.not());
        showStoresButton.disableProperty().bind(isXMLFileLoaded.not());
        oneStoreButton.disableProperty().bind(isXMLFileLoaded.not());
        buyWiseOrderButton.disableProperty().bind(isXMLFileLoaded.not());
        showOrderButton.disableProperty().bind(isXMLFileLoaded.not().or(isAnyOrderMade.not()));

    }

    @FXML
    void loadXMLButtonAction()
    {
        dynamicAreaFlowPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        URL fxmlLocation = getClass().getResource("/uiComponents/xmlLoadingGUI/xmlLoadingFXML.fxml");
        loader.setLocation(fxmlLocation);
        Node xmlLoadingGUI = null;
        try {
            xmlLoadingGUI = loader.load();
        } catch (IOException e) {
            Alert loadingAlert = new Alert(Alert.AlertType.ERROR);
            loadingAlert.setTitle("FXML Loading ERROR");
            loadingAlert.setHeaderText("Seems like we got a problem on our error loading... Nothing you can do...");

        }
        XmlLoadingController xmlLoadingController = loader.getController();
        xmlLoadingController.setSdmEngine(sdmEngine);
        xmlLoadingController.setStage(primaryStage);
        dynamicAreaFlowPane.getChildren().add(xmlLoadingGUI);

        //TODO here only for testing, should not be here. Binding should be done to property of SDMEngine
        isXMLFileLoaded.set(true);


    }


    @FXML
    void buyWiseOrderButtonAction() {


    }



    @FXML
    void oneStoreButtonAction() {

    }

    @FXML
    void showItemsButtonAction() throws IOException {
        dynamicAreaFlowPane.getChildren().clear();



            for (Item item:sdmEngine.getAllItems())
            {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlLocation = getClass().getResource("/uiComponents/itemComponent/itemUIFxml.fxml");
                loader.setLocation(fxmlLocation);
                Node itemUI = loader.load();
                itemUIController itemController = loader.getController();

                itemController.setItemLables(item);
                dynamicAreaFlowPane.getChildren().add(itemUI);

            }

    }

    @FXML
    void showOrderButtonAction() {

    }

    @FXML
    void showStoresButtonAction() {
        dynamicAreaFlowPane.getChildren().clear();

        for (Store store : sdmEngine.getAllStores())
        {
            FXMLLoaderProxy loader = new FXMLLoaderProxy();
            URL fxmlLocation = getClass().getResource("/uiComponents/storeGUI/storeGUIFXML.fxml");
            loader.setLocation(fxmlLocation);

            Node itemUI = loader.load();
            StoreGUIController storeGUIController = loader.getController();

            storeGUIController.setStore(store);

            dynamicAreaFlowPane.getChildren().add(itemUI);

        }

    }

    //TODO
    @FXML
    void showCostumersButtonAction() {
        /*
        for (Item item:sdmEngine.get)
        {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/uiComponents/itemComponent/itemUIFxml.fxml");
            loader.setLocation(fxmlLocation);
            Node itemUI = loader.load();
            itemUIController itemController = loader.getController();

            itemController.setItemLables(item);
            dynamicAreaFlowPane.getChildren().add(itemUI);

        }
        */
    }


}
