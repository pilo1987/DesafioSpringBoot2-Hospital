package cl.praxis.ProyectoHospitalPostPandemia.model.dto;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private String prevision;


    public Paciente() {
    }

    public Paciente(int id, String nombre, String apellido, String prevision) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.prevision = prevision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", prevision='" + prevision + '\'' +
                '}';
    }
}
