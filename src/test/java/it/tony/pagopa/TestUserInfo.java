package it.tony.pagopa;

import org.junit.Test;

public class TestUserInfo {

    @Test
    public void testEmptyLogin() {
        UserInfo userInfo = new UserInfo();
        int currentSize = userInfo.userList().size();
        userInfo.login(null);
        assert userInfo.getName().contains("defaultUser");
    }

    @Test
    public void testUserList() {
        UserInfo userInfo = new UserInfo();
        int currentSize = userInfo.userList().size();
        userInfo.login("antonio");
        assert userInfo.userList().size() == currentSize + 1;
    }

    @Test
    public void testExistingLogin() {
        UserInfo user1 = new UserInfo();
        user1.login("test");
        assert user1.getName().equals("test");
        UserInfo user2 = new UserInfo();
        user2.login("test");
        assert !user2.getName().equals("test");
        assert user2.getName().contains("test");
    }

}

