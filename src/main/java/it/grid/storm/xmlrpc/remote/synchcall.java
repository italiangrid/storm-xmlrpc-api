package it.grid.storm.xmlrpc.remote;


import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

/**
 * @author baltico
 */
public interface synchcall
{

    public Map<String, Object> ping(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> ls(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> prepareToPut(Map<String, Object> inputParam) throws XmlRpcFault;
    
    public Map<String, Object> prepareToPutStatus(Map<String, Object> parameters) throws XmlRpcFault;

    public Map<String, Object> putDone(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> releaseFiles(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> extendFileLifeTime(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> abortRequest(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> abortFiles(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> reserveSpace(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> getSpaceMetaData(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> getSpaceTokens(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> ReleaseSpace(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> mkdir(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> rmdir(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> rm(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> mv(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> prepareToGet(Map<String, Object> inputParam) throws XmlRpcFault;

    public Map<String, Object> prepareToGetStatus(Map<String, Object> parameters) throws XmlRpcFault;

}
