import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.io.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MatrizGUI extends JFrame implements ActionListener
{
    private JMenuBar  menuBarOpciones;
    private JMenu     menu;
    private JMenuItem miSumar, miRestar, miMultiplicar, miSalir;

    private JPanel    panel, panelA, panelB, panelC;
    private JButton   bSumar, bRestar, bMultiplicar;

    private JTextField tfMatrizA[][];
    private JTextField tfMatrizB[][];
    private JTextField tfMatrizC[][];

    private int matrizA[][];
    private int matrizB[][];
    private int matrizC[][];

    private int renglones, columnas;


    public MatrizGUI()
    {
        super("ALGEBRA DE MATRICES");

        // 1. Crear objetos
        menuBarOpciones = new JMenuBar();
        menu            = new JMenu("OPERACIONES");
        miSumar         = new JMenuItem("Suma  de Matrices");
        miRestar        = new JMenuItem("Resta de Matrices");
        miMultiplicar   = new JMenuItem("Multiplicacion de Matrices");
        miSalir         = new JMenuItem("Exit");
        bSumar          = new JButton("Sumar  C = A + B");
        bRestar         = new JButton("Restar C = A - B");
        bMultiplicar    = new JButton("Multiplicar C = A x B");

        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        panel  = new JPanel();

        // Adicionar ActionLisener a los botones
        bSumar.addActionListener(this);
        bRestar.addActionListener(this);
        bMultiplicar.addActionListener(this);


        // Adicionar listener a los menu items
        miSumar.addActionListener(this);
        miRestar.addActionListener(this);
        miMultiplicar.addActionListener(this);
        miSalir.addActionListener(this);

        // 2. Adicionar JMenuItems a JMenu
        menu.add(miSumar);
        menu.add(miRestar);
        menu.add(miMultiplicar);
        menu.add(miSalir);

        // 3. Adicionar JMenu a JMenuBar
        menuBarOpciones.add(menu);

        // 4. Adicionar JMenuBar al JFrame
        setJMenuBar(menuBarOpciones);

        setSize(300,300);
        setVisible(true);
    }

    private void dibujarMatricesSumaResta(int r, int c)
    {
        //JOptionPane.showMessageDialog(null,"Desplegar Panels para Sumar o Restar Matrices...");
        // 1. Crear los Layouts de los Panels
        panelA.setLayout(new GridLayout(r,c));
        panelB.setLayout(new GridLayout(r,c));
        panelC.setLayout(new GridLayout(r,c));
        panel.setLayout(new FlowLayout());

        // 2. Crear tfMatrices de los JTextFields
        tfMatrizA = new JTextField[r][c];
        tfMatrizB = new JTextField[r][c];
        tfMatrizC = new JTextField[r][c];

        // 3. Relacionar tfMatrices con panels: Crear los JTextFields de las casillas correspondientes de las Matrices A, B y C. Adicionar cada casilla de la Matriz en el Panel correspondiente
        for(int i = 0; i < r; i++){
          for(int j = 0; j < c; j++){
            tfMatrizA[i][j] = new JTextField(2);
            tfMatrizB[i][j] = new JTextField(2);
            tfMatrizC[i][j] = new JTextField(2);
            panelA.add(tfMatrizA[i][j]);
            panelB.add(tfMatrizB[i][j]);
            panelC.add(tfMatrizC[i][j]);
          }
        }

        // 4. Adicionar panels A, B, y C al panel de visualizacion
        panel.add(new JLabel("Matriz A"));
        panel.add(panelA);
        panel.add(new JLabel("Matriz B"));
        panel.add(panelB);
        panel.add(new JLabel("Matriz C"));
        panel.add(panelC);

        // 5. Adicionar los botones de Sumar y Restar al panel
        panel.add(bMultiplicar);

        // 6. Adicionar el panel de visualizacion al JFrame
        this.add(panel);
        setVisible(true);
    }


    private void dibujarMatricesMultiplica(int r, int c)
    {
      // 1. Crear los Layouts de los Panels
      panelA.setLayout(new GridLayout(r,c));
      panelB.setLayout(new GridLayout(c,r));
      panelC.setLayout(new GridLayout(r,r));
      panel.setLayout(new FlowLayout());

      // 2. Crear tfMatrices de los JTextFields
      tfMatrizA = new JTextField[r][c];
      tfMatrizB = new JTextField[c][r];
      tfMatrizC = new JTextField[r][r];

      // 3. Relacionar tfMatrices con panels: Crear los JTextFields de las casillas correspondientes de las Matrices A, B y C. Adicionar cada casilla de la Matriz en el Panel correspondiente
      for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
          tfMatrizA[i][j] = new JTextField(2);
          panelA.add(tfMatrizA[i][j]);
        }
      }

      for(int i = 0; i < c; i++){
        for(int j = 0; j < r; j++){
          tfMatrizB[i][j] = new JTextField(2);
          panelB.add(tfMatrizB[i][j]);
        }
      }

      for(int i = 0; i < r; i++){
        for(int j = 0; j < r; j++){
          tfMatrizC[i][j] = new JTextField(2);
          panelC.add(tfMatrizC[i][j]);
        }
      }

      // 4. Adicionar panels A, B, y C al panel de visualizacion
      panel.add(new JLabel("Matriz A"));
      panel.add(panelA);
      panel.add(new JLabel("Matriz B"));
      panel.add(panelB);
      panel.add(new JLabel("Matriz C"));
      panel.add(panelC);

      // 5. Adicionar los botones de Sumar y Restar al panel
      panel.add(bSumar);
      panel.add(bRestar);

      // 6. Adicionar el panel de visualizacion al JFrame
      this.add(panel);
      setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String strMatriz="";

        if(e.getSource() == miSumar)
        {
            renglones = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de renglones ="));
            columnas = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de columnas ="));

            dibujarMatricesSumaResta(renglones, columnas);
        }

        if(e.getSource() == miRestar)
        {
            renglones = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de renglones ="));
            columnas = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de columnas ="));

            dibujarMatricesSumaResta(renglones, columnas);

        }

        if(e.getSource() == miMultiplicar)
        {
            renglones = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de renglones ="));
            columnas = Integer.parseInt(JOptionPane.showInputDialog("Orden de Matrices\nNo. de columnas ="));

            dibujarMatricesMultiplica(renglones, columnas);

        }

        if(e.getSource() == bSumar)
        {
            JOptionPane.showMessageDialog(null,"Sumar Matrices...");
        }

        if(e.getSource() == bRestar)
        {
            JOptionPane.showMessageDialog(null,"Restar Matrices...");        }

        if(e.getSource() == bMultiplicar)
        {
            JOptionPane.showMessageDialog(null,"Multiplicar Matrices...");
        }

        if(e.getSource() == miSalir)
            System.exit(0);
    }

    public static void main(String args[])
    {
        MatrizGUI matriz = new MatrizGUI();
    }

}
