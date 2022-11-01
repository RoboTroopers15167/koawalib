package com.asiankoala.koawalib.path.gvf

import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.degrees
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.util.Speeds
import kotlin.math.min

/**
 *  Guided Vector Field follower
 *  @link https://arxiv.org/pdf/1610.04391.pdf
 *  @param path path
 *  @param kN normal path attraction
 *  @param kOmega heading weight
 *  @param kF end param weight
 *  @param kS raw scalar on translational power
 *  @param epsilon allowed absolute and projected error
 *  @param errorMap error map to transform normal displacement error
 *  @property isFinished path finish state
 */
class SimpleGVFController(
    path: Path,
    kN: Double,
    kOmega: Double,
    private val kF: Double,
    private val kS: Double,
    epsilon: Double,
    errorMap: (Double) -> Double = { it },
) : GVFController(path, kN, kOmega, epsilon, errorMap) {
    override fun headingControl(vel: Speeds): Pair<Double, Double> {
        val error = (path[s].heading - pose.heading).angleWrap.degrees
        val result = kOmega * error
        return Pair(result, error)
    }

    override fun vectorControl(vel: Speeds): Vector {
        return gvfVec * kS * min(1.0, (path.length - s) / kF)
    }

    init {
        require(kS <= 1.0)
    }
}
