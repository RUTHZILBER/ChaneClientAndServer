package changeclient;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener {
    //gui: with switchString
    private static String[] switchString = {"kg", "ton", "kiloTon", "hectogram", "dag", "gram", "karat", "tzetigram",
        "mg", "microgram", "nenogram", "paund", "gr",
        "ston", "onkia", "mark", "agora", "masaAtom"};

    private ConvertWeight object = new ConvertWeight(); // object to be sent for server handling
    private JLabel lTiltle = new JLabel("CONVERT WEIGHT");
    private JLabel lbl1 = new JLabel("CONVERT");
    private JLabel lbl2 = new JLabel("To");
    private JTextField txtFrom = new JTextField();
    private JTextField txtTo = new JTextField();
    private JButton btnSubmit = new JButton("CONVERT");
    private JComboBox cmbFrom, cmbTo;
    private Client client = new Client();

    public static void main(String[] args) {
        new ClientGUI();
    }

    public ClientGUI() {

        Font font1 = new Font("Showcard Gothic", Font.BOLD, 150);//Showcard GothicCooper Black
        Font font2 = new Font("Ink Free", Font.BOLD, 30);//Showcard GothicCooper Black
        Font font3 = new Font("Showcard Gothic", Font.BOLD, 90);//Showcard GothicCooper Black

        //Showcard Gothic Cooper Black
        lTiltle.setFont(font3);
        btnSubmit.setFont(font1);
        txtTo.setEditable(false);
        JPanel pMain = new JPanel(), pHeader = new JPanel(), middlePannel = new JPanel(), pButtonArea = new JPanel();
        pMain.setLayout(new GridLayout(0, 1));
        pHeader.setLayout(new GridLayout(1, 1));
        lTiltle.setHorizontalAlignment(SwingConstants.CENTER);
        pHeader.add(lTiltle, BorderLayout.NORTH);
        middlePannel.setLayout(new FlowLayout());
        middlePannel.add(lbl1);
        pHeader.setBackground(Color.BLUE);

        cmbFrom = new JComboBox(switchString);
        cmbFrom.addActionListener(this);
        cmbFrom.setPreferredSize(new Dimension(200, 50));
        middlePannel.add(txtFrom);
        middlePannel.add(cmbFrom);
        middlePannel.add(lbl2);
        cmbTo = new JComboBox(switchString);
        cmbTo.addActionListener(this);
        cmbTo.setPreferredSize(new Dimension(200, 50));
        middlePannel.add(txtTo);
        middlePannel.add(cmbTo);
        txtFrom.setHorizontalAlignment(JTextField.LEFT);
        txtFrom.setPreferredSize(new Dimension(100, 50));
        txtTo.setHorizontalAlignment(JTextField.LEFT);
        txtTo.setPreferredSize(new Dimension(100, 50));
        pButtonArea.setLayout(new GridLayout(1, 1));
        btnSubmit.setPreferredSize(new Dimension(200, 50));
        pButtonArea.add(btnSubmit);
        pMain.add(pHeader);
        pMain.add(middlePannel);
        pMain.add(pButtonArea);
        setContentPane(pMain);
        pMain.setBackground(Color.yellow);
        btnSubmit.addActionListener(new ButtonListener());
        setTitle("המרת משקל");
        setSize(1370, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        txtFrom.setFont(font2);
        txtTo.setFont(font2);
        cmbFrom.setFont(font2);
        cmbTo.setFont(font2);
        lbl1.setFont(font2);
        lbl2.setFont(font2);
        lTiltle.setForeground(Color.white);
        middlePannel.setBackground(Color.magenta);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setBackground(Color.darkGray);

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Send the ConvertWeight object to the serve
/////	
            object = new ConvertWeight();

            object.setAmountA(Double.valueOf(txtFrom.getText().trim()) + 0);// +0 is used if no value is written in
            // txtFrom.

            object.setIndexA(cmbFrom.getSelectedIndex()); // getting index inx.
            object.setIndexB(cmbTo.getSelectedIndex());

            System.out.println(object + "before sending"); // check data prior sending - debug
            client.writeToServer(object);

            // Get outputString from the server
            ConvertWeight outputObject = (ConvertWeight) client.readFromServer();
            System.out.println(outputObject + "after sending");// debug

            // Display to the text the changed amount of money
            txtTo.setText(outputObject.getAmountB() + "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == cmbFrom) {
            // object.setAmountA(Integer.valueOf((txtFrom.getText().trim())));
            object.setIndexA(cmbFrom.getSelectedIndex()); // getting index inx.
        }
        if (e.getSource() == cmbTo) {
            object.setIndexB(cmbTo.getSelectedIndex());
        }
    }
}
