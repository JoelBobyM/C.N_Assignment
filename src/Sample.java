import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Sample
{
    static Process process;
    public static void main(String[] args)
    {
        try
        {
            process = Runtime.getRuntime().exec("pwd");
            System.out.print("RESULT : ");
            printResults();
        } catch (IOException e)
        {
            System.out.println("IO EXCEPTION");
        }
    }
    public static void printResults() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
