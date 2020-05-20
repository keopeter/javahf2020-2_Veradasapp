package sql_handler;
import java.sql.*;
import java.time.temporal.ChronoUnit;

public class SQLConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;


    public SQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veradas", "root", "");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);

        }
    }
    public void getLog() {
        try {
            String query = "select * from log";
            rs = st.executeQuery(query);
            System.out.println("Echo from deep below:");
            while (rs.next()) {
                String uzenet = rs.getString("uzenet");

                System.out.println(uzenet);
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void setData( String query){
        try {

            st.execute(query);
            System.out.println("Execution Successful");

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public long getlastdaycount() {

        try {
           rs= st.executeQuery("select max(datum) from donation");
            String daystr="0";
            while (rs.next()) {
                daystr = rs.getString("max(datum)");


            }


            Date today=new Date(System.currentTimeMillis());;
            Date nottoday=Date.valueOf(daystr);


            int daysdiff = 0;
            long diff = today.getTime() - nottoday.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
            daysdiff = (int) diffDays;



            return daysdiff;



        } catch (SQLException e) {
            e.printStackTrace();
        }

            return 60;
    }

    public String GetAllDonat(){
        String result="Eredmény";
        try {
            String query = "select * from donation";
            rs = st.executeQuery(query);
            System.out.println("Data I call..");
            int count=0;
            while (rs.next()) {
                String act=null;
                if(rs.getInt("ok") == 1) {
                    count++;
                    act = count + ": " + rs.getString("datum") + " vérnyomás:" + rs.getString("sis") + "/" +
                            rs.getString("dis") + " hemo:" + rs.getString("hemo") + "\n------------------------------------";
                }
                else  act =   "X : " + rs.getString("datum") + "Sikertelen"+ "\n------------------------------------";

                result = result+"\n"+act;


            }
        } catch(Exception ex) {
            System.out.println(ex);
        }


        return result;
    }

}




