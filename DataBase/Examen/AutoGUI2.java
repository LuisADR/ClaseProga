import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class AutoGUI2 extends JFrame implements ActionListener
{
    private JTextField tfClave, tfMarca, tfTipo, tfPrecio;
    private JButton    bCapturar, bConsultar, bConsultarClave, bConsultarMarca, bCotizar, bSalir;
    private JButton    bVenta, bConsultarVentas, bCancelar;
    private JTextArea  taDatos;
    private JPanel     panel1, panel2;

    private AutosADjdbc autosadjdbc;

    //private AutoAD auto = new AutoAD();

    public AutoGUI2()
    {
        super("Catalogo de Autos");

        // Crear objetos
        tfClave  = new JTextField();
        tfMarca  = new JTextField();
        tfTipo   = new JTextField();
        tfPrecio = new JTextField();

        bCapturar  = new JButton("Capturar datos");
        bConsultar = new JButton("Consultar Autos");
        bConsultarClave = new JButton("Consultar Auto por Clave");
        bConsultarMarca = new JButton("Consultar Autos por Marca");
        bCotizar   = new JButton("Cotizar Auto");
        bVenta     = new JButton("Venta del Auto");
        bCancelar  = new JButton("Cancelar Transaccion");
        bConsultarVentas = new JButton("Consultar Ventas");
        bSalir     = new JButton("Salir");

        autosadjdbc = new AutosADjdbc();

        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarClave.addActionListener(this);
        bConsultarMarca.addActionListener(this);
        bCotizar.addActionListener(this);
        bVenta.addActionListener(this);
        bCancelar.addActionListener(this);
        bConsultarVentas.addActionListener(this);
        bSalir.addActionListener(this);

        bCotizar.setEnabled(false);
        bVenta.setEnabled(false);
        bCancelar.setEnabled(false);

        taDatos = new JTextArea(11,25);

        panel1 = new JPanel();
        panel2 = new JPanel();

        // Inicializar objetos
        panel1.setLayout(new GridLayout(9,2));
        panel2.setLayout(new FlowLayout());

        panel1.add(new JLabel("CLAVE"));
        panel1.add(tfClave);

        panel1.add(new JLabel("MARCA"));
        panel1.add(tfMarca);

        panel1.add(new JLabel("TIPO"));
        panel1.add(tfTipo);

        panel1.add(new JLabel("PRECIO"));
        panel1.add(tfPrecio);

        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarMarca);
        panel1.add(bConsultarClave);

        panel1.add(bCotizar);
        panel1.add(bVenta);
        panel1.add(bCancelar);
        panel1.add(bConsultarVentas);
        panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        add(panel2);
        setSize(600,500);
        setVisible(true);
    }

    private String obtenerDatos()
    {
        String datos = "";

        String clave  = tfClave.getText();
        String marca  = tfMarca.getText();
        String tipo   = tfTipo.getText();
        String precio = tfPrecio.getText();

        if(clave.equals("") ||  marca.equals("") || tipo.equals("") || precio.equals(""))
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(precio);
                datos = clave+"_"+marca+"_"+tipo+"_"+precio;
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

        tfClave.setText(st.nextToken());
        tfMarca.setText(st.nextToken());
        tfTipo.setText(st.nextToken());
        tfPrecio.setText(st.nextToken());
    }

    private void activarBotones()
    {
        bCotizar.setEnabled(true);
        bVenta.setEnabled(true);
        bCancelar.setEnabled(true);

        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarClave.setEnabled(false);
        bConsultarMarca.setEnabled(false);
    }

    private void inactivarBotones()
    {
        bCotizar.setEnabled(false);
        bVenta.setEnabled(false);
        bCancelar.setEnabled(false);

        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarClave.setEnabled(true);
        bConsultarMarca.setEnabled(true);
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
                    taDatos.setText("Precio debe ser numerico...");
                else
                {
                    // 2. Capturar datos desde AutosAD
                    //respuesta = auto.capturar(datos);
                    respuesta = autosadjdbc.capturar(datos);

                    // 3. Desplegar resultado de la captura
                    taDatos.setText(respuesta);
                }
        }

        if(e.getSource() == bConsultar)
        {
            // 1. ejecutar el metodo do consultar de AutosAD
            //datos = auto.consultar();
            datos = autosadjdbc.consultar();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarMarca)
        {
            // 1. ejecutar el metodo do consultar de AutosAD
            //datos = auto.consultar();
            datos = autosadjdbc.consultarMarca(tfMarca.getText());

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarClave)
        {
            // 1. Leer clave del tfClave
            String clave = tfClave.getText();

            // 2.Consultar la clave en AutosAD
            //datos = auto.consultarClave(clave);
            datos = autosadjdbc.consultarClave(tfClave.getText());

            // 3. Despleagr la respuesta
            if(datos.equals("NOT_FOUND"))
                taDatos.setText("Clave de Auto no localizada...");
            else
            {
                taDatos.setText(datos);
                desplegarDatos(datos);
                activarBotones();
            }
        }

        if(e.getSource() == bCotizar)
        {
            try
            {
                // 4. Desplegar resultado de la captura
                taDatos.setText(autosadjdbc.cotizar());
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("El Plazo de pago debe ser numerico...");
            }
        }

        if(e.getSource() == bVenta)
        {
            try
            {
                // 1. Pedir plazo de la cotizacion al usuario
                taDatos.setText(autosadjdbc.capturarVenta());

                inactivarBotones();
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("El Plazo de pago debe ser numerico...");
            }
        }

        if(e.getSource() == bCancelar)
        {
            inactivarBotones();
        }

        if(e.getSource() == bConsultarVentas)
        {
            // 1. ejecutar el metodo do consultar de AutosAD
            //datos = auto.consultarVentas();
            datos = autosadjdbc.consultarVentas();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new AutoGUI2();
    }
}
