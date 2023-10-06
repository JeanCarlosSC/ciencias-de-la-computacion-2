package model.direccion

const val DIRECCION_HASH_MOD = 0
const val DIRECCION_HASH_CUADRADO = 1
const val DIRECCION_HASH_PLEGAMIENTO = 2
const val DIRECCION_HASH_TRUNCAMIENTO = 3

abstract class AlgoritmoDireccion(private var type: Int) {

    abstract fun hashMod(x: Int, n: Int): Int
    abstract fun hashCuadrado(x: Int, n: Int): Int
    abstract fun hashPlegamiento(x: Int, n: Int): Int
    abstract fun hashTruncamiento(x: Int, n: Int): Int

    fun getDir(x: Int, N: Int): Int {
        return when (type) {
            DIRECCION_HASH_MOD -> hashMod(x, N)
            DIRECCION_HASH_CUADRADO -> hashCuadrado(x, N)
            DIRECCION_HASH_PLEGAMIENTO -> hashPlegamiento(x, N)
            DIRECCION_HASH_TRUNCAMIENTO -> hashTruncamiento(x, N)
            else -> -1
        }
    }
}
