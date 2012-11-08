package it.grid.storm.xmlrpc.outputdata;


import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.srm.types.TSizeInBytes;

public class PtGOutputData extends FileTransferOutputData
{

    protected TSizeInBytes size = null;
    protected TLifeTimeInSeconds pinLifetime = null;

    public PtGOutputData(String surl, String turl, TReturnStatus status, TRequestToken token) throws IllegalArgumentException
    {
        super(surl, turl, status, token);
    }

    /**
     * @return the size
     */
    public TSizeInBytes getSize()
    {
        return size;
    }

    /**
     * @return the pinLifetime
     */
    public TLifeTimeInSeconds getPinLifetime()
    {
        return pinLifetime;
    }

    public void setSize(TSizeInBytes size)
    {
        this.size = size;
        
    }

    public void setPinLifetime(TLifeTimeInSeconds pinLifetime)
    {
       this.pinLifetime = pinLifetime;
    }

}
