package model.direccion;

public class AlgoritmoDireccion {
    int type;

    public AlgoritmoDireccion(int type) {
        this.type = type;
    }

    public int getDireccion(int x, int N) {
        switch(type) {
            case 0:
                return fHashMod(x, N);
        }
        return -1;
    }

    public int fHashMod(int x, int N) {
        return x % N + 1;
    }
}
