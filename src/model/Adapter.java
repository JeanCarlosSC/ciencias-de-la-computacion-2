package model;

import java.util.Arrays;

public class Adapter {
    /* 
    public static String secuencial(int[] v, int x) {
        
    }
    
    public static String binario(int[] v, int x) {
        Arrays.sort(v);

        return secuencial(v, x);
    }

    public static String hashMod(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+Algoritmos.fHashMod(v[i])+", "+v[i]+"), ";
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
            return "La información "+x+" se encuentra en la posición "+Algoritmos.fHashMod(v[i])+estructura;
        }
    }

    public static String hashCuadrado(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+Algoritmos.fHashCuadrado(v[i])+", "+v[i]+"), ";
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
            return "La información "+x+" se encuentra en la posición "+Algoritmos.fHashCuadrado(v[i])+estructura;
        }
    }


    public static String hashPlegamiento(int[] v, int x) {
        String estructura = "\nEstructura dada en la forma (Posición, Clave)\n";
        for (int i=0; i<v.length; i++) {
            estructura += "("+Algoritmos.fHashPlegamiento(v[i])+", "+v[i]+"), ";
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
            return "La información "+x+" se encuentra en la posición "+Algoritmos.fHashPlegamiento(v[i])+estructura;
        }
    }

*/
}
