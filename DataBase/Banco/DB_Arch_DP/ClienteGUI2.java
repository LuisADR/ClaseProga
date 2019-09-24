import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.util.StringTokenizer;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ClienteGUI2 extends JFrame implements ActionListener
{
    private JTextField tfCuenta, tfNombre, tfTipo, tfSaldo;
    private JButton    bCapturar, bConsultar, bSalir;
    private JButton    bConsultarNocta, bConsultarTipo;
    private JButton    bConsultarRet, bConsultarDep;
    private JButton    bTransferencia, bConsultarTrans;
    private JButton    bDeposito, bRetiro, bCancelarTrans;
    private JPanel     panel1, panel2;
    private JTextArea  taDatos;

    private DateFormat dateFormat;
    private Date date;

    private BancoADjdbc bancoad= new BancoADjdbc();

    public ClienteGUI2()
    {
        super("Admon. de Clientes");

        // 1. Crear los objetos de los atributos
        tfCuenta = new JTextField();
        tfNombre = new JTextField();
        tfTipo   = new JTextField();
        tfSaldo  = new JTextField();

        bCapturar = new JButton("Capturar datos");
        bConsultar = new JButton("Consultar Clientes");

        bConsultarNocta = new JButton("Consultar Cuenta");
        bConsultarTipo = new JButton("Consultar Tipo");

        bTransferencia = new JButton("Transferencia");
        bConsultarTrans = new JButton("Consultar Trans");

        bDeposito = new JButton("Deposito");
        bConsultarDep = new JButton("Consultar Dep");

        bRetiro = new JButton("Retiro");
        bConsultarRet = new JButton("Consultar Ret");

        bCancelarTrans = new JButton("Cancelar Transaccion");
        dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");

        bSalir = new JButton("Exit");
        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(10,35);

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bSalir.addActionListener(this);

        bConsultarTipo.addActionListener(this);
        bConsultarNocta.addActionListener(this);

        bTransferencia.addActionListener(this);
        bConsultarTrans.addActionListener(this);

        bDeposito.addActionListener(this);
        bConsultarDep.addActionListener(this);

        bRetiro.addActionListener(this);
        bConsultarRet.addActionListener(this);
        bCancelarTrans.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(11,2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(new JLabel("No. DE CUENTA"));
        panel1.add(tfCuenta);
        panel1.add(new JLabel("NOMBRE: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("TIPO DE CUENTA"));
        panel1.add(tfTipo);
        panel1.add(new JLabel("SALDO"));
        panel1.add(tfSaldo);

        panel1.add(bCapturar);
        panel1.add(bConsultar);

        panel1.add(bConsultarNocta);
        panel1.add(bConsultarTipo);

        panel1.add(bDeposito);
        panel1.add(bConsultarDep);

        panel1.add(bRetiro);
        panel1.add(bConsultarRet);

        panel1.add(bTransferencia);
        panel1.add(bConsultarTrans);

        panel1.add(bCancelarTrans);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        //Desabilitamos los botones
        bDeposito.setEnabled(false);
        bRetiro.setEnabled(false);
        bTransferencia.setEnabled(false);
        bCancelarTrans.setEnabled(false);

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(450,500);
        setVisible(true);


    }

    private String obtenerDatos()
    {
        date = new Date();
        String datos;

        String nocta  = tfCuenta.getText();
        String nombre = tfNombre.getText();
        String tipo   = tfTipo.getText();
        String saldo  = tfSaldo.getText();

        if(nocta.equals("") || nombre.isEmpty() || tipo.equals("") || saldo.isEmpty())
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(saldo);
                datos = nocta+"_"+nombre+"_"+tipo+"_"+saldo+"_"+dateFormat.format(date);
            }
            catch(NumberFormatException nfe)
            {
                datos = "NO_NUMERICO";
            }
        }

        return datos;
    }

    private void enableButtons(boolean action){

      bCapturar.setEnabled(!action);
      bConsultar.setEnabled(!action);
      bConsultarNocta.setEnabled(!action);
      bConsultarTipo.setEnabled(!action);

      bDeposito.setEnabled(action);
      bRetiro.setEnabled(action);
      bCancelarTrans.setEnabled(action);
      bTransferencia.setEnabled(action);
    }

    public void actionPerformed(ActionEvent e)
    {
        String datos = "", respuesta = "";

        if(e.getSource() == bCapturar)
        {
            // 1. Obtener datos
            datos = obtenerDatos();

            // 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
                respuesta = "Algun campo esta vacio...";
            else
                if(datos.equals("NO_NUMERICO"))
                    respuesta = "Saldo debe ser numerico...";
                else
                    respuesta = bancoad.capturar("cliente", datos);
                    //respuesta = datos;

            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }

        if(e.getSource() == bConsultar)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultaGeneral();

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarDep)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultaDep();

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarRet)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultaRet();

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarNocta)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultarNocta(tfCuenta.getText());
            System.out.print(datos);
            if (!datos.equals("NULL")) enableButtons(true);
            if (datos.equals("NULL")) datos = "No se encontro el No de cuenta";

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarTipo)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultarTipo(tfTipo.getText());

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bCancelarTrans)
        {
            //Desabilitamos los botones
            enableButtons(false);
        }

        if(e.getSource() == bDeposito)
        {
          datos = bancoad.depositar(Float.parseFloat(JOptionPane.showInputDialog("Cantidad a depositar")));

          taDatos.setText(datos);

          enableButtons(true);
        }

        if(e.getSource() == bRetiro)
        {
          datos = bancoad.retiro(Float.parseFloat(JOptionPane.showInputDialog("Cantidad a retirar")));

          taDatos.setText(datos);

          enableButtons(true);
        }

        if(e.getSource() == bTransferencia)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.trans(
              JOptionPane.showInputDialog("No a transferir"),
              Float.parseFloat(JOptionPane.showInputDialog("Cantidad a transferir"))
            );

            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new ClienteGUI2();
    }
}
