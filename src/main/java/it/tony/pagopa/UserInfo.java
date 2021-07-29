package it.tony.pagopa;

import java.util.*;
import java.util.stream.Collectors;

public class UserInfo {

    private static final Map<String, UserInfo> userDB = new HashMap<>();
    private String name;

    public String getName() {
        return name;
    }

    public UserInfo login(String username) {
        if (username == null || username.length() == 0) {
            username = "defaultUser";
        }
        this.name = username;
        if (userDB.containsKey(username)) {
            //If the user exists in the database change his name
            Random r = new Random();
            int randomInt = r.nextInt(1000000) + 1;
            this.name += randomInt;
        }
        userDB.put(this.name, this);
        return this;
    }

    public void logout() {
        userDB.remove(this.name);
    }

    public Map userList() {
        Set<Map.Entry<String, UserInfo>> entries = userDB.entrySet();
        HashMap<String, UserInfo> shallowCopy = (HashMap<String, UserInfo>) entries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return shallowCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return name.equals(userInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


