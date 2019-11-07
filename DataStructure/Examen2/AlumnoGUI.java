import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class AlumnoGUI extends JFrame implements ActionListener
{
    // Atributos
    private JButton    bConsultarListaAlumnos, bCalcularPromedios, bSalir;
    private JButton    bGenerarListaPromedios, bConsultarListaPromedios;

    private JTextArea  taDatos;
    private JPanel     panel1, panel2;

    //private AlumnoADLL    alumnoad = new AlumnoADLL();
    private AlumnoADLList alumnoad = new AlumnoADLList();

    // Constructor
    public AlumnoGUI()
    {
        super("Linked Lists: Gestion de Alumnos");

        // 1. Crear los objetos de los atributos

        bConsultarListaAlumnos   = new JButton("Consultar (LList Alumnos)");
        bCalcularPromedios       = new JButton("Calcular Promedios (LList Alumnos)");
        bGenerarListaPromedios   = new JButton("Generar Lista Promedios (LList Promedios)");
        bConsultarListaPromedios = new JButton("Consultar Lista Promedios (LList Promedios)");
        bSalir                   = new JButton("Salir");

        // Adicionar addActionListener a lo JButtons
        bConsultarListaAlumnos.addActionListener(this);
        bCalcularPromedios.addActionListener(this);
        bGenerarListaPromedios.addActionListener(this);
        bConsultarListaPromedios.addActionListener(this);
        bSalir.addActionListener(this);

        taDatos = new JTextArea(15,40);

        panel1 = new JPanel();
        panel2 = new JPanel();

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(3,2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(bConsultarListaAlumnos);
        panel1.add(bCalcularPromedios);
        panel1.add(bGenerarListaPromedios);
        panel1.add(bConsultarListaPromedios);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        add(panel2);
        setSize(650,380);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String datos="", respuesta="", resultado="";

        if(e.getSource() == bConsultarListaAlumnos)
        {
            // 1. Consultar Lista Enlazada de Alumnos
            datos = alumnoad.consultar();

            // 2. Desplegar la datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bCalcularPromedios)
        {
            // 1. Calcular Promedios y actualizar el Promedio en la Lista Enlazada de Alumnos
            respuesta = alumnoad.calcularPromedios();

            // 2. Desplegar la respuesta
            taDatos.setText(respuesta);
        }

        if(e.getSource() == bGenerarListaPromedios)
        {
            // 1. Generar Lista de alumnos con un promedio >= 90
            respuesta = alumnoad.generarListaPromedios();

            // 2. Desplegar la respuesta
            taDatos.setText(respuesta);

        }

        if(e.getSource() == bConsultarListaPromedios)
        {
            // 1. Consultar Lista Enlazada de Alumnos con un promedio >= 90
            datos = alumnoad.consultarListaPromedios();

            // 2. Desplegar la datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new AlumnoGUI();
    }
}
