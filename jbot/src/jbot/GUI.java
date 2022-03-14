package jbot;

import GeoUtilis.Coordinate;
import GeoUtilis.Geocoding;
import GeoUtilis.Place;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Davide
 */
public class GUI extends JFrame {

    public JTextField txt_citta;
    public JTextArea txt_pubblicita;
    public JTextField txt_raggio;
    public ADVList advList;

    public GUI() {
        getContentPane().setLayout(null);//-->Importante 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermo tutta l'applicazione quando chiudo il jframe
        setSize(1000, 700);
        advList = ADVList.GetInstance();
        DrawGUI();
        setVisible(true);
    }

    public void DrawGUI() {
        //elementi per l'inserimento della latitudine
        JLabel lbl_citta = new JLabel("Città");
        lbl_citta.setBounds(300, 100, 100, 25);
        lbl_citta.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(lbl_citta);
        txt_citta = new JTextField("");
        txt_citta.setBounds(400, 100, 250, 25);
        txt_citta.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(txt_citta);

        //elementi per l'inserimento della longitudine
        JLabel lbl_raggio = new JLabel("Raggio (in metri)");
        lbl_raggio.setBounds(275, 140, 250, 25);
        lbl_raggio.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(lbl_raggio);
        txt_raggio = new JTextField("1000");
        txt_raggio.setBounds(400, 140, 250, 25);
        txt_raggio.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(txt_raggio);

        //elementi per l'inserimento della descrizione della pubblicità
        JLabel lbl_description = new JLabel("Descrizione");
        lbl_description.setBounds(300, 180, 100, 25);
        lbl_description.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(lbl_description);
        txt_pubblicita = new JTextArea("");
        txt_pubblicita.setBounds(400, 180, 250, 100);
        txt_pubblicita.setLineWrap(true);
        txt_pubblicita.setWrapStyleWord(true);
        txt_pubblicita.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(txt_pubblicita);

        //pulsante utilizzato per confermare l'inserimento
        JButton submit_btn = new JButton("Aggiungi");
        submit_btn.setBackground(new Color(134, 194, 247));
        submit_btn.setFont(new Font("Verdana", Font.PLAIN, 13));
        submit_btn.setBounds(450, 300, 100, 40);
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllo se sono stati aggiunti tutti i campi e in caso affermativo salvo la pubblicità nel file
                if (txt_pubblicita.getText() != "" && IsRadiusValid(txt_raggio.getText()) && txt_citta.getText() != "") {
                    try {
                        List<Place> placeList = Geocoding.SearchPlace(txt_citta.getText());

                        if (placeList.size() > 0) {
                            SendADV sa = new SendADV(placeList.get(0).getCoordinate(), txt_pubblicita.getText(), Integer.parseInt(txt_raggio.getText()));
                            sa.start();
                            JOptionPane.showMessageDialog(txt_citta.getParent(), "Messaggi inviati correttamente");
                            txt_pubblicita.setText("");
                            txt_raggio.setText("");
                            txt_citta.setText("");
                        } else {
                            JOptionPane.showMessageDialog(txt_citta.getParent(), "Non esistono luoghi con questo nome");
                        }

                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(txt_citta.getParent(), "Errore nell'invio del messaggio");
                    } catch (SAXException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(txt_citta.getParent(), "Errore nell'invio del messaggio");

                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(txt_citta.getParent(), "Errore nell'invio del messaggio");
                    }
                } else {
                    JOptionPane.showMessageDialog(txt_citta.getParent(), "Non tutti i campi sono stati riempiti in modo corretto");
                }
            }

        });
        this.add(submit_btn);
    }

    public void setFrameIcon(String path) {
        URL url = getClass().getResource(path);
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    //funzione per controllare se una stringa è un numero double oppure no
    private boolean IsRadiusValid(String text) {
        try {
            int r = Integer.parseInt(text);
            if (r <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
