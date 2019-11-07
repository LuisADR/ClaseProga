import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.io.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HospitalGUI extends JFrame implements ActionListener
{
    private JMenuBar  menuBarOpciones;
    private JMenu     menuRegistro, menuConsultas;

    //Registro
    private JMenu     miDoctor, miPacientes, miAnalisis;
    private JMenuItem doctor, paciente, analisis, asignar;

    //Consulta
    private JMenuItem miDoctorPacientes, miPacientesDoctor, miAnalisisConsulta;
    private JPanel panel1;

    public HospitalGUI(){

        super("Sistema Hospital");

        // 1. Crear objetos De Registro
        menuBarOpciones = new JMenuBar();
        menuRegistro = new JMenu("Registro");
        menuConsultas = new JMenu("Consultas");
        panel1 = new JPanel();
        asignar = new JMenuItem("Asignar Doctor");

        //Doctor
        miDoctor = new JMenu("Doctor");

        doctor = new JMenuItem("Registrar / Editar Doctor");

        //Paciente
        miPacientes = new JMenu("Paciente");

        paciente = new JMenuItem("Registrar / Editar Paciente");

        //Analisis
        miAnalisis = new JMenu("Analisis");

        analisis = new JMenuItem("Registrar / Editar Analisis");

        //Consultas
        miPacientesDoctor = new JMenuItem("Consultar Pacientes");
        miDoctorPacientes = new JMenuItem("Consultar Doctores");
        miAnalisisConsulta = new JMenuItem("Consultar Analisis");

        //Adicionar Listener
        doctor.addActionListener(this);

        paciente.addActionListener(this);

        analisis.addActionListener(this);

        asignar.addActionListener(this);

        miPacientesDoctor.addActionListener(this);

        miDoctorPacientes.addActionListener(this);

        miAnalisisConsulta.addActionListener(this);

        // 2. Adicionar JMenuItems a JMenu

        panel1.setLayout(new FlowLayout());

        miDoctor.add(doctor);

        miPacientes.add(paciente);
        miPacientes.add(asignar);

        miAnalisis.add(analisis);

        menuRegistro.add(miDoctor);
        menuRegistro.add(miPacientes);
        menuRegistro.add(miAnalisis);

        menuConsultas.add(miPacientesDoctor);
        menuConsultas.add(miDoctorPacientes);
        menuConsultas.add(miAnalisisConsulta);

        // 3. Adicionar JMenu a JMenuBar
        menuBarOpciones.add(menuRegistro);
        menuBarOpciones.add(menuConsultas);

        // 4. Adicionar JMenuBar al JFrame
        setJMenuBar(menuBarOpciones);
        this.add(panel1);

        setSize(500,500);
        setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){


        if(e.getSource() == paciente){
          panel1.removeAll();
          panel1.add(new PacienteGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);

        }

        if(e.getSource() == doctor){
          panel1.removeAll();
          panel1.add(new DoctorGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);

        }

        if(e.getSource() == analisis){
          panel1.removeAll();
          panel1.add(new AnalisisGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);

        }

        if(e.getSource() == asignar){
          panel1.removeAll();
          panel1.add(new AsignarGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);

        }

        if(e.getSource() == miPacientesDoctor){
          panel1.removeAll();
          panel1.add(new ConsultaPacienteGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);
        }

        if(e.getSource() == miDoctorPacientes){
          panel1.removeAll();
          panel1.add(new ConsultaDoctorGUI());

          setSize(400,600);
        }

        if(e.getSource() == miAnalisisConsulta){
          panel1.removeAll();
          panel1.add(new ConsultaAnalisisGUI());
          panel1.revalidate();
          panel1.repaint();
          pack();
          setSize(400,600);
        }
    }

    public static void main(String args[]){

        HospitalGUI matriz = new HospitalGUI();
    }

}
