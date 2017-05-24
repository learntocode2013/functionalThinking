package org.functional.pitfalls;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutOfScopeExceptionWithClosures
{
    private InputStreamReader reader = null ;

    private boolean fileStreamIsOpen()
    {
        try
        {
            return reader.ready();
        }
        catch (IOException cause)
        {
            //ignore
        }
        return false;
    }

    protected Function<Void,String> createFileReaderClosure(Path filePath)
    {
        Function<Void,String> fileReader = null ;
        try(BufferedReader br = new BufferedReader(reader = new InputStreamReader(new FileInputStream(filePath.toFile()))))
        {
            fileReader = (v) -> {
                List<String> allLines = new ArrayList();
                String aLine = null;
                try
                {
                    // Defensive check for out of scope variables
                    if( null == reader || null == br )
                    {
                        System.out.printf("Detected usage of an invalid reference !!!!");
                        return StringUtils.EMPTY ;
                    }

                    // Some more check to ensure proper state before processing. Ugly, huh !
                    if(! fileStreamIsOpen())
                    {
                        System.out.printf("Input stream to file is already closed. Cannot proceed !!! %n");
                        // Commenting this will result in an error...
                        return StringUtils.EMPTY;
                    }

                    // Notice the BufferedReader reference being used from outer scope in here...
                    while( null != (aLine = br.readLine()) )
                    {
                        allLines.add(aLine);
                    }
                }
                catch (IOException cause)
                {
                    System.out.printf("fileReader failed to read file entries !!! %n");
                    cause.printStackTrace();
                }

                return allLines.stream().collect(Collectors.joining(System.lineSeparator()));
            } ;
        }
        catch (IOException cause)
        {
            System.out.printf("Error reading file %s %n",filePath);
        }
        return fileReader;
    }

    public static void main(String[] args)
    {
        Path toFile = Paths.get("/","Users","disen","Desktop","8L2KIO2G2A.txt"); // modify this path as appropriate
        OutOfScopeExceptionWithClosures oosewc = new OutOfScopeExceptionWithClosures();
        Function<Void, String> fileReaderClosure = oosewc.createFileReaderClosure(toFile);
        // Exception will be thrown from here due to lazy nature of closures
        fileReaderClosure.apply(null);
    }
}
