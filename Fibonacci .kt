package com.mctech.single_activity.presentation


private fun recursiveFabonacci(fabNumber: Int): Int {
    if (fabNumber <= 1)
        return fabNumber;
    return recursiveFabonacci(fabNumber - 1) + recursiveFabonacci(fabNumber - 2);
}

private fun iterativeFabonacci(fabNumber: Int): Int {
    val numbers = arrayListOf<Int>()
    var i = 2
    numbers.add(0)
    numbers.add(1)
    while (i <= fabNumber) {
        numbers.add(numbers[i - 1] + numbers[i - 2])
        i++
    }
    return numbers[numbers.lastIndex]
}



fun main(args: Array<String>) {
    println("fab recursive is ${recursiveFabonacci(9)}")
    println("fab iterative is ${iterativeFabonacci(9)}")

}
