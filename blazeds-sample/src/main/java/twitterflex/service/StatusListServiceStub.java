package twitterflex.service;

import java.util.Date;

public class StatusListServiceStub implements StatusListService {

    @Override
    public TwitterStatus[] getStatusList(String id, String password) throws Exception {

        TwitterStatus[] statusList = new TwitterStatus[2];
        statusList[0] = new TwitterStatus(new Date(), "userA", "sample1");
        statusList[1] = new TwitterStatus(new Date(), "userB", "sample2");
        return statusList;
    }

    @Override
    public String sendStatus(String id, String password, String status) throws Exception {

        return "dummy";
    }

}
