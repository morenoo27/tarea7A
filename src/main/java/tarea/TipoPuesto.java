
package tarea;

import java.util.ArrayList;

/**
 * Esta clase almacenara todas los tipos posibles de puestos de trabajo que
 * almacenaremos del archivo para asi ahorrarnos codigo. Clase innecesaria ya
 * que podemos captar directamente la cadena de texto del archivo, que define
 * totalmente el tipo de puesto que ejerce dicho empleado.
 * 
 * Si se quisiera almacenar en una clase aparte la informacion de cada tipo de 
 * puesto de trabajo para futuras acciones seria de al siguiente manera.
 *
 * @author ALEJANDRO MORENO MARTIN 1°DAW
 */
public enum TipoPuesto {

    //tipos de puesto que vam0os a encontrar en el archivo
    MATEMATICAS("Matemáticas P.E.S."),
    COVID("Area Científico-Tecnolog. (Apoyo Covid)"),
    RELIGION("Contr. Lab. Religión (Sec-Ere)"),
    TECNOLOGIA("Tecnología P.E.S."),
    GEOGRAFIAHISTORIA("Geografía e Historia P.E.S."),
    LENGUA("Lengua Castellana y Literatura P.E.S."),
    ADE("Administración de Empresas P.E.S."),
    AREALENGUA("Area de Lengua y CC. Soc. (Apoyo Covid)"),
    INFORMATICA("Informática P.E.S."),
    INGLES("Inglés P.E.S."),
    SISTEMASELECTRICOS("Sistemas Electrónicos P.E.S."),
    BIOLOGIAGEOLOGIA("Biología y Geología P.E.S."),
    EDUCACIONFISICA("Educación Física P.E.S."),
    DIBUJO("Dibujo P.E.S."),
    FOL("Formación y Orientación Laboral P.E.S."),
    FISICAQUIMICA("Física y Química P.E.S."),
    LABORATORIO("Contr. Lab.Evangelic. (Sec-Ere) 04 Horas"),
    GESTION("Proc. Gestión Administrativa P.T.F.P."),
    ECONOMIA("Economía P.E.S."),
    CULTURACLASICA("Cultura Clásica P.E.S."),
    ORIENTACION("Orientación Educativa P.E.S."),
    FILOSOFIA("Filosofía P.E.S."),
    FRANCES("Francés P.E.S.");

    //solamente tendremos como parametro el nombre
    private final String nombre;

    TipoPuesto(String nombre) {

        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Almacena mediente el metodo .add cada uno de los tipos de
     * trabajos que tenemos en la clase TipoPuesto. Se añaden de manera manual.
     * 
     * CUESTION: Se podra hacer de forma automatica este metodo?
     *
     * @return lista con todos los puestos de trabajos definidos en la clase
     * TipoPuesto
     */
    public static ArrayList<TipoPuesto> getTipos() {

        ArrayList<TipoPuesto> lista = new ArrayList<>();

        lista.add(TipoPuesto.ADE);
        lista.add(TipoPuesto.AREALENGUA);
        lista.add(TipoPuesto.BIOLOGIAGEOLOGIA);
        lista.add(TipoPuesto.COVID);
        lista.add(TipoPuesto.DIBUJO);
        lista.add(TipoPuesto.ECONOMIA);
        lista.add(TipoPuesto.EDUCACIONFISICA);
        lista.add(TipoPuesto.FILOSOFIA);
        lista.add(TipoPuesto.FISICAQUIMICA);
        lista.add(TipoPuesto.FOL);
        lista.add(TipoPuesto.FRANCES);
        lista.add(TipoPuesto.GEOGRAFIAHISTORIA);
        lista.add(TipoPuesto.GESTION);
        lista.add(TipoPuesto.INFORMATICA);
        lista.add(TipoPuesto.INGLES);
        lista.add(TipoPuesto.LABORATORIO);
        lista.add(TipoPuesto.LENGUA);
        lista.add(TipoPuesto.MATEMATICAS);
        lista.add(TipoPuesto.ORIENTACION);
        lista.add(TipoPuesto.RELIGION);
        lista.add(TipoPuesto.SISTEMASELECTRICOS);
        lista.add(TipoPuesto.TECNOLOGIA);

        return lista;
    }
}
