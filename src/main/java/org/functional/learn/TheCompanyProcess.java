package org.functional.learn;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Problem statement:</b> Given a list of names, some of which consist of a single character,
 * you are asked to return a comma-delimited string
 * <br>
 * <br>#1 With the single letter names removed
 * <br>#2 With each name capitalized.
 */

public class TheCompanyProcess
{
    private final List<String> listOfNames = Arrays.asList("neal", "s", "stu", "j", "rich", "bob", "aiden", "j", "ethan",
                                                           "liam", "mason", "noah", "lucas", "jacob", "jayden", "jack", null) ;

    private String cleanUpNames(List<String> rawNames)
    {
        if(null == rawNames) { return StringUtils.EMPTY ; }
        return rawNames.stream()
                       .filter(name -> null != name)
                       .filter(name -> name.length() > 1)
                       .map(name -> StringUtils.capitalize(name))
                       .collect(Collectors.joining(","));
    }

    public static void main(String[] args)
    {
        TheCompanyProcess tcp = new TheCompanyProcess();
        String namesAsCSV = tcp.cleanUpNames(tcp.listOfNames);

        System.out.printf("#-- Filtered names: %s %n",namesAsCSV);
    }
}
