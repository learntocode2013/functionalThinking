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
    public static IntStream factorsOf(final int num)
    {
        return IntStream.range(1,num+1)
                        .filter(potential -> num % potential == 0);
    }

    public static int aliquotSum(final int num)
    {
        return factorsOf(num).sum() - num ;
    }

    public static boolean isPerfect(final int num)
    {
        boolean result = aliquotSum(num) == num ;
        return result;
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
        NumberClassifier nc = new NumberClassifier();
        System.out.printf("%d is : perfect ? %b | is abundant ? %b | is deficient ? %b %n", 100,nc.isPerfect(100),
                          nc.isAbundant(100),nc.isDeficient(100));
    }
}
