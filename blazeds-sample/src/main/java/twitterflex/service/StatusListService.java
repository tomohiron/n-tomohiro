package twitterflex.service;

public interface StatusListService {

	TwitterStatus[] getStatusList(String id, String password) throws Exception;

	String sendStatus(String id, String password, String status)
			throws Exception;
}
