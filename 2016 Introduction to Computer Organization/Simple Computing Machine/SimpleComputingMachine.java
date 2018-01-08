
/**
 * Justin Seda
 * 02/10/2016
 * 
 * Creates a Simple Computing Machine that takes data from opcode.txt and uses that data
 * to access specific commands then prints it into output.txt.
 */
import java.io.*;
import java.util.*;
public class SimpleComputingMachine
{
    public static void main(String [] args)throws FileNotFoundException
    {
        File f = new File("opcode.txt");
        Scanner in = new Scanner(f);
        PrintWriter pw = new PrintWriter("output.txt");
        
        //accumulator is variables[0]
        int[] variables = new int[9];
        //Initialize everything to 0
        for(int i = 0; i < variables.length; i++)
        {
            variables[i] = 0;
        }

        while(in.hasNextLine())
        {
            String userInput = in.nextLine();
            Scanner sin = new Scanner(userInput);
            String operation = sin.next();
            int value = sin.nextInt();

            //If value > 90 user wants a variable, not a literal value
            if(value > 90)
            {
                if (!operation.equals("21"))
                {
                    value = variables[(value - 90) % 9];//We want the position
                }
                else
                {
                    value = (value - 90) % 9;//We want the value
                }
            }
            
            //Store to accumulator
            if(operation.equals("01"))
            {
                variables[0] = value;
            }

            //Store accumulator to variable
            else if(operation.equals("21"))
            {
                variables[value] = variables[0];
            }

            //Add variable to accummulator
            else if (operation.equals("05"))
            {
                variables[0] = variables[0] + value;        
            }

            //Subtract variable to accummulator
            else if (operation.equals("06"))
            {
                variables[0] = variables[0] - value;                
            }

            //Mult. variable to acummulator
            else if (operation.equals("0B"))
            {
                variables[0] = variables[0] * value;
            }

            //Div. variable to accumulator
            else if (operation.equals("0C"))
            {
                variables[0] = variables[0] / value;                
            }

            //Print variable
            else if (operation.equals("99"))
            {              
                pw.println(value);   
            }
        }
         pw.close();
         in.close();
    }
}

