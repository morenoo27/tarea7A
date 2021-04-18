
package tarea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal en la que realizamos el ejercicio en si. El ejercicio
 * consiste en leer un archivo con elementos para crear uun objeto y almacenar
 * en ootro los empleados que tienen una antiguiedad igual o mayor a 20 años
 * trabajando
 *
 * @author ALEJANDRO MORENO MARTIN 1°DAW
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //creamos al lista en la que vamosa a almacenar cada uno de los objetos
        ArrayList<Trabajador> listaEmpleados = new ArrayList<>();

        leerFichero(listaEmpleados);

        //mostramos todos los empleados
        for (Trabajador empleado : listaEmpleados) {
            System.out.println(empleado.toString());
        }

        escribirEnFichero(listaEmpleados);
    }

    /**
     * Como cada uno de las divisiones vana a estar con comillas, creamos un
     * metodo para quitar esas comillas usando el metodo de la clase String
     * "substring". Si al cadena esta vacia, se devolvera una cadena que dira
     * que no hay datos.
     *
     * @param token Division con comiilas de la linea del archivo que estamos
     * leyendo
     * @return cadena de caracteres sin comillas
     */
    private static String refactor(String token) {

        if (token.equals("")) {
            return "sin datos";
        } else {
            String cadenaFormateada = token.substring(1, token.length() - 1);
            return cadenaFormateada;
        }
    }

    /**
     * Metodo para determianr que tipo de puesto de trabajo tiene el objeto en
     * cuestion. Para ello usaremos el metodo equals o contains
     *
     * @param token Division sin comiilas de la linea del archivo que estamos
     * leyendo
     * @return TipoPuesto correspondiente
     */
    private static TipoPuesto puesto(String token) {

        ArrayList<TipoPuesto> tipos = TipoPuesto.getTipos();

        for (int i = 0; i < tipos.size(); i++) {

            if (token.contains(tipos.get(i).getNombre())) {
                return tipos.get(i);
            }
        }

        return null;
    }

    /**
     * Metodo para convertir una cadean de String a un objeto de tipo LocalDate
     * con el formato de dia/mes/año
     *
     * @param token Division sin comiilas de la linea del archivo que estamos
     * leyendo
     * @return Fecha de tipo LocalDate con el formato español
     */
    public static LocalDate fecha(String token) {
        
        //quitamos todos los caracteres que no necesitamos
        String tmp = token.replace("\"", "");
        
        if (tmp.equals("")) {
            return null;
        }
        LocalDate fecha = LocalDate.parse(tmp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return fecha;
    }

    /**
     * Metodo para convertir el String en un booleano. Si el texto es si
     * devolvera true y si es no devolvera false
     *
     * @param token Division sin comiilas de la linea del archivo que estamos
     * leyendo
     * @return booleano correspondiente
     */
    private static boolean conversionBoolean(String token) {
        return !token.equalsIgnoreCase("no");
    }

    /**
     * Como por medio tenemos una coma, el nombre se divide en 2. Por lo que
     * vamos a coger ambas cadenas y unirlas y foramtearlas para que salga el
     * nombre completo de m,anera correcta
     *
     * @param token1 Cadena de texto sin formatear
     * @param token2 Cadena de texto sin formatear
     * @return Cadena de texto unida y formateada a gusto propio
     */
    private static String concat(String token1, String token2) {

        String apellidos = token1.substring(1);//quitamos la primera comilla

        //quitamos la ultima comilla y el espacio
        String nombre = token2.substring(1, token2.length() - 1);

        String nombreCompleto = nombre + " " + apellidos;

        return nombreCompleto;
    }

    /**
     * Metodo en el que leeremos el archivo en cuestion y, por medio de crear un
     * objeto de tipo Trabajador y los setters de la clase, iremos leyendo cada
     * linea, troceandola en partes dependiendo del elemento de separaacionlo,
     * iremos aniadiendo a una lista variable
     *
     * @param listaEmpleados Lista en la que añadiremos los empleados
     */
    private static void leerFichero(ArrayList<Trabajador> listaEmpleados) {

        //id del artchivo que vamos a leer
        String idFichero = "RelPerCen.csv";

        //variable donde almacenaremos la linea completa
        String linea;

        //Array donde almacenaremos las divisiones
        String[] tokens;

        LocalDate fechaPosesion, fechaCese;

        try ( Scanner datosFichero = new Scanner(new File(idFichero), "ISO-8859-1")) {

//          eliminamos la primera linea, ya que es la que nos da la info de que
//          campos tenemos que almacenar
            datosFichero.nextLine();

            while (datosFichero.hasNextLine()) {

//              Almacenamos la informacion de la linea en un string
                linea = datosFichero.nextLine();

//              dividimos la linea con le caracter que divide cada uno de los
//              diferentes campos y los metemos en un array
                tokens = linea.split(",");

//              Instanciamos un objeto de tipo Trabajador
                Trabajador tmp = new Trabajador();

//              Ahora lo que hacemos es usar los setters de la clase para crear
//              cada uno de los empleados
                tmp.setNombreCompleto(concat(tokens[0], tokens[1]));
                tmp.setDni(refactor(tokens[2]));
                /*
                SI SE QUISIERA REALIZAR CON LA CALSE ENUM, EN VEZ DE SER CON EL
                METODO "refactor" SOLO SERIA DE LA SIGUIENTE MANERA:
                tmp.setPuesto(puesto(refactor(tokens[3])));
                */
                tmp.setPuesto(refactor(tokens[3]));
//              Para controlar que no haya fallos, si uno de esto campos es null
//              no se molesta el programa en realizar el set
                fechaPosesion = fecha(tokens[4]);
                tmp.setPosesion(fechaPosesion);
                fechaCese = fecha(tokens[5]);
                tmp.setCese(fechaCese);
                tmp.setTelefono(refactor(tokens[6]));
                tmp.setEvaluador(conversionBoolean(refactor(tokens[7])));
                tmp.setCoordinador(conversionBoolean(refactor(tokens[8])));

                listaEmpleados.add(tmp);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para escribir en un nuevo fichero a traves del Objeto BufferWriter
     *
     * @param listaEmpleados Lista de los empleados
     */
    private static void escribirEnFichero(ArrayList<Trabajador> listaEmpleados) {

        String idFichero = "MasDe20.csv";

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

//          Escribimos en la primera linea el titulo de cada una de las celdas
//          y una nueva linea (cada vez quie escribamos)
            flujo.write("Empleado/a,DNI/Pasaporte,Puesto,Fecha de toma de posesión,Fecha de cese,Teléfono,Evaluador,Coordinador");

            flujo.newLine();

            for (int i = 0; i < listaEmpleados.size(); i++) {

                if (isMayorDe20(listaEmpleados.get(i))) {
                    flujo.write(listaEmpleados.get(i).toString());
                    flujo.newLine();
                }
            }

//          Metodo fluh() guarda cambios en disco 
            flujo.flush();

            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para saber si un empleado lleva trabajando mas de 20 años
     *
     * @param empleado Objeto tipo Trabajador
     * @return true mas de(o igual) 20 false menos de 20
     */
    private static boolean isMayorDe20(Trabajador empleado) {
        return LocalDate.now().getYear() - empleado.getPosesion().getYear() >= 20;
    }
}