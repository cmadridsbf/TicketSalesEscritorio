/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
//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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author cmadrid
 */
public class traerJson {
    
    
     public static String getUsuarios() {
         return getJson("https://ticketsales.store/public/RestAPI/GetUser.php");
         
     }
     
     public static String getRegistros() {
         return getJson("https://ticketsales.store/public/RestAPI/GetRegistros.php");
         
     }
     
     public static String getPonentes() {
         return getJson("https://ticketsales.store/public/RestAPI/GetPonentes.php");
         
     }
     
     public static String getEventos() {
         return getJson("https://ticketsales.store/public/RestAPI/GetEventos.php");
         
     }
     public static String getJson(String urlString){
        StringBuilder jsonResponse = new StringBuilder();

        try {
            // Crear un objeto URL
            URL url = new URL(urlString);

            // Abrir una conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar método GET
            connection.setRequestMethod("GET");

            // Establecer tiempo de espera para la respuesta
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Construir el BufferedReader para leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            // Leer la respuesta línea por línea y agregarla al StringBuilder
            while ((inputLine = in.readLine()) != null) {
                jsonResponse.append(inputLine);
            }

            // Cerrar el BufferedReader
            in.close();

            // Desconectar la conexión HTTP
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolver el contenido del JSON como una cadena
        return jsonResponse.toString();
    }
}