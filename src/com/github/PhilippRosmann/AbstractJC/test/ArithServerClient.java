/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.PhilippRosmann.AbstractJC.test;

import com.github.PhilippRosmann.AbstractJC.AbstractJC;
import com.github.PhilippRosmann.AbstractJC.requests.StringJCRequest;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philipp
 */
public class ArithServerClient extends AbstractJC
{

    public ArithServerClient(String server) throws UnknownHostException
    {
        super(server, 55555);
    }
    
    private String send(String op, String ...params) throws IOException
    {
        String data = op.toUpperCase();
        for(int i=0; i<params.length;i++)
            data += " "+String.valueOf(params[i]);
        StringJCRequest request = new StringJCRequest(data);
        sendRequest(request);
        return request.getResponse();
    }
    
    private double opNumbers(String op , double ...numbers) throws Exception
    {
        String[] params = new String[numbers.length];
        for(int i=0; i<params.length; i++)
        {
            params[i] = String.valueOf(numbers[i]);
        }
        
        String res = send(op, params);
        if(!res.startsWith("RES:"))
            throw new Exception(res);
        
        return Double.parseDouble(res.substring(5));
    }
    
    public double add(double ...numbers) throws IOException, Exception
    {
        return opNumbers("ADD", numbers);
    }
    
    public double sub(double ...numbers) throws IOException, Exception
    {
        return opNumbers("SUB", numbers);
    }
    
    public double mul(double ...numbers) throws IOException, Exception
    {
        return opNumbers("MUL", numbers);
    }
    
    public double div(double ...numbers) throws IOException, Exception
    {
        return opNumbers("DIV", numbers);
    }
    
    public double sqrt(double ...numbers) throws IOException, Exception
    {
        return opNumbers("SQRT", numbers);
    }
    
    public static void main(String[] args)
    {
        try
        {
            AbstractJC.setLogLevel(Level.WARNING);
            ArithServerClient asc = new ArithServerClient("localhost");
            
            double result = asc.add(2.0,3.0,4.0);
            System.out.println("res1: "+result);
            
            result = asc.sub(2.0,3.0,4.0);
            System.out.println("res2: "+result);
            
            result = asc.mul(2.0,3.0,4.0);
            System.out.println("res3: "+result);
            
            result = asc.div(2.0,3.0,4.0);
            System.out.println("res4: "+result);
            
            result = asc.sqrt(4.0);
            System.out.println("res5: "+result);
            
            result = asc.sqrt(2.0,3.0,4.0);
            System.out.println("res6: "+result);
            
            //result = 
        }
        catch (Exception ex)
        {
            Logger.getLogger(ArithServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
