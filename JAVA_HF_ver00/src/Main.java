import container.Bloodtype;
import sql_handler.SQLConnection;
import container.PersonContainer;
import static container.Gender.MALE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import container.BloodContainer;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
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
