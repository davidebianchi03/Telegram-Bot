package jbot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class GUI extends JFrame {

    public JTextField txt_latitude;
    public JTextField txt_longitude;
    public JTextArea txt_pubblicita;
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
        JLabel lbl_lat = new JLabel("Latitudine");
        lbl_lat.setBounds(300, 100, 100, 25);
        lbl_lat.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(lbl_lat);
        txt_latitude = new JTextField("45.0");
        txt_latitude.setBounds(400, 100, 250, 25);
        txt_latitude.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(txt_latitude);

        //elementi per l'inserimento della longitudine
        JLabel lbl_lon = new JLabel("Longitudine");
        lbl_lon.setBounds(300, 140, 100, 25);
        lbl_lon.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(lbl_lon);
        txt_longitude = new JTextField("9.0");
        txt_longitude.setBounds(400, 140, 250, 25);
        txt_longitude.setFont(new Font("Verdana", Font.PLAIN, 13));
        this.add(txt_longitude);

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
        submit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllo se sono stati aggiunti tutti i campi e in caso affermativo salvo la pubblicità nel file
                if(txt_pubblicita.getText() != "" && IsDouble(txt_latitude.getText()) && IsDouble(txt_longitude.getText())){
                    ADV adv = new ADV(txt_pubblicita.getText(), Double.parseDouble(txt_latitude.getText()), Double.parseDouble(txt_longitude.getText()));
                    advList.AddAdv(adv);
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
    private boolean IsDouble(String text){
        try{
            Double.parseDouble(text);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
}
