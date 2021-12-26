import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sample
{
    public static void main(String[] args)
    {
        try
        {
            Process process = Runtime.getRuntime().exec("pwd");
            printResults(process);
        } catch (IOException e)
        {
            System.out.println("IO EXCEPTION");
        }
    }
    public static void printResults(Process process) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
