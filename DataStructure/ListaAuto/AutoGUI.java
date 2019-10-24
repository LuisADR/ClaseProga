import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class AutoGUI extends JFrame implements ActionListener
{
    private JTextField tfClave, tfMarca, tfTipo, tfPrecio;
    private JButton    bCapturar, bConsultar, bConsultarClave, bConsultarMarca, bCotizar, bSalir;
    private JButton bConsultarArrObj, bVenta, bConsultarVentas, bCancelar;
    private JTextArea  taDatos;
    private JPanel     panel1, panel2;

    private AutoADLList auto = new AutoADLList();

    public AutoGUI()
    {
        super("Catalogo de Autos");

        // Crear objetos
        tfClave  = new JTextField();
        tfMarca  = new JTextField();
        tfTipo   = new JTextField();
        tfPrecio = new JTextField();

        bCapturar  = new JButton("Capturar datos (Archivo)");
        bConsultar = new JButton("Consultar datos (Archivo)");
        bConsultarArrObj = new JButton("Consultar datos (Arreglo Objetos)");
        bConsultarClave = new JButton("Consultar Clave (Arreglo Objetos)");
        bConsultarMarca = new JButton("Consultar Marca (Arreglo Objetos)");
        bCotizar   = new JButton("Cotizar Auto (Arreglo Objetos)");
        bVenta     = new JButton("Venta del Auto (Arreglo Objetos)");
        bCancelar  = new JButton("Cancelar Transaccion (Arreglo Objetos)");
        bConsultarVentas = new JButton("Consultar Ventas (Archivo)");
        bSalir     = new JButton("Salir");

        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarClave.addActionListener(this);
        bConsultarMarca.addActionListener(this);
        bCotizar.addActionListener(this);
        bConsultarArrObj.addActionListener(this);
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
        panel1.add(bConsultarArrObj);
        panel1.add(bConsultarClave);

        panel1.add(bCotizar);
        panel1.add(bCancelar);
        panel1.add(bVenta);
        panel1.add(bSalir);
        panel1.add(bConsultarVentas);

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
        bConsultarArrObj.setEnabled(false);
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
        bConsultarArrObj.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String datos="", respuesta="";

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
                    respuesta = auto.capturar(datos);

                    // 3. Desplegar resultado de la captura
                    taDatos.setText(respuesta);
                }
        }

        if(e.getSource() == bConsultar)
        {
            // 1. ejecutar el metodo do consultar de AutosAD
            datos = auto.consultar();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarClave)
        {
            // 1. Leer clave del tfClave
            String clave = tfClave.getText();

            // 2.Consultar la clave en AutosAD
            datos = auto.consultarClave(clave);

            // 3. Despleagr la respuesta
            //taDatos.setText(datos);
            if(datos.equals("LISTA_VACIA"))
                taDatos.setText("Lista vacio...");
            else
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("Clave de Auto no localizada...");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    activarBotones();
                }
        }

        if(e.getSource() == bConsultarMarca)
        {
            // 1. Leer marca del tfMarca
            String marca = tfMarca.getText();

            // 2.Consultar la marca en AutosAD
            datos = auto.consultarMarca(marca);

            // 3. Despleagr la respuesta
            //taDatos.setText(datos);
            taDatos.setText(datos);
        }

        if(e.getSource() == bConsultarArrObj)
        {
            // 1.Consultar Arreglo Objetos de AutoDP
            //datos = auto.consularArregloObj();

            // 3. Desplegar la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bCotizar)
        {
            String cotizacion="";

            try
            {
                // 2. Pedir plazo de la cotizacion al usuario
                String strPlazo = JOptionPane.showInputDialog("No. de Meses de la Cotizacion [12, 24, 48]");
                int  plazo = Integer.parseInt(strPlazo);

                // 3. Cotizar automovil desde AutosAD
                cotizacion = auto.cotizarAuto(plazo);

                // 4. Desplegar resultado de la captura
                taDatos.setText(cotizacion);
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("El Plazo de pago debe ser numerico...");
            }
        }

        if(e.getSource() == bVenta)
        {
            String resultado="";

            try
            {
                // 1. Pedir plazo de la cotizacion al usuario
                String strPlazo = JOptionPane.showInputDialog("No. de Meses de la Cotizacion [12, 24, 48]");
                int  plazo = Integer.parseInt(strPlazo);

                // 2. Venta automovil desde AutosAD
                resultado = auto.venta(plazo);

                // 3. Desplegar resultado de la captura
                taDatos.setText(resultado);

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
            datos = auto.consultarVentas();

            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }

        if(e.getSource() == bSalir){
          auto.datosListaCocheArchivo();
          auto.datosListaVentasArchivo();
          
          System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new AutoGUI();
    }
}
