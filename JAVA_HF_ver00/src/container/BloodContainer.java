package container;

import java.sql.Date;

import static java.lang.Boolean.TRUE;

public class BloodContainer {
    private Date date;
    private int hemo; //hemoglobin szint
    private int sis; //szisztolés
    private int dis;//diasztolés (kisebb)
    boolean ok;


    public BloodContainer(Date date, int hemo, int sis, int dis, boolean ok) {
        this.date = date;
        this.hemo = hemo;
        this.sis = sis;
        this.dis = dis;
        this.ok=ok;
    }

    public String  toString(){
        if(this.ok ==TRUE){
         String tos="Véradás \nDátuma:"+this.date.toString()+"\n"+"Vérnyomás:"+this.sis+"/"+this.dis+"\n"+"Hemoglobin:"+this.hemo+"\n";
            return tos;
        }
        else return "Sikertelen Véradás  \nDátuma:"+this.date.toString();
    }

    public String toQuery(){

        if(this.ok ==TRUE) {
            String tos = "insert into donation(datum, dis,sis,hemo,ok) values(" + "\"" + this.date.toString() + "\"" + "," + this.dis + "," + this.sis + "," + this.hemo + ",true);"; //OK
            return tos;
        };
            String tus = "insert into donation(datum, dis,sis,hemo,ok) values(" + "\"" + this.date.toString() + "\"" + "," + this.dis + "," + this.sis + "," + this.hemo + ",false);"; //Not OK
        return tus;
    }
}




