package model;

import java.util.Arrays;

import view.Frame;

public class Algoritmos {
    /**
     * v: arreglo de datos
     * n: size
     * int x: elemento x
     * @return valor y posición
     */
    public static String secuencial(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+i+", "+v[i]+"), ";
        }

        int i = 0;
        int n = v.length;
        while(i < n && v[i] != x) {
            i++;
        }
        if(i>=n) {
            return "La clave "+x+" no está en el arreglo."+estructura;
        }
        else {
            return "La información "+x+" se encuentra en la posición "+i+estructura;
        }
    }

    public static String binario(int[] v, int x) {
        Arrays.sort(v);

        return secuencial(v, x);
    }

    public static String hashMod(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+fHashMod(v[i])+", "+v[i]+"), ";
        }

        int i = 0;
        int n = v.length;
        while(i < n && v[i] != x) {
            i++;
        }
        if(i>=n) {
            return "La clave "+x+" no está en el arreglo."+estructura;
        }
        else {
            return "La información "+x+" se encuentra en la posición "+fHashMod(v[i])+estructura;
        }
    }

    public static int fHashMod(int x) {
        int n = Frame.n;
        return x % n + 1;
    }

    public static String hashCuadrado(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+fHashCuadrado(v[i])+", "+v[i]+"), ";
        }

        int i = 0;
        int n = v.length;
        while(i < n && v[i] != x) {
            i++;
        }
        if(i>=n) {
            return "La clave "+x+" no está en el arreglo."+estructura;
        }
        else {
            return "La información "+x+" se encuentra en la posición "+fHashCuadrado(v[i])+estructura;
        }
    }

    public static int fHashCuadrado(int x) {
        int cuadrado = x*x;
        if(cuadrado < Frame.n) {
            return (cuadrado+1) % Frame.n;
        }
        else {
            int centro = (cuadrado+"").length()/2 + 1;
            String eliminaDerecha = (cuadrado+"").substring(0, centro);
            while(eliminaDerecha.length()>Frame.n){
                eliminaDerecha = eliminaDerecha.substring(1);
            }
            return (Integer.parseInt(eliminaDerecha)+1) % Frame.n;
        }
    }

    public static String hashPlegamiento(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+fHashPlegamiento(v[i])+", "+v[i]+"), ";
        }

        int i = 0;
        int n = v.length;
        while(i < n && v[i] != x) {
            i++;
        }
        if(i>=n) {
            return "La clave "+x+" no está en el arreglo."+estructura;
        }
        else {
            return "La información "+x+" se encuentra en la posición "+fHashPlegamiento(v[i])+estructura;
        }
    }

    public static int fHashPlegamiento(int x) {
        int cuadrado = x*x;
        if(cuadrado < Frame.n) {
            return (cuadrado+1) % Frame.n;
        }
        else {
            int centro = (cuadrado+"").length()/2 + 1;
            String eliminaDerecha = (cuadrado+"").substring(0, centro);
            while(eliminaDerecha.length()>Frame.n){
                eliminaDerecha = eliminaDerecha.substring(1);
            }
            return (Integer.parseInt(eliminaDerecha)+1) % Frame.n;
        }
    }



}
