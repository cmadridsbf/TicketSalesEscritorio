/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Controladores.traerJson;
import static Controladores.traerJson.getRegistros;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cmadrid
 */
public class formUsuariosConfirmados extends javax.swing.JFrame {
    private JPanel panelChartPanel1;
    traerJson getData = new traerJson();
    String jsonUsuarios = traerJson.getUsuarios();
    JSONArray jsonArray = new JSONArray(jsonUsuarios);
    /**
     * Creates new form formHome
     */
    public formUsuariosConfirmados() {
        initComponents();
        // Asegúrate de que panelContenedor se inicialice antes de llamar a generarGraficaGanancias
        panelChartPanel1 = new JPanel();
        panelChartPanel1.setLayout(new BorderLayout());
        getContentPane().add(panelChartPanel1, BorderLayout.CENTER);
        initChart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TICKETSALES");

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Reportes TicketSales");

        btnGenerarReporte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnGenerarReporte.setText("GENERAR PDF");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 1351, Short.MAX_VALUE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 670, Short.MAX_VALUE)
                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:
        generarPDF();
    }//GEN-LAST:event_btnGenerarReporteActionPerformed
    
    private void generarPDF() {
        Document document = new Document();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte");

        // Establecer una sugerencia para el nombre del archivo
        fileChooser.setSelectedFile(new File("ReporteUsuariosConfirmados.pdf"));

        // Mostrar el diálogo de guardar archivo
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Asegúrate de que el archivo tenga la extensión .pdf
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            try {
                // Crear un PdfWriter que escucha al documento y escribe a un archivo
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

                // Abrir el documento
                document.open();

                // Crear un párrafo y agregarlo al documento
                Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
                Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph title = new Paragraph("Reporte de Usuarios Confirmados", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(Chunk.NEWLINE);

                // Formatear y mostrar la fecha actual
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String currentDate = dateFormat.format(new Date());
                Paragraph fecha = new Paragraph("Fecha: " + currentDate, smallBold);
                fecha.setAlignment(Element.ALIGN_RIGHT);
                document.add(fecha);

                // Agregar otro espacio en blanco
                document.add(Chunk.NEWLINE);

                Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                Font bodyFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

                PdfPCell headerCell = new PdfPCell();
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setPadding(5);

                PdfPTable table = new PdfPTable(3); // Número de columnas

                

                headerCell.setPhrase(new Phrase("Nombre", headerFont));
                table.addCell(headerCell);

                headerCell.setPhrase(new Phrase("Apellido", headerFont));
                table.addCell(headerCell);

                headerCell.setPhrase(new Phrase("Correo", headerFont));
                table.addCell(headerCell);


                PdfPCell bodyCell = new PdfPCell();
                bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                bodyCell.setPadding(5);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int confirmado = jsonObject.optInt("confirmado");
                    if (confirmado == 1) {

                        bodyCell.setPhrase(new Phrase(jsonObject.optString("nombre"), bodyFont));
                        table.addCell(bodyCell);
                        bodyCell.setPhrase(new Phrase(jsonObject.optString("apellido"), bodyFont));
                        table.addCell(bodyCell);
                        bodyCell.setPhrase(new Phrase(jsonObject.optString("email"), bodyFont));
                        table.addCell(bodyCell);
                    }
                }

                // Agregar la tabla al documento
                document.add(table);

            } catch (DocumentException | FileNotFoundException e) {
                // Utiliza StringWriter para capturar la traza de la excepción en un String
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String exceptionText = sw.toString();

                // Muestra la traza de la excepción en el cuadro de diálogo
                JOptionPane.showMessageDialog(this, exceptionText, "Alerta", JOptionPane.WARNING_MESSAGE);
            } finally {
                // Cerrar el documento
                document.close();
                try {
                    Desktop.getDesktop().open(fileToSave);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formUsuariosConfirmados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formUsuariosConfirmados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formUsuariosConfirmados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formUsuariosConfirmados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formUsuariosConfirmados().setVisible(true);
            }
        });
    }
    
     
     private void initChart() {
        // Datos de ejemplo
        // Suponiendo que esto es tu método estático
        int confirmedUsers = 0;
        int notConfirmedUsers = 0;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int confirmado = jsonObject.optInt("confirmado");
            if (confirmado == 1) {
                confirmedUsers++;
            } else {
                notConfirmedUsers++;
            }
        }

        // Crear dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(confirmedUsers, "Confirmados", "");
        dataset.addValue(notConfirmedUsers, "No Confirmados", "");

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Confirmación de Usuarios",
                "Estado",
                "Cantidad",
                dataset
        );

        // Crear el ChartPanel con el gráfico
        panelChartPanel1 = new ChartPanel(chart);
        panelChartPanel1.setBounds(10, 100, 1350, 630); // Ajustar las dimensiones según sea necesario

        // Agregar el ChartPanel al JFrame
        getContentPane().add(panelChartPanel1);
        getContentPane().revalidate();
        getContentPane().repaint();
        //panelChartPanel.add(panelChartPanel1);
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
