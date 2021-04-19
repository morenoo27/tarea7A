package tarea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

        //creamos las listas en la que vamosa a almacenar cada uno de los objetos
        ArrayList<Trabajador> listaEmpleados = new ArrayList<>();
        ArrayList<Trabajador> informatica = new ArrayList<>();
        ArrayList<Trabajador> biologiaCoord = new ArrayList<>();
        ArrayList<Trabajador> nifN = new ArrayList<>();

        System.out.println(LocalDate.now());
        leerFichero(listaEmpleados);

        System.out.println("Todos los empelados leidos, profcedemos a mostrarlso por consola:");
        //mostramos todos los empleados
        //cambio a expresion lambda
        listaEmpleados.forEach(empleado -> {
            System.out.println(empleado.toString());
        });

        escribirEnFichero(listaEmpleados);

//      AMPLIACION EJERCICIO
        //damos un poco de espacio en al consola
        System.out.println();
        System.out.println("-------AMPLIACION EJERCICIO---------");

//      APARTADO A
//      PUNTO 1:
//      Contar el número de profesores de Informática.
        System.out.println("------------------PUNTO 1-----------------");
        System.out.println();

//      Recorremos toda la lista y cuando veamos uno que sea profesor de informatica
//      sumaremos uno al contador
        for (Trabajador empleado : listaEmpleados) {
            if (empleado.getPuesto().equals(TipoPuesto.INFORMATICA)) {
                informatica.add(empleado);
            }
        }

//      Mostramos cuantos profesores de informatica hay y los mostramos
        System.out.println("Hay " + informatica.size() + " profesores de informatica");
        for (Trabajador trabajador : informatica) {
            System.out.println(trabajador.toString());
        }

//      PUNTO 2:
//      Saber si algún profesor/a de Biología es también coordinador.
        System.out.println();
        System.out.println("------------------PUNTO 2-----------------");
        System.out.println();

//      Mismo procedimiento que el anterior, pero esta vez la condicion es que
//      sea profesor de Biologia y coordinador
        for (Trabajador trabajador : listaEmpleados) {
            if (trabajador.getPuesto().equals(TipoPuesto.BIOLOGIAGEOLOGIA) && trabajador.isCoordinador()) {
                biologiaCoord.add(trabajador);
            }
        }

        System.out.println("Hay " + biologiaCoord.size() + " profesores de biologia que son coordinadores");
        for (Trabajador trabajador : biologiaCoord) {
            System.out.println(trabajador.toString());
        }

//      PUNTO 3:
//      Obtener una lista ordenada alfabéticamente con todos los apellidos de 
//      los empleados cuyo NIF contenga la letra N.
        System.out.println();
        System.out.println("------------------PUNTO 3-----------------");
        System.out.println();

        for (Trabajador empleado : listaEmpleados) {
            if (empleado.getNif().contains("N")) {
                nifN.add(empleado);
            }
        }

        //ordenamos por apellidos
        Collections.sort(nifN, (e1, e2) -> e1.getApellidos().compareTo(e2.getApellidos()));

        for (Trabajador trabajador : nifN) {
            System.out.println(trabajador.getApellidos() + "," + trabajador.getNombre()
                    + "\t" + trabajador.getNif());
        }

//      PUNTO 4:
//      Verificar que ningún profesor se llama "Jonh".
        System.out.println();
        System.out.println("------------------PUNTO 4-----------------");
        System.out.println();

        Trabajador jonh = new Trabajador();
        boolean Jonh = false;

        for (Trabajador empleado : listaEmpleados) {
            if (empleado.getNombre().contains("Jonh")) {
                Jonh = true;
                jonh = empleado;
                break;
            }
        }

        if (Jonh) {
            System.out.println("Si hay alguien que se llama Jonh");
        } else {
            System.out.println("No hay nadie que se llama Jonh");
        }

//      APARTADO B
        System.out.println();
        System.out.println();
//      PUNTO 1:
//      Contar el número de profesores de Informática. CON API
        System.out.println("------------------PUNTO 1-----------------");
        System.out.println();

        List<Trabajador> linformatica = buscarprofesoresInformaticos(listaEmpleados);
        System.out.println("Hay " + linformatica.size() + " profesores de informatica");
        linformatica.forEach(trabajador -> {
            System.out.println(trabajador.toString());
        });

//      PUNTO 2:
//      Saber si algún profesor/a de Biología es también coordinador. CON API
        System.out.println();
        System.out.println("------------------PUNTO 2-----------------");
        System.out.println();
        List<Trabajador> lBioCoord = buscarProfesoresBioCoord(listaEmpleados);
        System.out.println("Hay " + lBioCoord.size() + " profesores de biologia que son coordinadores");
        lBioCoord.forEach(trabajador -> {
            System.out.println(trabajador.toString());
        });

//      PUNTO 3:
//      Obtener una lista ordenada alfabéticamente con todos los apellidos de 
//      los empleados cuyo NIF contenga la letra N. CON API
        System.out.println();
        System.out.println("------------------PUNTO 3-----------------");
        System.out.println();

        List<Trabajador> listaDNIN = buscarN(listaEmpleados);
        listaDNIN.forEach(trabajador -> {
            System.out.println(trabajador.getApellidos() + "," + trabajador.getNombre()
                    + "\t" + trabajador.getNif());
        });

