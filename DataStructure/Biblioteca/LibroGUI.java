import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import java.util.StringTokenizer;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibroGUI extends JFrame implements ActionListener
{
    private JTextField tfTitulo;
    private JTextField tfAutor;
    private JTextField tfEditorial;

    private JButton bCapturar, bConsultar, bConsultarEditorial, bEliminar, bSalir;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private LibroADList libroad = new LibroADList();

    public LibroGUI()
    {
        super("Biblioteca Tec");

        // 1. Crear objetos
        tfTitulo     = new JTextField();
        tfAutor      = new JTextField();
        tfEditorial  = new JTextField();

        bCapturar  = new JButton("Capturar datos");
        bConsultar = new JButton("Consultar Libros");
        bConsultarEditorial = new JButton("Consultar Editorial");
        bEliminar = new JButton("Eliminar Nodo");
        bSalir     = new JButton("Salir");

        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarEditorial.addActionListener(this);
        bSalir.addActionListener(this);
        bEliminar.addActionListener(this);

        taDatos    = new JTextArea(8,25);

        panel1 = new JPanel();
        panel2 = new JPanel();

        // 2. Adicionar los objetos a panel1 y panel2
        panel1.setLayout(new GridLayout(7,2));
        panel2.setLayout(new FlowLayout());

        panel1.add(new JLabel("TITULO: "));
        panel1.add(tfTitulo);

        panel1.add(new JLabel("AUTOR: "));
        panel1.add(tfAutor);

        panel1.add(new JLabel("EDITORIAL: "));
        panel1.add(tfEditorial);

        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarEditorial);
        panel1.add(bEliminar);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));


        // 3. Adicionar panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(400,400);
        setVisible(true);

    }

    private String obtenerDatos()
    {
        String datos="";

        String titulo     = tfTitulo.getText();
        String autor      = tfAutor.getText();
        String editorial  = tfEditorial.getText();

        if(titulo.equals("") || autor.equals("") || editorial.equals(""))
            datos = "VACIO";
        else
        {
            datos = titulo+"_"+autor+"_"+editorial;
        }

        return datos;
    }

    private void editar(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        tfTitulo.setText(st.nextToken());
        tfAutor.setText(st.nextToken());
        tfEditorial.setText(st.nextToken());
    }

    public void actionPerformed(ActionEvent e)
    {
        String datos="";
        String respuesta="";

        if(e.getSource() == bCapturar)
        {
            datos = obtenerDatos();

            if(datos.equals("VACIO"))
                taDatos.setText("Algun campo esta vacio...");
            else
            {
                respuesta = libroad.capturar(datos);
                taDatos.setText(respuesta);
            }
        }

        if(e.getSource() == bConsultar)
        {
            datos = libroad.consultar();

            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarEditorial)
        {
            String edit = tfEditorial.getText();
            datos = libroad.consultarEditorial(edit);

            taDatos.setText(datos);
        }

        if(e.getSource() == bEliminar){
          respuesta = libroad.eliminarNodo(tfTitulo.getText());
          taDatos.setText(respuesta);
        }

        if(e.getSource() == bSalir)
            System.exit(0);
    }

    public static void main(String args[])
    {
        LibroGUI libroiug = new LibroGUI();
    }
}
