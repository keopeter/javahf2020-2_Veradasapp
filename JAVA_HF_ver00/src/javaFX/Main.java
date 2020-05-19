package javaFX;

import container.Bloodtype;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sql_handler.SQLConnection;
import container.PersonContainer;
import static container.Gender.MALE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import container.BloodContainer;

import java.sql.Date;

public class Main extends Application {
    Stage window;
    Scene mainscene, setscene,veradasscene,wiewscene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;

        //////////////////////////////////INDULÓ
        Text label1= new Text("Véradás Java App");

        Button verad = new Button("Vért Adtam!");
        verad.setOnAction(e->window.setScene(veradasscene));
        Button setbutton = new Button("Beállítások");
        setbutton.setOnAction(e->window.setScene(setscene));
        Button wiewbutton = new Button("Böngészés");
        wiewbutton.setOnAction(e->window.setScene(wiewscene));
        Hyperlink link= new Hyperlink("https://www.veradas.hu/");
        Label labeltud= new Label("Tudj meg többet a Véradásról!");

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1,verad,wiewbutton,setbutton,labeltud,link);
        mainscene =new Scene(layout1,600,400);

        ////////////////////////BEÁLLÍTÁSOK

        TextField txf1= new TextField();
        txf1.setPromptText("Név");
        TextField txf2= new TextField();
        txf2.setPromptText("Város");
        TextField txf3= new TextField();
        txf3.setPromptText("Tömeg(kg)");
        Button backbtn = new Button("Vissza!");
        backbtn.setOnAction(e->window.setScene(mainscene));
        Button savbtn = new Button("Mentés");
        savbtn.setOnAction(e->{
            System.out.println("Properties-t írni kellene");
            window.setScene(mainscene);});
        Text label2= new Text("Beállítások");

        Label lblood=new Label("Vércsoport");
        Label lgender=new Label("Neme");

        ChoiceBox chb1= new ChoiceBox();
        chb1.setValue("0+");

        chb1.getItems().add("0+");
        chb1.getItems().add("0-");
        chb1.getItems().add("A+");
        chb1.getItems().add("A-");
        chb1.getItems().add("B+");
        chb1.getItems().add("B-");
        chb1.getItems().add("AB+");
        chb1.getItems().add("AB-");

        CheckBox cb1= new CheckBox("Nő");
        CheckBox cb2= new CheckBox("Férfi");
        VBox layout2= new VBox(10);
        layout2.getChildren().addAll(label2,txf1,txf2,txf3,lgender,cb1,cb2,lblood,chb1,savbtn,backbtn);
        setscene =new Scene(layout2,600,400);
        //////////////////////////VÉRADÁS

        Button button2 = new Button("Vissza!");
        button2.setOnAction(e->window.setScene(mainscene));
        Button buttonsave = new Button("Mentés");
        buttonsave.setOnAction(e->{

            System.out.println("Menteni kellene");
            window.setScene(mainscene);});

        Text label3= new Text("Véradás");
        Label dias=new Label("Sistole");
        Label sis=new Label("Diastole");
        Label hemo=new Label("Hemoglobin");

        TextField txdate= new TextField();
        txdate.setPromptText("Véradás Dátuma");
        TextField txf4= new TextField();
        txf4.setPromptText("Sistole");
        TextField txf5= new TextField();
        txf5.setPromptText("Diastole");
        TextField txf6= new TextField();
        txf6.setPromptText("Hemoglobin");

        CheckBox cbok= new CheckBox("Sikeres Véradás");

        VBox layout3= new VBox(10);
        layout3.getChildren().addAll(label3,txdate,txf4,txf5,txf6,button2,buttonsave);
        veradasscene =new Scene(layout3,600,400);

        ////////////////////////Böngészés
        Button backbtn2 = new Button("Vissza!");

        backbtn2.setOnAction(e->window.setScene(mainscene));
        Text lbwiew= new Text("Korábbi véradások");
        VBox layout4= new VBox(10);
        layout4.getChildren().addAll(lbwiew,backbtn2);
        wiewscene=new Scene(layout4,600,400);

        //////////////////////////
        window.setScene(mainscene);
        window.setTitle("Véradás Java App");
        window.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Véradás JAVA-App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();*/
    }


    public static void main(String[] args) {



        launch(args);




        System.out.println("I am alive, you can't bring me down!");

        SQLConnection connect = new SQLConnection();
        connect.getLog();
        connect.setData("insert into donation(datum, dis,sis,hemo,ok) values(\"2012-12-02\",80,120,134,true);"); //OK

        PersonContainer joco = new PersonContainer("Józsi",85.4,"Budapest",MALE, Bloodtype.AN);
        BloodContainer elso =new BloodContainer(Date.valueOf("1930-10-21"),150,120,80,TRUE);
        BloodContainer masodik =new BloodContainer(Date.valueOf("1930-10-21"),150,120,80,FALSE);

        connect.setData(elso.toQuery());
        connect.setData(masodik.toQuery());

        System.out.println(elso.toString());
        System.out.println(masodik.toString());

    }






}





