package twitterclient.server;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitterclient.client.StatusListService;
import twitterclient.client.TwitterStatus;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StatusListServiceImpl extends RemoteServiceServlet implements
		StatusListService {

	@Override
	public TwitterStatus[] getStatusList(String id, String password)
			throws Exception {

		Twitter twitter = new Twitter(id, password);
		List<Status> statuses;
		try {
			statuses = twitter.getFriendsTimeline();
		} catch (TwitterException e) {
			throw new Exception(e.getMessage(), e);
		}

		int size = statuses.size();
		TwitterStatus[] statusList = new TwitterStatus[size];

		for (int i = 0; i < size; ++i) {
			Status status = statuses.get(i);
			statusList[i] = new TwitterStatus(status.getUser().getName(),
					status.getText());
		}

		return statusList;
	}

	@Override
	public String sendStatus(String id, String password, String status)
			throws Exception {

		Twitter twitter = new Twitter(id, password);
		Status result = null;
		try {
			result = twitter.updateStatus(status);
		} catch (TwitterException e) {
			throw new Exception(e.getMessage(), e);
		}

		return result.getText();
	}

}
