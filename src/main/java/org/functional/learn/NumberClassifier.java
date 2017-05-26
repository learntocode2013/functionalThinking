package org.functional.learn;


import java.util.stream.IntStream;

/**
 * <b>Problem statement: </b> Given a number, determine if that number is one of the following:
 * <br>
 * <br>1. Perfect number   = (aliquot sum == number)
 * <br>2. Abundant number  = (aliquot sum > number)
 * <br>3. Deficient number = (aliquot sum < number)
 * <br>
 * <br>
 * <b>aliquot sum </b> is sum of the factorsOf of a number not including the number itself,
 * <br>which is nominally one of the factorsOf.
 */
public class NumberClassifier
{
    // Stateless function which concentrates on the what not how. No visible iteration.
    public static IntStream factorsOf(final int num)
    {
        return IntStream.range(1,num+1)
                        .filter(potential -> num % potential == 0);
    }

    // Stateless function which concentrates on the what not how. No visible iteration.
    public static int aliquotSum(final int num)
    {
        return factorsOf(num).sum() - num ;
    }

    public static boolean isPerfect(final int num)
    {
        return aliquotSum(num) == num ;
    }

    public static boolean isAbundant(final int num)
    {
        return aliquotSum(num) > num ;
    }

    public static boolean isDeficient(final int num)
    {
        return aliquotSum(num) < num ;
    }

    public static void main(String[] args)
    {
        int num = 100 ;
        System.out.printf("%d is : perfect ? %b | is abundant ? %b | is deficient ? %b %n",
                          num,
                          NumberClassifier.isPerfect(num),
                          NumberClassifier.isAbundant(num),
                          NumberClassifier.isDeficient(num));
    }
}
