package model;


public class Algoritmos {

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
}
