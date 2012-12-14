package it.grid.storm.xmlrpc;
/*
*
*  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

import it.grid.storm.srm.types.ArrayOfTExtraInfo;
import it.grid.storm.srm.types.ArrayOfTSpaceToken;
import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TRetentionPolicyInfo;
import it.grid.storm.srm.types.TSizeInBytes;
import it.grid.storm.srm.types.TSpaceToken;
import it.grid.storm.xmlrpc.executors.AbortFilesExecutor;
import it.grid.storm.xmlrpc.executors.AbortRequestExecutor;
import it.grid.storm.xmlrpc.executors.LsExecutor;
import it.grid.storm.xmlrpc.executors.MkdirExecutor;
import it.grid.storm.xmlrpc.executors.MvExecutor;
import it.grid.storm.xmlrpc.executors.PingExecutor;
import it.grid.storm.xmlrpc.executors.PtGExecutor;
import it.grid.storm.xmlrpc.executors.PtPExecutor;
import it.grid.storm.xmlrpc.executors.SurlRequestStatusExecutor;
import it.grid.storm.xmlrpc.executors.PutDoneExecutor;
import it.grid.storm.xmlrpc.executors.ReleaseFilesExecutor;
import it.grid.storm.xmlrpc.executors.RmExecutor;
import it.grid.storm.xmlrpc.executors.RmdirExecutor;
import it.grid.storm.xmlrpc.outputdata.*;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import redstone.xmlrpc.XmlRpcProxy;

/**
 * @author Michele Dibenedetto
 *
 */
public class BackendApi
{

    private synchcall storm;

    /**
     * @param backendServer
     * @param backendPort
     * @throws Exception
     */
    public BackendApi(String backendServer, Long backendPort) throws ApiException
    {
        URL url;
        try
        {
            url = new URL("http://" + backendServer + ":" + backendPort + "/RPC2");
        } catch(MalformedURLException e)
        {
            throw new ApiException(e);
        }
        storm = (synchcall) XmlRpcProxy.createProxy(url, new Class[] { synchcall.class }, false);
    }

    /**
     * @param userDN the dn of the user 
     * @param userFQANS a list of not-null/not-empty fqan values
     * @return an object representing the operation result
     * @throws ApiException if is unable to perform the remote call
     * @throws IllegalArgumentException if the provided elements are invalid
     */
    public PingOutputData ping(String userDN, List<String> userFQANS) throws ApiException, IllegalArgumentException
    {
        return PingExecutor.execute(storm, userDN, userFQANS);
    }

    public PingOutputData ping(String userDN) throws ApiException, IllegalArgumentException
    {
        return PingExecutor.execute(storm, userDN);
    }
    
    public PingOutputData ping() throws ApiException, IllegalArgumentException
    {
        return PingExecutor.execute(storm);
    }
    
    public SurlArrayRequestOutputData putDone(String userDN, List<String> userFQANS, List<String> surls,
            TRequestToken requestToken) throws ApiException
    {
        return PutDoneExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }

    public SurlArrayRequestOutputData putDone(String userDN, List<String> surls,
            TRequestToken requestToken) throws ApiException
    {
        return PutDoneExecutor.execute(storm, userDN, surls, requestToken);
    }
    
