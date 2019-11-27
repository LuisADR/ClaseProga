import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Busqueda extends JPanel implements ActionListener{

  //atributos siempre sin privados y van al inicio
  private String [] medicamentos, receta;
  private JButton bCrear;

  //Cosntructor
  public Busqueda(){
    bCrear = new JButton("Crear receta");
    medicamentos = new String[] {"Next", "Ibuprofeno", "Vick"}; //Todas las variables se crean/inician en el constructor

    bCrear.addActionListener(this);

    this.add(bCrear);
  }

  private void crearReceta(){
    int lenght = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de medicamentos"));
    String[] datos = new String[lenght];

    for (int i = 0;i < lenght ; i++) {
      datos[i] = JOptionPane.showInputDialog("Medicamento " + i);
    }

    receta = datos;
  }

  //Mutator

  //set
  public void setMedicamentos(String [] datos){
    medicamentos = datos;
  }

  //get
  public String[] getMedicamentos(){
    return this.medicamentos;
  }

  public String[] getReceta(){
    return this.receta;
  }

  public JPanel getPanel(){
    return this;
  }

  //Eventos
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == bCrear){
      crearReceta();
    }
  }


}
