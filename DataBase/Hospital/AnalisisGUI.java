import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.StringTokenizer;

public class AnalisisGUI extends JPanel implements ActionListener{
  private JTextField tfPclave, tfTipo, tfFecha_aplicacion, tfFecha_entrega, tfDiagnostico, tfDescripcion;
  private JButton bCapturar, bConsultar, bConsultarClave, bActualizar, bCancelar;

  private JTextArea  taDatos;
  private JPanel     panel1, panel2;
  private HospitalAD hospital;

  public AnalisisGUI(){
    //Creamos los objetos
    panel1 = new JPanel();
    panel2 = new JPanel();
    taDatos = new JTextArea(11,30);
    hospital = new HospitalAD();

    //Creamos JTextField
    tfPclave = new JTextField();
    tfTipo = new JTextField();
    tfFecha_aplicacion = new JTextField();
    tfFecha_entrega = new JTextField();
    tfDiagnostico = new JTextField();
    tfDescripcion = new JTextField();

    //Creamos Botones
    bCapturar = new JButton("Capturar");
    bConsultar = new JButton("Consultar Analisis");
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
    panel1.add(new JLabel("Clave Paciente: "));
    panel1.add(tfPclave);

    panel1.add(new JLabel("Tipo Analisis: "));
    panel1.add(tfTipo);

    panel1.add(new JLabel("Fecha Aplicacion: "));
    panel1.add(tfFecha_aplicacion);

    panel1.add(new JLabel("Fecha Entrega: "));
    panel1.add(tfFecha_entrega);

    panel1.add(new JLabel("Diagnostico: "));
    panel1.add(tfDiagnostico);

    panel1.add(new JLabel("Descripcion: "));
    panel1.add(tfDescripcion);

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

    String clave = tfPclave.getText();
    String tipo = tfTipo.getText();
    String fecha_aplicacion = tfFecha_aplicacion.getText();
    String fecha_entrega = tfFecha_entrega.getText();
    String diagnostico = tfDiagnostico.getText();
    String descripcion = tfDescripcion.getText();

    if(clave.isEmpty() || tipo.isEmpty() || fecha_entrega.isEmpty() || fecha_aplicacion.isEmpty() ||
       diagnostico.isEmpty() || descripcion.isEmpty()){
      datos = "VACIO";
    } else {

        datos = clave+"_"+tipo+"_"+fecha_aplicacion+"_"+fecha_entrega+"_"+descripcion+"_"+diagnostico;

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

      tfPclave.setText(st.nextToken());
      tfTipo.setText(st.nextToken());
      tfFecha_aplicacion.setText(st.nextToken());
      tfFecha_entrega.setText(st.nextToken());
      tfDescripcion.setText(st.nextToken());
      tfDiagnostico.setText(st.nextToken());
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
          taDatos.setText(hospital.capturarAnalisis(respuesta));
        }
      }
    }

    if(e.getSource() == bConsultar){
      taDatos.setText(hospital.consultarAnalisis());
    }

    if(e.getSource() == bConsultarClave){

      String respuesta = hospital.consultarClaveAnalisis(tfPclave.getText());

      if (respuesta.equals("NOT_FOUND")){
        taDatos.setText("No se encontro la clave del paciente");
      } else {
        taDatos.setText(respuesta);
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
          taDatos.setText(hospital.actualizarAnalisis(respuesta));
          inactivarBotones();
        }
      }
    }

    if(e.getSource() == bCancelar){
      inactivarBotones();
    }
  }
}
