import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.StringTokenizer;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI2 extends JFrame implements ActionListener
{
    private JTextField tfCuenta, tfNombre, tfTipo, tfSaldo;
    private JButton    bCapturar, bConsultar, bSalir;
    private JButton    bConsultarTipo, bConsultarCuenta;
    private JButton    bDepositar, bRetirar, bCancelar;
    private JPanel     panel1, panel2;
    private JTextArea  taDatos;

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
        bSalir = new JButton("Exit");

        bConsultarTipo = new JButton("Consultar Tipo");
        bConsultarCuenta = new JButton("Consultar Cuenta");

        bDepositar = new JButton("Depositar");
        bRetirar = new JButton("Retirar");
        bCancelar = new JButton("Cancelar");

        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(10,30);

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bSalir.addActionListener(this);

        bConsultarTipo.addActionListener(this);
        bConsultarCuenta.addActionListener(this);

        bDepositar.addActionListener(this);
        bRetirar.addActionListener(this);
        bCancelar.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(8,2));
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

        panel1.add(bConsultarTipo);
        panel1.add(bConsultarCuenta);

        panel1.add(bDepositar);
        panel1.add(bRetirar);

        panel1.add(bCancelar);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        bDepositar.setEnabled(false);
        bRetirar.setEnabled(false);
        bCancelar.setEnabled(false);


        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(400,400);
        setVisible(true);
    }

    private String obtenerDatos()
    {
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
                datos = nocta+"_"+nombre+"_"+tipo+"_"+saldo;
            }
            catch(NumberFormatException nfe)
            {
                datos = "NO_NUMERICO";
            }
        }

        return datos;
    }

    private void inactivarBotones(){
      bCapturar.setEnabled(false);
      bConsultar.setEnabled(false);
      bConsultarTipo.setEnabled(false);
      bConsultarCuenta.setEnabled(false);

      bDepositar.setEnabled(true);
      bRetirar.setEnabled(true);
      bCancelar.setEnabled(true);
    }

    private void activarBotones(){
      bCapturar.setEnabled(true);
      bConsultar.setEnabled(true);
      bConsultarTipo.setEnabled(true);
      bConsultarCuenta.setEnabled(true);

      bDepositar.setEnabled(false);
      bRetirar.setEnabled(false);
      bCancelar.setEnabled(false);
    }

    private String deplegar (String datos){
      StringTokenizer str = new StringTokenizer("_");
      tfCuenta.setText(str.nextToken());
      tfNombre.setText(str.nextToken());
      tfTipo.setText(str.nextToken());
      tfSaldo.setText(str.nextToken());
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
                    respuesta = bancoad.capturar(datos);
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

        if(e.getSource() == bConsultarCuenta) {

          // 1. Realizar consulta de clientes
          datos = bancoad.consultarClientes();

          if(datos.equals("NOT_FOUND")){
            // 2. Desplegar datos
            taDatos.setText("No se localizo la cuenta");
          } else {
            taDatos.setText(datos);
            inactivarBotones();
          }
        }

        if(e.getSource() == bDepositar){
          //Obtener el numero de cuenta y la cantidad a depositar
          //Realizar el deposito
          //Desplegar resultado de la transaccion
        }

        if(e.getSource() == bCancelar){
          activarBotones();
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