//      PUNTO 4:
//      Verificar que ningún profesor se llama "Jonh". CON API
        System.out.println();
        System.out.println("------------------PUNTO 4-----------------");
        System.out.println();

        Jonh = buscarJonh(listaEmpleados);

        if (Jonh) {
            System.out.println("Si hay alguien que se llama Jonh");
        } else {
            System.out.println("No hay nadie que se llama Jonh");
        }

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

        if (token.equals("")) {
            return null;
        }

        LocalDate fecha = LocalDate.parse(token, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

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
     * vamos a coger ambas cadenas y foramtearlas para que salga el nombre
     * completo de manera correcta
     *
     * @param token1 Cadena de texto sin formatear
     * @param token2 Cadena de texto sin formatear
     * @return Cadena de texto unida y formateada a gusto propio
     */
    private static Trabajador concat(Trabajador tmp, String token1, String token2) {

        String apellidos = token1.substring(1);//quitamos la primera comilla

        //quitamos la ultima comilla y el espacio
        String nombre = token2.substring(1, token2.length() - 1);

        tmp.setNombre(nombre);
        tmp.setApellidos(apellidos);

        return tmp;
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

//              Establecemos el nombre y el apellido
                tmp = concat(tmp, tokens[0], tokens[1]);

//              Ahora lo que hacemos es usar los setters de la clase para crear
//              cada uno de los empleados
                tmp.setNif(refactor(tokens[2]));
                tmp.setPuesto(puesto(refactor(tokens[3])));
                tmp.setPosesion(fecha(refactor(tokens[4])));
                tmp.setCese(fecha(refactor(tokens[5])));
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
            flujo.write("Nombre,Apellidos,DNI/Pasaporte,Puesto,Fecha de toma de posesión,Fecha de cese,Teléfono,Evaluador,Coordinador");

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
     * Metodo para saber si un empleado lleva trabajando mas de 20 años. Tenemos
     * que controlar tambien que miremos si nuestro empleado ha sido despedido
     * ya que aunques de 20, ya no esta trabajando en este momento
     *
     * @param empleado Objeto tipo Trabajador
     * @return true mas de 20 false menos de 20
     */
    private static boolean isMayorDe20(Trabajador empleado) {
        if (empleado.getCese() != null) {
            return false;
        }
        return empleado.getPosesion().isBefore(LocalDate.now().minusYears(20));
    }

    /**
     * Metodo para almacenar en una lista todos los profesores que estan
     * especializados en el campo de la Informatica usando el metodo .stream en
     * una lista con empleados
     * Para este ultimo metodo realizamos las siguientes acciones:
     *      .filter para buscar solo aquellos que sean informaticos
     *      .collect para guardar todos los que cumplan la accion/acciones
     *          anteriores en la lista
     *
     * @param listaEmpleados Lista de todos los empeleados
     * @return Lista con los profesores informaticos
     */
    private static List<Trabajador> buscarprofesoresInformaticos(ArrayList<Trabajador> listaEmpleados) {
        List<Trabajador> listaInformaticos = listaEmpleados.stream()
                .filter(empleado -> empleado.getPuesto() == TipoPuesto.INFORMATICA)
                .collect(Collectors.toList());

        return listaInformaticos;
    }

    /**
     * Metodo para buscar todos los profesores, de una lista de empleados, que
     * son, ademas de profesores especializados en el campo de biologia,
     * cordinador
     * Para ello realizamos las siguientes operaciones:
     *      .filter para buscar solo aquellos que sean biologos y coordinadores
     *      .collect para guardar todos los que cumplan la accion/acciones
     *          anteriores en la lista
     *
     * @param listaEmpleados Lista de todos los empeleados
     * @return Lista con los biologos que son coordinadores
     */
    private static List<Trabajador> buscarProfesoresBioCoord(ArrayList<Trabajador> listaEmpleados) {
        List<Trabajador> biologosCoordinadores = listaEmpleados.stream()
                .filter(empleado -> empleado.getPuesto() == TipoPuesto.BIOLOGIAGEOLOGIA && empleado.isCoordinador())
                .collect(Collectors.toList());

        return biologosCoordinadores;
    }

    /**
     * Metodo para ordenar por apellidos los empleado que tengan una N en su nif.
     * Para ello creamos una lista a partir de la lista global donde estan todos
     * los empleados y realizamos las siguientes operaciones:
     *      .filter para buscar lso nif que tengan N
     *      .sorted apra ordenar por apellidos la lista
     *      .collect para guardar todos los que cumplan la accion/acciones
     *          anteriores en la lista
     *
     * @param listaEmpleados Lista de todos los empeleados
     * @return Lista ordenada por apellidos de los empleados que tengan una N en
     * su nif
     */
    private static List<Trabajador> buscarN(ArrayList<Trabajador> listaEmpleados) {
        List<Trabajador> EmpleadosConN = listaEmpleados.stream()
                .filter(empleado -> empleado.getNif().contains("N"))
                .sorted((e1, e2) -> e1.getApellidos().compareTo(e2.getApellidos()))
                .collect(Collectors.toList());

        return EmpleadosConN;
    }

    /**
     * Metodo para buscar si algun empleado tiene un nombre especifico, en este
     * caso se trata del nombre de Jonh.
     * Para ello creamos una lista a partir de la lista global donde estan todos
     * los empleados y realizamos las siguientes operaciones:
     *      .filter para la condicion de que algun nombre sea igual a "Jonh"
     *      .collect para guardar todos los que cumplan la accion/acciones
     *          anteriores en la lista
     *
     * @param listaEmpleados Lista de todos los empeleados
     * @return Si la lista esta vacia devolvera false(ya que no habra nadie que
     * se llame Jonh), si devuleve true es por que se ha almacenado almenos una
     * persona con el nomnre de Jonh
     */
    private static boolean buscarJonh(ArrayList<Trabajador> listaEmpleados) {
        List<Trabajador> Jonh = listaEmpleados.stream()
                .filter(empleado -> empleado.getNombre().equals("Jonh"))
                .collect(Collectors.toList());

        return !Jonh.isEmpty();
    }
}
