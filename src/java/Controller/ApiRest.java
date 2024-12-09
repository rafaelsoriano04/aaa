package Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRest {

    private final String url = "http://localhost/SOA/controllers/apiRest.php";

    public String getStudents() {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                System.out.println("Error");

            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return result.toString();
    }

    private String sendRequest(String method, String cedula, String nombre, String apellido, String direccion, String telefono) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            String params = "cedula=" + cedula
                    + "&nombre=" + nombre
                    + "&apellido=" + apellido
                    + "&direccion=" + direccion
                    + "&telefono=" + telefono;
            DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
            writer.writeBytes(params);
            writer.flush();
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
        return "";
    }

    public String saveStudent(String cedula, String nombre, String apellido, String direccion, String telefono) {
        return sendRequest("POST", cedula, nombre, apellido, direccion, telefono);
    }

    public String updateStudent(String cedula, String nombre, String apellido, String direccion, String telefono) {
        return sendRequest("PUT", cedula, nombre, apellido, direccion, telefono);
    }

    public String deleteStudent(String cedula) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url + "?cedula=" + cedula);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                System.out.println("Error");

            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return result.toString();
    }
    
     public String getStudentByCedula(String cedula) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url + "?cedula=" + cedula);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                System.out.println("Error");

            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return result.toString();
    }

}
