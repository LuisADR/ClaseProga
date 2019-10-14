import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class ClienteGUI extends JFrame implements ActionListener
{
    // Atributos
    private JTextField tfNocta, tfNombre, tfTipo, tfSaldo;
    private JButton    bCapturar, bConsultar, bConsultarNocta, bConsultarTipo, bSalir;
    private JButton    bDepositar, bRetirar, bConsultarDepositos, bConsultarRetiros, bCancelar;

    private JTextArea  taDatos;
    private JPanel     panel1, panel2;

    private JComboBox comboCuentas;
    private String opcionesCuenta[] = {"INVERSION","CREDITO","AHORRO","HIPOTECA"};

    private BancoADLL bancoad = new BancoADLL();

    // Constructor
    public ClienteGUI()
    {
        super("Bancomer Linked Lists");

        // 1. Crear los objetos de los atributos
        tfNocta   = new JTextField();
        tfNombre  = new JTextField();
        tfTipo    = new JTextField();
        tfSaldo   = new JTextField();

        bCapturar           = new JButton("Capturar datos (LList)");
        bConsultar          = new JButton("Consultar Clientes (LList)");
        bConsultarNocta     = new JButton("Consultar No. Cuenta (LList)");
        bConsultarTipo      = new JButton("Consultar Tipo Cuenta (LList)");
        bDepositar          = new JButton("Depositos (LList)");
        bRetirar            = new JButton("Retiros (LList)");
        bCancelar           = new JButton("Cancelar Transaccion (LList)");
        bConsultarDepositos = new JButton("Consultar Despositos (LList)");
        bConsultarRetiros   = new JButton("Consultar Retiros (LList)");
        bSalir              = new JButton("Salir");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarNocta.addActionListener(this);
        bConsultarTipo.addActionListener(this);
        bDepositar.addActionListener(this);
        bRetirar.addActionListener(this);
        bCancelar.addActionListener(this);
        bConsultarDepositos.addActionListener(this);
        bConsultarRetiros.addActionListener(this);
        bSalir.addActionListener(this);

        bDepositar.setEnabled(false);
        bRetirar.setEnabled(false);
        bConsultarDepositos.setEnabled(false);
        bConsultarRetiros.setEnabled(false);
        bCancelar.setEnabled(false);

        comboCuentas = new JComboBox(opcionesCuenta);

        taDatos = new JTextArea(11,25);

        panel1 = new JPanel();
        panel2 = new JPanel();

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(9,2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(new JLabel("No. DE CUENTA"));
        panel1.add(tfNocta);
        panel1.add(new JLabel("NOMBRE: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("TIPO DE CUENTA"));
        //panel1.add(tfTipo);
        panel1.add(comboCuentas);
        panel1.add(new JLabel("SALDO"));
        panel1.add(tfSaldo);
        panel1.add(bCapturar);
        panel1.add(bConsultar);

        panel1.add(bConsultarNocta);
        panel1.add(bConsultarTipo);
        panel1.add(bDepositar);
        panel1.add(bRetirar);
        panel1.add(bConsultarDepositos);
        panel1.add(bConsultarRetiros);
        panel1.add(bCancelar);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        add(panel2);
        setSize(500,500);
        setVisible(true);
    }

    private String obtenerDatos()
    {
        String datos = "";

        String nocta  = tfNocta.getText();
        String nombre = tfNombre.getText();
        //String tipo   = tfTipo.getText();
        String tipo   = (String)comboCuentas.getSelectedItem();
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

    private void desplegarDatos(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        tfNocta.setText(st.nextToken());
        tfNombre.setText(st.nextToken());
        tfTipo.setText(st.nextToken());
        tfSaldo.setText(st.nextToken());
    }

    private void activarBotones()
    {
        bDepositar.setEnabled(true);
        bRetirar.setEnabled(true);
        bConsultarDepositos.setEnabled(true);
        bConsultarRetiros.setEnabled(true);
        bCancelar.setEnabled(true);

        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarNocta.setEnabled(false);
        bConsultarTipo.setEnabled(false);
    }

    private void inactivarBotones()
    {
        bDepositar.setEnabled(false);
        bRetirar.setEnabled(false);
        bConsultarDepositos.setEnabled(false);
        bConsultarRetiros.setEnabled(false);
        bCancelar.setEnabled(false);

        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarNocta.setEnabled(true);
        bConsultarTipo.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String datos="", respuesta="", resultado="";

        if(e.getSource() == bCapturar)
        {
            // 1. obtener datos de los TextFields
            datos = obtenerDatos();

            if(datos.equals("VACIO"))
                taDatos.setText("Algun campo esta vacio...");
            else
                if(datos.equals("NO_NUMERICO"))
                    taDatos.setText("Saldo debe ser numerico...");
                else
                {
                    // 2. Capturar datos: Crear Nuevo Nodo ClienteDP en Lista Enlazada
                    respuesta = bancoad.capturar(datos);

                    // 3. Desplegar resultado de la captura
                    //taDatos.setText("Crear Nodo en Lista: "+datos);
                    taDatos.setText(respuesta);
                }
        }

        if(e.getSource() == bConsultar)
        {
            // 1. Consultar Lista Enlazada de Objetos ClienteDP
            datos = bancoad.consultar();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarNocta)
        {
            // 1. Leer clave del tfClave
            String ncta = tfNocta.getText();

            // 2.Consultar No. de Cuenta en los Nodos de la Lista Enlazada
            datos = bancoad.consultarNocta(ncta);

            // 3. Desplegar la respuesta
            if(datos.equals("LISTA_VACIA"))
                taDatos.setText("Lista de Clientes vacia...");
            else
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("No. de Cuenta no localizado...");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    activarBotones();
                }
        }

        if(e.getSource() == bConsultarTipo)
        {
            // 1. Obtner de tfTipo el tipo de cuenta a buscar en los Nodos de la Lista Enlazada
            //String tcta = tfTipo.getText();
            String tcta   = (String)comboCuentas.getSelectedItem();

            // 2.Consultar el tipo de cuenta en los Nodos de la Lista Enlazada
            datos = bancoad.consultarTipo(tcta);

            // 3. Desplegar la respuesta
            taDatos.setText(datos);
            taDatos.setText(tcta);
        }

        if(e.getSource() == bDepositar)
        {
            try
            {
                // 1. Pedir cantidad a depositar al usuario
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a depositar ="));

                // 2. Depositar la cantidad en el Saldo del Nodo correspondiente en la LList
                resultado = bancoad.depositar(cantidad);

                // 3. Desplegar resultado de la captura
                taDatos.setText(resultado);

                inactivarBotones();
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("La Cantidad a depositar debe ser numerica...");
            }
        }

        if(e.getSource() == bRetirar)
        {
            try
            {
                // 1. Pedir cantidad a retirar al usuario
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a retirar ="));

                // 2. Retirar la cantidad en el Saldo del Nodo correspondiente en la LList
                resultado = bancoad.retirar(cantidad);

                // 3. Desplegar resultado de la captura
                taDatos.setText(resultado);

                inactivarBotones();
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("La Cantidad a retirar debe ser numerica...");
            }
        }

        if(e.getSource() == bCancelar)
        {
            inactivarBotones();
        }

        if(e.getSource() == bConsultarDepositos)
        {
            // 1. Consultar la Lista Enlazada de Depositos
            datos = bancoad.consultarDepositos();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);

            //inactivarBotones();
        }

        if(e.getSource() == bConsultarRetiros)
        {
            // 1. Consultar la Lista Enlazada de Retiros
            datos = bancoad.consultarRetiros();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);

            //inactivarBotones();
        }

        if(e.getSource() == bSalir){
          bancoad.listaClienteDatosArchivo();
          bancoad.listaRetiroDatosArchivo();
          bancoad.listaDepositosDatosArchivo();
          System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new ClienteGUI();
    }
}