    public SurlArrayRequestOutputData putDone(List<String> surls,
            TRequestToken requestToken) throws ApiException
    {
        return PutDoneExecutor.execute(storm, surls, requestToken);
    }
    
    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls);
    }
    
    public LsOutputData ls(String userDN, List<String> surls) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, surls);
    }
    
    public LsOutputData ls(List<String> surls) throws ApiException
    {
        return LsExecutor.execute(storm, surls);
    }

    /**
     * @param userDN
     * @param userFQANS
     * @return
     * @throws ApiException
     */
    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, recursion);
    }
    
    public LsOutputData ls(String userDN, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.execute(storm, userDN, surls, recursion);
    }
    
    public LsOutputData ls(List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.execute(storm, surls, recursion);
    }
    
    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, count);
    }

    public LsOutputData ls(String userDN, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, surls, count);
    }
    
    public LsOutputData ls(List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, surls, count);
    }
    
    public LsOutputData ls(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, recursion, count);
    }
    
    public LsOutputData ls(String userDN, 
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, surls, recursion, count);
    }
    
    public LsOutputData ls(List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, surls, recursion, count);
    }

    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls);
    }
    
    public LsOutputData lsDetailed(String userDN, List<String> surls) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, surls);
    }
    
    public LsOutputData lsDetailed(List<String> surls) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, surls);
    }

    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, recursion);
    }

    public LsOutputData lsDetailed(String userDN, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, surls, recursion);
    }
    
    public LsOutputData lsDetailed(List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.executeDetailed(storm, surls, recursion);
    }
    
    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, count);
    }
    
    public LsOutputData lsDetailed(String userDN, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, surls, count);
    }
    
    public LsOutputData lsDetailed(List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, surls, count);
    }
    
    public LsOutputData lsDetailed(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, recursion, count);
    }
    
    public LsOutputData lsDetailed(String userDN, 
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, surls, recursion, count);
    }
    
    public LsOutputData lsDetailed(List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, surls, recursion, count);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, String surl) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, surl);
    }
    
    public FileTransferOutputData prepareToPut(String surl) throws ApiException
    {
        return PtPExecutor.execute(storm, surl);
    }

    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, surl, transferProtocols);
    }
    
    public FileTransferOutputData prepareToPut(String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.execute(storm, surl, transferProtocols);
    }

    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, surl, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPut(String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, surl, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPut(String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, String surl) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, surl);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String surl) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, surl);
    }

    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, transferProtocols);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, surl, transferProtocols);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, surl, transferProtocols);
    }

    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, surl, desiredFileLifetime, desiredPinLifetime);
    }

    public FileTransferOutputData prepareToPutOverwrite(String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, surl, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }

    public FileTransferOutputData prepareToPutOverwrite(String userDN, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public RequestOutputData prepareToPutStatus(String userDN, List<String> userFQANS, String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, userFQANS, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }
    
    public RequestOutputData prepareToPutStatus(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, userFQANS, surl, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }

    public RequestOutputData prepareToPutStatus(String userDN, String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }
    
    public RequestOutputData prepareToPutStatus(String userDN, String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, surl, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }
    
    public RequestOutputData prepareToPutStatus(String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }
    
    public RequestOutputData prepareToPutStatus(String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, surl, SurlRequestStatusExecutor.SurlRequestType.PTP);
    }
    
    public PtGOutputData prepareToGet(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, userFQANS, surl);
    }

    public PtGOutputData prepareToGet(String userDN, String surl) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, surl);
    }
    
    public PtGOutputData prepareToGet(String surl) throws ApiException
    {
        return PtGExecutor.execute(storm, surl);
    }
    
    public PtGOutputData prepareToGet(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols);
    }
    
    public PtGOutputData prepareToGet(String userDN, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, surl, transferProtocols);
    }
    
    public RequestOutputData prepareToGetStatus(String userDN, List<String> userFQANS, String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, userFQANS, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }
    
    public RequestOutputData prepareToGetStatus(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, userFQANS, surl, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }

    public RequestOutputData prepareToGetStatus(String userDN, String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }
    
    public RequestOutputData prepareToGetStatus(String userDN, String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, userDN, surl, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }
    
    public RequestOutputData prepareToGetStatus(String surl,
            TRequestToken requestToken) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, surl, requestToken, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }
    
    public RequestOutputData prepareToGetStatus(String surl) throws ApiException
    {
        return SurlRequestStatusExecutor.execute(storm, surl, SurlRequestStatusExecutor.SurlRequestType.PTG);
    }
    
    public SurlArrayRequestOutputData releaseFiles(String userDN, List<String> userFQANS, List<String> surls, TRequestToken requestToken) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }
    
    public SurlArrayRequestOutputData releaseFiles(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, userDN, userFQANS, surls);
    }
    
    public SurlArrayRequestOutputData releaseFiles(String userDN, List<String> surls, TRequestToken requestToken) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, userDN, surls, requestToken);
    }

    public SurlArrayRequestOutputData releaseFiles(String userDN, List<String> surls) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, userDN, surls);
    }
    
    public SurlArrayRequestOutputData releaseFiles(List<String> surls, TRequestToken requestToken) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, surls, requestToken);
    }
    
    public SurlArrayRequestOutputData releaseFiles(List<String> surls) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, surls);
    }
    
    public OutputData extendFileLifeTime(String userDN, List<String> userFQANS, String surl, long fileLifetime) throws ApiException
    {
        throw new ApiException("Unable to perform extendFileLifeTime call. Not implemented");
    }
    
    public OutputData extendPinLifeTime(String userDN, List<String> userFQANS, TRequestToken requestToken, String surl, long pinLifetime) throws ApiException
    {
        throw new ApiException("Unable to perform extendPinLifeTime call. Not implemented");
    }

    public RequestOutputData abortRequest(String userDN, List<String> userFQANS, TRequestToken requestToken) throws ApiException
    {
        return AbortRequestExecutor.execute(storm, userDN, userFQANS, requestToken);
    }
    
    public RequestOutputData abortRequest(String userDN, TRequestToken requestToken) throws ApiException
    {
        return AbortRequestExecutor.execute(storm, userDN, requestToken);
    }

    public RequestOutputData abortRequest(TRequestToken requestToken) throws ApiException
    {
        return AbortRequestExecutor.execute(storm, requestToken);
    }
    
    public SurlArrayRequestOutputData abortFiles(String userDN, List<String> userFQANS, TRequestToken requestToken, List<String> surls) throws ApiException
    {
        return AbortFilesExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }

    public SurlArrayRequestOutputData abortFiles(String userDN, TRequestToken requestToken, List<String> surls) throws ApiException
    {
        return AbortFilesExecutor.execute(storm, userDN, surls, requestToken);
    }

    public SurlArrayRequestOutputData abortFiles(TRequestToken requestToken, List<String> surls) throws ApiException
    {
        return AbortFilesExecutor.execute(storm, surls, requestToken);
    }
    
    public OutputData reserveSpace(String userDN, List<String> userFQANS, String spaceAlias,
            TRetentionPolicyInfo retentionPolicyInfo, TSizeInBytes desiredSizeOfTotalSpace,
            TSizeInBytes desiredSizeOfGuaranteedSpace, TLifeTimeInSeconds desiredLifetimeOfReservedSpace,
            ArrayOfTExtraInfo storageSystemInfo) throws ApiException
    {
        throw new ApiException("Unable to perform reserveSpace call. Not implemented");
    }

    public OutputData reserveSpace(String userDN, List<String> userFQANS,
            TRetentionPolicyInfo retentionPolicyInfo, TSizeInBytes desiredSizeOfTotalSpace,
            TSizeInBytes desiredSizeOfGuaranteedSpace, TLifeTimeInSeconds desiredLifetimeOfReservedSpace,
            ArrayOfTExtraInfo storageSystemInfo) throws ApiException
    {
        throw new ApiException("Unable to perform reserveSpace call. Not implemented");
    }
    
    public OutputData getSpaceMetaData(String userDN, List<String> userFQANS, ArrayOfTSpaceToken arrayOfSpaceTokens) throws ApiException
    {
        throw new ApiException("Unable to perform getSpaceMetaData call. Not implemented");
    }

    public OutputData getSpaceTokens(String userDN, List<String> userFQANS, String spaceTokenDescription) throws ApiException
    {
        throw new ApiException("Unable to perform getSpaceTokens call. Not implemented");
    }

    public OutputData releaseSpace(String userDN, List<String> userFQANS, TSpaceToken spaceToken) throws ApiException
    {
        throw new ApiException("Unable to perform releaseSpace call. Not implemented");
    }

    public OutputData releaseSpaceForced(String userDN, List<String> userFQANS, TSpaceToken spaceToken) throws ApiException
    {
        throw new ApiException("Unable to perform releaseSpaceForced call. Not implemented");
    }
    
    public RequestOutputData mkdir(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return MkdirExecutor.execute(storm, userDN, userFQANS, surl);
    }

    public RequestOutputData mkdir(String userDN, String surl) throws ApiException
    {
        return MkdirExecutor.execute(storm, userDN, surl);
    }

    public RequestOutputData mkdir(String surl) throws ApiException
    {
        return MkdirExecutor.execute(storm, surl);
    }
    
    public RequestOutputData rmdir(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return RmdirExecutor.execute(storm, userDN, userFQANS, surl);
    }

    public RequestOutputData rmdir(String userDN, String surl) throws ApiException
    {
        return RmdirExecutor.execute(storm, userDN, surl);
    }
    
    public RequestOutputData rmdir(String surl) throws ApiException
    {
        return RmdirExecutor.execute(storm, surl);
    }
    
    public RequestOutputData rmdirRecursively(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return RmdirExecutor.executeRecursive(storm, userDN, userFQANS, surl);
    }

    public RequestOutputData rmdirRecursively(String userDN, String surl) throws ApiException
    {
        return RmdirExecutor.executeRecursive(storm, userDN, surl);
    }
    
    public RequestOutputData rmdirRecursively(String surl) throws ApiException
    {
        return RmdirExecutor.executeRecursive(storm, surl);
    }
    
    public RequestOutputData rm(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return RmExecutor.execute(storm, userDN, userFQANS, surls);
    }

    public RequestOutputData rm(String userDN, List<String> surls) throws ApiException
    {
        return RmExecutor.execute(storm, userDN, surls);
    }
    
    public RequestOutputData rm(List<String> surls) throws ApiException
    {
        return RmExecutor.execute(storm, surls);
    }
    
    public RequestOutputData mv(String userDN, List<String> userFQANS, String fromSurl, String toSurl) throws ApiException
    {
        return MvExecutor.execute(storm, userDN, userFQANS, fromSurl, toSurl);
    }
    
    public RequestOutputData mv(String userDN, String fromSurl, String toSurl) throws ApiException
    {
        return MvExecutor.execute(storm, userDN, fromSurl, toSurl);
    }
    
    public RequestOutputData mv(String fromSurl, String toSurl) throws ApiException
    {
        return MvExecutor.execute(storm, fromSurl, toSurl);
    }
}
