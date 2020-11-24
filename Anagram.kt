package com.mctech.single_activity.presentation


fun isAnagram(inputOne: CharArray, inputTwo: CharArray): Boolean {

    if (inputOne.size == inputTwo.size) {
        // sort(inputOne,inputTwo)
        val first = inputOne.sorted()
        val second = inputTwo.sorted()
        for (i in inputOne.indices)
            if (first[i] != second[i])
                return false
    } else return false

    return true
}

fun main(args: Array<String>) {
    val firstInput = "debit card".replace(" ", "").toLowerCase().toCharArray()
    val secondInput = "bad credit".replace(" ", "").toLowerCase().toCharArray()

    if (isAnagram(firstInput, secondInput))
        println("The Entered Values are anagram of each other")
    else
        println("The Entered Values are not anagram of each other")


}

private fun sort(vararg inputs: CharArray) {
    inputs.map { item ->
        item.apply { sort() }
    }
}