package model.direccion

class DirInterna(type: Int) : AlgoritmoDireccion(type) {
    constructor() : this(0)

    override fun hashMod(x: Int, n: Int): Int {
        return x % n + 1
    }

    override fun hashCuadrado(x: Int, n: Int): Int {
        val cuadrado = x * x
        return if (cuadrado < n) {
            (cuadrado + 1) % n
        } else {
            val centro = (cuadrado.toString() + "").length / 2 + 1
            var eliminaDerecha = (cuadrado.toString() + "").substring(0, centro)
            while (eliminaDerecha.length > n) {
                eliminaDerecha = eliminaDerecha.substring(1)
            }
            (eliminaDerecha.toInt() + 1) % n
        }
    }

    override fun hashPlegamiento(x: Int, n: Int): Int {
        val str = n.toString() + ""
        val num = String.format("%0" + str.length + "d", x)
        val a = num.substring(0, num.length / 2).toInt()
        val b = num.substring(num.length / 2).toInt()
        return (a * b + 1) % n
    }

    override fun hashTruncamiento(x: Int, n: Int): Int {
        val str = n.toString() + ""
        val num = String.format("%0" + str.length + "d", x)
        val a = num.substring(num.length - 3, num.length - 1).toInt()
        return (a + 1) % n
    }
}