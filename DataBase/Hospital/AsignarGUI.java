import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.StringTokenizer;

public class AsignarGUI extends JPanel implements ActionListener{
  private JTextField tfDoctor, tfPaciente, tfFecha, tfDiagnostico, tfTratamiento;
  private JButton bCapturar, bConsultar, bConsultarClave;

  private JTextArea  taDatos;
  private JPanel     panel1, panel2;

  private HospitalAD hospital = new HospitalAD();

  public AsignarGUI(){
    //Creamos los objetos
    panel1 = new JPanel();
    panel2 = new JPanel();
    taDatos = new JTextArea(11,30);

    //Creamos JTextField
    tfDoctor = new JTextField();
    tfPaciente = new JTextField();
    tfFecha = new JTextField();
    tfDiagnostico = new JTextField();
    tfTratamiento = new JTextField();

    //Creamos Botones
    bCapturar = new JButton("Asignar");
    bConsultar = new JButton("Consultar");
    bConsultarClave = new JButton("Consultar Claves");

    //Añadimos Action Listener
    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bConsultarClave.addActionListener(this);

    //Definir los Layouts de los JPanels
    panel1.setLayout(new GridLayout(9,2));
    panel2.setLayout(new GridLayout(2,1));

    //Añadimos los Elementos
    panel1.add(new JLabel("Clave Doctor: "));
    panel1.add(tfDoctor);

    panel1.add(new JLabel("Clave Paciente: "));
    panel1.add(tfPaciente);

    panel1.add(new JLabel("Fecha: "));
    panel1.add(tfFecha);

    panel1.add(new JLabel("Diagnostico: "));
    panel1.add(tfDiagnostico);

    panel1.add(new JLabel("Tratamiento: "));
    panel1.add(tfTratamiento);

    panel1.add(bCapturar);
    panel1.add(bConsultar);
    panel1.add(bConsultarClave);

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));

    this.add(panel2);
    setVisible(true);

    inactivarBotones();
  }

  private String obtenerDatos(){
    String datos = "";

    String doctor = tfDoctor.getText();
    String paciente = tfPaciente.getText();
    String fecha = tfFecha.getText();
    String diagnostico = tfDiagnostico.getText();
    String tratamiento = tfTratamiento.getText();

    if(doctor.isEmpty() || paciente.isEmpty() || fecha.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()){
      datos = "VACIO";
    } else {

      datos = doctor+"_"+paciente+"_"+fecha+"_"+diagnostico+"_"+tratamiento;

    }

    return datos;
  }

  private void inactivarBotones(){
    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarClave.setEnabled(true);

  }

  private void activarBotones(){
    bCapturar.setEnabled(false);
    bConsultar.setEnabled(false);
    bConsultarClave.setEnabled(false);

  }

  private void desplegarDatos(String datos)
  {
      StringTokenizer st = new StringTokenizer(datos,"_");

      tfDoctor.setText(st.nextToken());
      tfPaciente.setText(st.nextToken());
      tfFecha.setText(st.nextToken());
      tfDiagnostico.setText(st.nextToken());
      tfTratamiento.setText(st.nextToken());
  }

  public void actionPerformed(ActionEvent e){

    if(e.getSource() == bCapturar){

      String respuesta = obtenerDatos();

      if(respuesta.equals("VACIO")){
        taDatos.setText("Algun Campo Esta Vacio");
      } else{
        if(respuesta.equals("NO_NUMERICO")){
          taDatos.setText("El telefono tiene que ser Numerico Con Max 9 Digitos");
        } else {
          taDatos.setText(hospital.asignarDoctor(respuesta));
        }
      }
    }

    if(e.getSource() == bConsultar){
      taDatos.setText(hospital.consultarAtiende());
    }

    if(e.getSource() == bConsultarClave){

      String respuesta = hospital.consultarClaves(tfDoctor.getText(), tfPaciente.getText());

      if (respuesta.equals("NOT_FOUND")){
        taDatos.setText("No se encontro la clave");
      } else {
        taDatos.setText(respuesta);
        desplegarDatos(respuesta);
      }

    }

  }
}
