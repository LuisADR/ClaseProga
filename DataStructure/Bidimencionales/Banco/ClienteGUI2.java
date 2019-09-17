import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

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
    private JButton    bConsultarArreglo, bDatosArreglo;
    private JButton    bConsultarArregloObj, bDatosArregloObj;
    private JButton    bConsultarTC, bConsultarTCObj;
    private JButton    bConsultarCuenta, bConsultarCuentaObj;
    private JButton    bDeposito, bRetiro;
    private JButton    bConsultarRet, bConsultarDep;
    private JButton    barregloObjDatos;
    private JPanel     panel1, panel2;
    private JTextArea  taDatos;

    private DateFormat dateFormat;
    private Date date;

    private BancoAD bancoad= new BancoAD();

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

        bDatosArreglo = new JButton("Datos-->Arreglo");
        bConsultarArreglo = new JButton("Consultar arreglo");

        bDatosArregloObj = new JButton("Datos Archivo-->Arreglo Obj");
        bConsultarArregloObj = new JButton("Consultar Arreglo--Obj");

        bConsultarTC= new JButton("Consultar Tipo de Cuenta");
        bConsultarTCObj= new JButton("Consultar Tipo de Cuenta->Objeto");

        bConsultarCuenta= new JButton("Consultar No de Cuenta");
        bConsultarCuentaObj= new JButton("Consultar No de Cuenta->Objeto");

        bDeposito= new JButton("Depositar Cuenta");
        bRetiro= new JButton("Retirar Cuenta");

        bConsultarDep = new JButton("Consultar Depositos");
        bConsultarRet = new JButton("Consultar Retiros");

        barregloObjDatos = new JButton("Arreglo Obj-->Datos Archivo");
        bSalir = new JButton("Exit");

        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(10,30);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bSalir.addActionListener(this);

        bDatosArreglo.addActionListener(this);
        bConsultarArreglo.addActionListener(this);

        bDatosArregloObj.addActionListener(this);
        bConsultarArregloObj.addActionListener(this);

        bConsultarTC.addActionListener(this);
        bConsultarTCObj.addActionListener(this);

        bConsultarCuenta.addActionListener(this);
        bConsultarCuentaObj.addActionListener(this);

        bDeposito.addActionListener(this);
        bRetiro.addActionListener(this);

        bConsultarDep.addActionListener(this);
        bConsultarRet.addActionListener(this);

        barregloObjDatos.addActionListener(this);
        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(14,2));
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

        panel1.add(bDatosArreglo);
        panel1.add(bConsultarArreglo);

        panel1.add(bDatosArregloObj);
        panel1.add(bConsultarArregloObj);

        panel1.add(bConsultarTC);
        panel1.add(bConsultarTCObj);

        panel1.add(bConsultarCuenta);
        panel1.add(bConsultarCuentaObj);

        panel1.add(bDeposito);
        panel1.add(bRetiro);

        panel1.add(barregloObjDatos);
        panel1.add(bConsultarDep);

        panel1.add(bConsultarRet);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));



        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(600,650);
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

    public void actionPerformed(ActionEvent e)
    {
        String datos, respuesta;

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
                    respuesta = bancoad.capturar(datos, false);
                    //respuesta = datos;

            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }

        if(e.getSource() == bConsultar)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultarClientes();
            //datos="Consultar datos del archivo";
            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bDatosArreglo){

            // 1. Realizar consulta de clientes
            respuesta = bancoad.datosArchArreglos();
            // 2. Desplegar datos
            taDatos.setText(respuesta);
        }

        if(e.getSource() == bConsultarArreglo){

            // 1. Realizar consulta de clientes
            datos = bancoad.consultarArreglo();
            // 2. Desplegar datos
            taDatos.setText(datos);
        }

        if(e.getSource() == bDatosArregloObj){
          respuesta = bancoad.datosArchArregloObj();

          taDatos.setText(respuesta);
        }

        if(e.getSource() == bConsultarArregloObj){
          datos = bancoad.consultarArregloObj();

          taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarTC){
          datos = bancoad.consultarTC(tfTipo.getText());

          taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarTCObj){
          datos = bancoad.consultarTCObj(tfTipo.getText());

          taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarCuenta){
          datos = bancoad.consultarCuenta(tfCuenta.getText());

          taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarCuentaObj){
          datos = bancoad.consultarCuentaObj(tfCuenta.getText());

          taDatos.setText(datos);
        }

        if(e.getSource() == bDeposito){
          datos = bancoad.depositar(tfCuenta.getText(), Integer.parseInt(JOptionPane.showInputDialog("Cantidad a depositar")));

          taDatos.setText(datos);
        }

        if(e.getSource() == bRetiro){
          datos = bancoad.retirar(tfCuenta.getText(), Integer.parseInt(JOptionPane.showInputDialog("Cantidad a retira")));

          taDatos.setText(datos);
        }

        if(e.getSource() == barregloObjDatos)
        {
            datos = bancoad.arregloObjetosDatos();

            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarDep)
        {
            datos = bancoad.consultarDep();

            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarRet)
        {
            datos = bancoad.consultarRet();

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
