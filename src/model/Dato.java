package model;

public class Dato {
    public int direccion;
    public int clave;
    public boolean isCollision;

    public Dato(int direccion, int clave, boolean isCollision) {
        this.direccion = direccion;
        this.clave = clave;
        this.isCollision = isCollision;
    }
}