/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.PhilippRosmann.AbstractJC;


import com.github.PhilippRosmann.AbstractJC.requests.JCRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philipp
 */
public abstract class AbstractJC
{
    private static final Logger LOG = Logger.getLogger(AbstractJC.class.getName());
    
    private final InetAddress target;
    private final int port;
    
    public static synchronized void setLogLevel(Level newLevel) throws SecurityException
    {
        LOG.setLevel(newLevel);
    }

    public AbstractJC(String server,int serverport) throws UnknownHostException
    {
        target = Inet4Address.getByName(server);
        port = serverport;
    }
    
    protected JCRequest sendRequest(JCRequest request) throws IOException
    {
        LOG.log(Level.INFO, "Connecting to {0}:{1} ...", 
            new Object[]{target.getHostName(), String.valueOf(port)});
        
        try (Socket socket = new Socket(target, port))
        {
            LOG.info("Sending Request ...");
            {
                OutputStream os = socket.getOutputStream();
                os.write(request.getBytesToSend());
                os.flush();
                socket.shutdownOutput();
            }
            
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            {
                InputStream is = socket.getInputStream();
                byte data;

                do
                {
                    data = (byte)is.read();
                    if(data!=-1)
                    {
                        buffer.put(data);
                    }
                }
                while(data!=-1);
            }
            
            LOG.log(Level.INFO, 
                "received {0} Bytes as Response ...", 
                String.valueOf(buffer.position()));
            
            {
                byte[] response;
                response = new byte[buffer.position()];
                
                System.arraycopy(buffer.array(), 0, response, 0, buffer.position());
                
                request.addResponseBytes(response);
            }
        }
        LOG.info("Successfully terminated Connection ");
        
        return request;
    }
    
    
}
