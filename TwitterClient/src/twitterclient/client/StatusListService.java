package twitterclient.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("statusList")
public interface StatusListService extends RemoteService {

	TwitterStatus[] getStatusList(String id, String password) throws Exception;

}
