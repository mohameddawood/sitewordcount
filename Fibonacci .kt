package com.mctech.single_activity.presentation


private fun recursiveFibonacci(fabNumber: Int): Int {
    if (fabNumber <= 1)
        return fabNumber;
    return recursiveFibonacci(fabNumber - 1) + recursiveFibonacci(fabNumber - 2);
}

private fun iterativeFibonacci(fabNumber: Int): Int {
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
    println("fab recursive is ${recursiveFibonacci(9)}")
    println("fab iterative is ${iterativeFibonacci(9)}")

}
