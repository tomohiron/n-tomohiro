package twitterclient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StatusListServiceAsync {

	void getStatusList(String id, String password,
			AsyncCallback<TwitterStatus[]> callback);

	void sendStatus(String id, String password, String status,
			AsyncCallback<String> callback);

}
