package com.example.myapplication

// x_0 * w_0 + ... + x_n * w_n
private fun output(equation: List<Pair<Double, Double>>) =
    equation.map { it.first * it.second }.sum()

private fun predict(value: Double, threshold: Double) = value > threshold

private fun delta(expected: Double, actual: Double) = expected - actual

private fun train(data: List<Pair<List<Double>, Boolean>>,
          weights: List<Double>,
          iterations: Int,
          rate: Double,
          threshold: Double): List<Double> {
    var localWeights = weights
    (1 .. iterations).forEach { _ ->
        data.forEach { test ->
            val xs = test.first
            val expected = test.second
            val y = output(xs zip localWeights)
            val prediction = predict(y, threshold)
            val error = delta(threshold, y)
            if (expected != prediction) {
                localWeights = localWeights.zip(xs) { w, x -> w + x * rate * error }.toList()
            }
        }
    }
    return localWeights
}

fun solve(data: List<Pair<List<Double>, Boolean>>, rate: Double, iterations: Int, threshold: Double): Pair<List<Boolean>, List<Double>> {
    val startingWeights: List<Double> = listOf(0.0, 0.0)
    val weights = train(data, startingWeights, iterations, rate, threshold)
    val results = data.map { predict(output(it.first zip weights), threshold) }
    return results to weights
}
