import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo extends JFrame implements ActionListener{

  //atributos siempre sin privados y van al inicio
  private Busqueda busqueda;
  private JPanel panel1;
  private JMenuBar mPrincipal;
  private JMenuItem miCrearDatos, miReceta, miMostrar, miCrearReceta;

  //Constructor
  public Ejemplo(){
    super("Ejemplo");
    //Creamos objetos
    busqueda = new Busqueda();
    mPrincipal = new JMenuBar();

    miCrearDatos = new JMenuItem("Cambiar datos");
    miMostrar = new JMenuItem("Mostrar medicamentos");
    miReceta = new JMenuItem("Mostrar receta");
    miCrearReceta = new JMenuItem("Crear receta");

    panel1 = new JPanel();

    //Agregamos action Listener
    miCrearDatos.addActionListener(this);
    miMostrar.addActionListener(this);
    miReceta.addActionListener(this);
    miCrearReceta.addActionListener(this);

    //Agregamos al menu
    mPrincipal.add(miCrearDatos);
    mPrincipal.add(miMostrar);
    mPrincipal.add(miReceta);
    mPrincipal.add(miCrearReceta);

    //Formato del JPanel;
    panel1.setLayout(new FlowLayout());

    //Damos estructura al JFrame
    this.add(mPrincipal);
    this.add(panel1);
    setJMenuBar(mPrincipal);

    setSize(500,500);
    setVisible(true);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void cambiarDatos(){
    int lenght = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de medicamentos"));
    String[] datos = new String[lenght];

    for (int i = 0;i < lenght ; i++) {
      datos[i] = JOptionPane.showInputDialog("Medicamento " + i);
    }

    //ejecutamos la funcion para cambiar los datos
    busqueda.setMedicamentos(datos);
  }

  public void actionPerformed(ActionEvent e){

    //Evento para "Cambiar Medicamento" <---------------------Uso de set
    if(e.getSource() == miCrearDatos){
      this.cambiarDatos();
    }

    //Evento para "Mostrar medicamentos" <---------------------Uso de get
    if(e.getSource() == miMostrar){
      String[] datos = busqueda.getMedicamentos();

      //Agregar al panel 1
      panel1.removeAll();
      for (int i = 0; i < datos.length ; i++) {
        this.panel1.add(new JLabel(datos[i]));
      }

      //Mostrar la informacion
      panel1.revalidate();
      panel1.repaint();
      pack();

    }

    //Evento para "crear Receta" <---------------------Uso JPanel
    if(e.getSource() == miCrearReceta){
      panel1.removeAll();
      panel1.add(busqueda.getPanel());
      panel1.revalidate();
      panel1.repaint();
      pack();
    }

    //Evento para "Mostrar receta"
    if(e.getSource() == miReceta){
      String[] datos = busqueda.getReceta();

      //Agregar al panel 1
      panel1.removeAll();
      for (int i = 0; i < datos.length ; i++) {
        this.panel1.add(new JLabel(datos[i]));
      }

      //Mostrar la informacion
      panel1.revalidate();
      panel1.repaint();
      pack();
    }
  }

  public static void main(String[] args){
    new Ejemplo();
  }
}
