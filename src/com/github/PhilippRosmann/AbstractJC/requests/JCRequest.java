/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.PhilippRosmann.AbstractJC.requests;

/**
 *
 * @author Philipp
 */
public interface JCRequest
{
    public byte[] getBytesToSend();
    public boolean hasResponse();
    public void addResponseBytes(byte[] response);
    public void eraseResponse();
    public byte[] getResponseBytes();
    
}
