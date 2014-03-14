/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.PhilippRosmann.AbstractJC.requests;

/**
 *
 * @author Philipp
 */
public abstract class ByteJCRequest<T> implements JCRequest
{
    private final byte[] data;
    private byte[] response = null;

    public ByteJCRequest(byte[] data)
    {
        this.data = data;
    }
    
    @Override
    public byte[] getBytesToSend()
    {
        return data;
    }

    @Override
    public boolean hasResponse()
    {
        return response!=null;
    }

    @Override
    public byte[] getResponseBytes()
    {
        return response;
    }

    @Override
    public void addResponseBytes(byte[] response)
    {
        this.response = response;
    }

    @Override
    public void eraseResponse()
    {
        response = null;
    }

    public abstract T getResponse();
    
}
