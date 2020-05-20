package sql_handler;
import java.sql.*;

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

    public String GetAllDonat(){
        String result="Eredmény";
        try {
            String query = "select * from donation";
            rs = st.executeQuery(query);
            System.out.println("Data I call..");
            while (rs.next()) {
                String act=null;

                act= rs.getString("datum")+" vérnyomás:" +rs.getString("sis")+"/"+
                        rs.getString("dis")+" hemo:"+rs.getString("hemo")+"\n------------------------------------";

                result = result+"\n"+act;


            }
        } catch(Exception ex) {
            System.out.println(ex);
        }


        return result;
    }

}




