package es.izquierdofp.retrovault;

import com.formdev.flatlaf.FlatDarkLaf;
import es.izquierdofp.retrovault.datos.DatabaseManager;
import es.izquierdofp.retrovault.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo cargar FlatLaf: " + e.getMessage());
        }

        DatabaseManager.inicializar();

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
