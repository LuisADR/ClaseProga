import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.io.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaDoctorGUI extends JPanel implements ActionListener
{
  private JTextField tfPaciente;
  private JButton bConsultar, bConsultarClave;

  private JTextArea  taDatos;
  private JPanel     panel1, panel2;

  private HospitalAD hospital = new HospitalAD();

    public ConsultaDoctorGUI(){

      //Creamos los objetos
      panel1 = new JPanel();
      panel2 = new JPanel();
      taDatos = new JTextArea(11,30);

      //Creamos JTextField
      tfPaciente = new JTextField();

      //Creamos Botones
      bConsultar = new JButton("Consulta General");
      bConsultarClave = new JButton("Consultar Clave");

      bConsultar.addActionListener(this);
      bConsultarClave.addActionListener(this);

      //Definir los Layouts de los JPanels
      panel1.setLayout(new GridLayout(9,2));
      panel2.setLayout(new GridLayout(2,1));

      panel1.add(new JLabel("Clave Doctor: "));
      panel1.add(tfPaciente);

      panel1.add(bConsultar);
      panel1.add(bConsultarClave);

      panel2.add(panel1);
      panel2.add(new JScrollPane(taDatos));

      this.add(panel2);
      setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
      if(e.getSource() == bConsultar){
        taDatos.setText(hospital.consultarPacientes());
      }

      if(e.getSource() == bConsultarClave){

        String dato = tfPaciente.getText();

        if (dato.isEmpty()){
          taDatos.setText("Ingrese un dato");
        } else {
          taDatos.setText(hospital.joinDoctor(dato));
        }
      }
    }
}
