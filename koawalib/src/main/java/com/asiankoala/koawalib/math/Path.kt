package com.asiankoala.koawalib.math

import kotlin.math.atan
import kotlin.math.pow

abstract class Path {
    abstract fun closestTOnPathTo(r: Point, guess: Double): Double
    abstract fun calculatePoint(t: Double): Point
    abstract fun tangentVec(t: Double): Point
    abstract fun levelSet(r: Point, closestT: Double): Double
    abstract fun errorGradient(r: Point, closestT: Double): Point
    open fun error(s: Double): Double {
        return atan(s)
    }
    open fun errorDeriv(s: Double): Double {
        return 1.0 / (1.0 + s.pow(2))
    }
    abstract fun nVec(r: Point, closestT: Double): Point
    abstract fun length(): Double
}