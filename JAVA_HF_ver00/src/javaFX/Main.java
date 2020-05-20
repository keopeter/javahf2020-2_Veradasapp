package javaFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sql_handler.SQLConnection;
import static java.lang.Integer.parseInt;
import container.BloodContainer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

public class Main extends Application {
    Stage window;
    Scene mainscene, setscene,veradasscene,wiewscene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("my_prop.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(properties.getProperty("Name")==null){
            properties.setProperty("Name","ismeretlen hős");
            properties.setProperty("City","messzi messzi galaxis");
            properties.setProperty("Weight","68");
            properties.setProperty("Gender","Férfi");
            properties.setProperty("Type","AB+");

        }

        window=primaryStage;

        //////////////////////////////////INDULÓ
        Text Text1= new Text("Véradás Java App");
        Text1.setStyle("-fx-font: 30 arial;");
        Text1.setFill(Color.RED);


        SQLConnection conn2 = new SQLConnection();
        conn2.getLog();
        String records=conn2.GetAllDonat();

        Button verad = new Button("Vért Adtam!");
        verad.setOnAction(e->window.setScene(veradasscene));
        Button setbutton = new Button("Beállítások");
        setbutton.setOnAction(e->window.setScene(setscene));
        Button wiewbutton = new Button("Böngészés");
        wiewbutton.setOnAction(e->window.setScene(wiewscene));
        Label cani = new Label();

        long days= conn2.getlastdaycount();
        if(days>53.0){cani.setText("Adhatsz vért!");
                        cani.setTextFill(Color.RED);
        }
        else{ cani.setText("Nem adhatsz vért, még " + (53-days) + " napig! ");
            cani.setTextFill(Color.GREEN);

        }


        Hyperlink link= new Hyperlink("https://www.veradas.hu/");
        link.fire();
        Label labeltud= new Label("Tudj meg többet a Véradásról!");

        Label labelname= new Label(properties.getProperty("Name") +" / "+properties.getProperty("City"));


        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(Text1,verad,wiewbutton,setbutton,cani,labeltud,link,labelname);
        layout1.setAlignment(Pos.CENTER);
        mainscene =new Scene(layout1,300,400);

        ////////////////////////BEÁLLÍTÁSOK

        TextField txf1= new TextField(properties.getProperty("Name"));
        txf1.setPromptText("Név");
        TextField txf2= new TextField(properties.getProperty("City"));
        txf2.setPromptText("Város");
        TextField txf3= new TextField(properties.getProperty("Weight"));
        txf3.setPromptText("Tömeg(kg)");
        Button backbtn = new Button("Vissza!");
        backbtn.setOnAction(e->window.setScene(mainscene));

        Text text2= new Text("Beállítások");
        text2.setStyle("-fx-font: 30 arial;");
        text2.setFill(Color.RED);

        Label lblood=new Label("Vércsoport");
        Label lgender=new Label("Neme");

        ChoiceBox chb1= new ChoiceBox();
        chb1.setValue(properties.get("Type"));

        chb1.getItems().add("0+");
        chb1.getItems().add("0-");
        chb1.getItems().add("A+");
        chb1.getItems().add("A-");
        chb1.getItems().add("B+");
        chb1.getItems().add("B-");
        chb1.getItems().add("AB+");
        chb1.getItems().add("AB-");

        CheckBox cb1= new CheckBox("Nő");
        cb1.setSelected(properties.getProperty("Gender").equals("Nő"));
        CheckBox cb2= new CheckBox("Férfi");
        cb2.setSelected(properties.getProperty("Gender").equals("Férfi"));

        Button savbtn = new Button("Mentés");
        savbtn.setOnAction(e->{

            properties.setProperty("Name",txf1.getText());
            properties.setProperty("City",txf2.getText());
            properties.setProperty("Weight",txf3.getText());
            properties.setProperty("Type",chb1.getValue().toString());

            if (cb1.isSelected())properties.setProperty("Gender","Nő");
            else properties.setProperty("Gender","Férfi");

            try{
                properties.store(new FileOutputStream("my_prop.properties"), "mycomment");
                System.out.println("Properties átírva");
            }
            catch (
                    IOException exx) {
                exx.printStackTrace();
            }

            System.out.println( "Restarting app!" );
            primaryStage.close();
            Platform.runLater( () -> {
                try {
                    new Main().start( new Stage() );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            window.setScene(mainscene);});

        HBox helper= new HBox(10);
        HBox helper2= new HBox(10);
        VBox layout2= new VBox(10);
        layout2.setAlignment(Pos.CENTER);
        helper.getChildren().addAll(lgender,cb1,cb2,lblood,chb1);
        helper2.getChildren().addAll(savbtn,backbtn);
        layout2.getChildren().addAll(text2,txf1,txf2,txf3,helper,helper2);
        setscene =new Scene(layout2,300,400);

        try{
            properties.store(new FileOutputStream("my_prop.properties"), "mycomment");
        }
        catch (
                IOException e) {
            e.printStackTrace();
        }


        //////////////////////////VÉRADÁS

        Button button2 = new Button("Vissza!");
        button2.setOnAction(e->window.setScene(mainscene));
        Button buttonsave = new Button("Mentés");


        Text text3= new Text("Véradás");
        text3.setStyle("-fx-font: 30 arial;");
        text3.setFill(Color.RED);

        TextField txdate= new TextField();
        txdate.setPromptText("Véradás Dátuma");
        TextField txf4= new TextField();
        txf4.setPromptText("Sistole");
        TextField txf5= new TextField();
        txf5.setPromptText("Diastole");
        TextField txf6= new TextField();
        txf6.setPromptText("Hemoglobin");

        CheckBox cbok= new CheckBox("Minden rendben ment. ");

        buttonsave.setOnAction(e->{
            if(txdate.getText()!=null){

                BloodContainer container=new BloodContainer(Date.valueOf(txdate.getText()),parseInt(txf6.getText()),
                                                                parseInt(txf4.getText()),parseInt(txf5.getText()),cbok.isSelected());
                SQLConnection conn = new SQLConnection();
                conn.getLog();
                conn.setData(container.toQuery());

                System.out.println( "Restarting app!" );
                primaryStage.close();
                Platform.runLater( () -> {
                    try {
                        new Main().start( new Stage() );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                window.setScene(wiewscene);

            }


            window.setScene(mainscene);});
        VBox layout3= new VBox(10);
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(text3,txdate,txf4,txf5,txf6,cbok,button2,buttonsave);
        veradasscene =new Scene(layout3,300,400);

        ////////////////////////Böngészés


        records=conn2.GetAllDonat();
        ScrollPane scrollPane = new ScrollPane();
        Label bigone=new Label(records);

        Button backbtn2 = new Button("Vissza!");

        backbtn2.setOnAction(e->window.setScene(mainscene));
        Text text4= new Text("Korábbi véradások");
        text4.setStyle("-fx-font: 30 arial;");
        text4.setFill(Color.RED);


        VBox layout4= new VBox(10);
        VBox layout4helper= new VBox(10);
        layout4helper.setAlignment(Pos.CENTER);


        layout4helper.getChildren().addAll(bigone);

        scrollPane.setContent(layout4helper);


        scrollPane.pannableProperty().set(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        layout4.getChildren().addAll(text4,scrollPane,backbtn2);
        layout4.setAlignment(Pos.CENTER);
        wiewscene = new Scene(layout4, 300, 400);




        //////////////////////////
        window.setScene(mainscene);
        window.setTitle("Véradás Java App");
        window.show();

    }


    public static void main(String[] args) {

        launch(args);

        System.out.println("I am alive, you can't bring me down!");

    }
}





