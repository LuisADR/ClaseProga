import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.StringTokenizer;

public class DoctorGUI extends JPanel implements ActionListener{
  private JTextField tfClave, tfNombre, tfDireccion, tfTelefono, tfEspecialidad;
  private JButton bCapturar, bConsultar, bConsultarClave, bActualizar, bCancelar;

  private JTextArea  taDatos;
  private JPanel     panel1, panel2;

  private HospitalAD hospital = new HospitalAD();

  public DoctorGUI(){
    //Creamos los objetos
    panel1 = new JPanel();
    panel2 = new JPanel();
    taDatos = new JTextArea(11,30);

    //Creamos JTextField
    tfClave = new JTextField();
    tfNombre = new JTextField();
    tfDireccion = new JTextField();
    tfTelefono = new JTextField();
    tfEspecialidad = new JTextField();

    //Creamos Botones
    bCapturar = new JButton("Capturar");
    bConsultar = new JButton("Consultar Doctores");
    bConsultarClave = new JButton("Consultar Clave");
    bActualizar = new JButton("Actualizar");
    bCancelar = new JButton("Cancelar");

    //Añadimos Action Listener
    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bActualizar.addActionListener(this);
    bConsultarClave.addActionListener(this);
    bCancelar.addActionListener(this);

    //Definir los Layouts de los JPanels
    panel1.setLayout(new GridLayout(9,2));
    panel2.setLayout(new GridLayout(2,1));

    //Añadimos los Elementos
    panel1.add(new JLabel("Clave: "));
    panel1.add(tfClave);

    panel1.add(new JLabel("Nombre: "));
    panel1.add(tfNombre);

    panel1.add(new JLabel("Direccion: "));
    panel1.add(tfDireccion);

    panel1.add(new JLabel("Especialidad: "));
    panel1.add(tfEspecialidad);

    panel1.add(new JLabel("Telefono: "));
    panel1.add(tfTelefono);

    panel1.add(bCapturar);
    panel1.add(bConsultar);
    panel1.add(bConsultarClave);
    panel1.add(bActualizar);
    panel1.add(bCancelar);

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));

    this.add(panel2);
    setVisible(true);

    inactivarBotones();
  }

  private String obtenerDatos(){
    String datos = "";

    String clave = tfClave.getText();
    String nombre = tfNombre.getText();
    String direccion = tfDireccion.getText();
    String especialidad = tfEspecialidad.getText();
    String telefono = tfTelefono.getText();

    if(clave.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || especialidad.isEmpty()){
      datos = "VACIO";
    } else {

      try{
          int n = Integer.parseInt(telefono);
          datos = clave+"_"+nombre+"_"+direccion+"_"+especialidad+"_"+telefono;
      }
      catch(NumberFormatException nfe){
          datos = "NO_NUMERICO";
      }

    }

    return datos;
  }

  private void inactivarBotones(){
    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarClave.setEnabled(true);

    bActualizar.setEnabled(false);
    bCancelar.setEnabled(false);
  }

  private void activarBotones(){
    bCapturar.setEnabled(false);
    bConsultar.setEnabled(false);
    bConsultarClave.setEnabled(false);

    bActualizar.setEnabled(true);
    bCancelar.setEnabled(true);
  }

  private void desplegarDatos(String datos)
  {
      StringTokenizer st = new StringTokenizer(datos,"_");

      tfClave.setText(st.nextToken());
      tfNombre.setText(st.nextToken());
      tfDireccion.setText(st.nextToken());
      tfEspecialidad.setText(st.nextToken());
      tfTelefono.setText(st.nextToken());
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
          taDatos.setText(hospital.capturarDoctor(respuesta));
        }
      }
    }

    if(e.getSource() == bConsultar){
      taDatos.setText(hospital.consultarDoctores());
    }

    if(e.getSource() == bConsultarClave){

      String respuesta = hospital.consultarClaveDoctor(tfClave.getText());

      if (respuesta.equals("NOT_FOUND")){
        taDatos.setText("No se encontro la clave");
      } else {
        desplegarDatos(respuesta);
        activarBotones();
      }

    }

    if(e.getSource() == bActualizar){
      String respuesta = obtenerDatos();

      if(respuesta.equals("VACIO")){
        taDatos.setText("Algun Campo Esta Vacio");
      } else{
        if(respuesta.equals("NO_NUMERICO")){
          taDatos.setText("El telefono tiene que ser Numerico Con Max 9 Digitos");
        } else {
          taDatos.setText(hospital.actualizarDoctor(respuesta));
          inactivarBotones();
        }
      }
    }

    if(e.getSource() == bCancelar){
      inactivarBotones();
    }

  }
}
