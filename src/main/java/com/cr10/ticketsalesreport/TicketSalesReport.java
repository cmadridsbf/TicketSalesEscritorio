/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cr10.ticketsalesreport;
import Formularios.formHome;
import Formularios.formReportesUsuarios;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author cmadrid
 */
public class TicketSalesReport {

    public static void main(String[] args) {
       try {
            // Establece el Look and Feel de FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Aquí es donde inicializarías y mostrarías tu JFrame principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formHome().setVisible(true);
            }
        });
    }
}
