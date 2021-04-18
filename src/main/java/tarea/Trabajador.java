
package tarea;

import java.time.LocalDate;

/**
 * Una clase POJO (Plain Old Java Object) Un POJO es una instancia de una clase
 * que no extiende ni implementa nada en especial. Para los programadores Java
 * sirve para enfatizar el uso de clases simples y que no dependen de un
 * framework en especial. Este concepto surge en oposición al modelo planteado
 * por los estándares EJB anteriores al 3.0, en los que los Enterprise JavaBeans
 * (EJB) debían implementar interfaces especiales.
 *
 * @author ALEJANDRO MORENO MARTIN 1°DAW
 */
public class Trabajador {

    private String nombreCompleto;
    private String dni;
    private String telefono;
    private String puesto;
    private LocalDate posesion;
    private LocalDate cese;
    private boolean evaluador; //   si = true   no = false
    private boolean coordinador;//   si = true   no = false

//  GETTERS & SETTERS
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getPosesion() {
        return posesion;
    }

    public void setPosesion(LocalDate posesion) {
        this.posesion = posesion;
    }

    public LocalDate getCese() {
        return cese;
    }

    public void setCese(LocalDate cese) {
        this.cese = cese;
    }

    public boolean isEvaluador() {
        return evaluador;
    }

    public void setEvaluador(boolean evaluador) {
        this.evaluador = evaluador;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }

//  TO STRING
    @Override
    public String toString() {

//      Variables para el toString
        String fechaPosesion = "", fechaCese = "", coord = "No", eval = "No";

        if (this.evaluador) {
            eval = "Si";
        }

        if (this.coordinador) {
            coord = "Si";
        }

        return nombreCompleto + "," + dni + "," + puesto + "," + posesion + "," + cese + "," + telefono + "," + eval + "," + coord;
    }
}
