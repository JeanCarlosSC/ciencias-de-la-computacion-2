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

    public static int fHashMod(int x, int N) {
        return x % N + 1;
    }
    
    public static int fHashCuadrado(int x, int N) {
        int cuadrado = x*x;
        if(cuadrado < N) {
            return (cuadrado+1) % N;
        }
        else {
            int centro = (cuadrado+"").length()/2 + 1;
            String eliminaDerecha = (cuadrado+"").substring(0, centro);
            while(eliminaDerecha.length()>N){
                eliminaDerecha = eliminaDerecha.substring(1);
            }
            return (Integer.parseInt(eliminaDerecha)+1) % N;
        }
    }
    
    public static int fHashPlegamiento(int x, int N) {
        String str = N+"";
        String num = String.format("%0"+str.length()+"d", x);
        int a = Integer.parseInt(num.substring(0, num.length()/2));
        int b = Integer.parseInt(num.substring(num.length()/2));
        return (a*b+1) % N;
    }

    public static int fHashTruncamiento(int x, int N) {
        String str = N+"";
        String num = String.format("%0"+str.length()+"d", x);
        int a = Integer.parseInt(num.substring(num.length()-3, num.length()-1));
        return (a+1) % N;
    }
}
