/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.PhilippRosmann.AbstractJC.requests;

import java.util.Arrays;

/**
 *
 * @author Philipp
 */
public class StringJCRequest extends ByteJCRequest<String>
{

    public StringJCRequest(String data)
    {
        super(data.getBytes());
    }
    
    
    @Override
    public String getResponse()
    {
        return new String(getResponseBytes());
    }
    
}
