package Test.user;

import java.util.Map;
import java.util.Set;

/**
 * User
 * <p>
 * String username
 * <p>
 * isOwner
 * Permissions
 * (C,R,U,D)
 * Map<doc,Set<String>permission>
 * <p>
 * <p>
 * Fnx-
 * createDoc(doc)
 * grantAccess(fromUser,toUser,doc,permission) → Exception handling
 * readDoc(fromUser,doc)
 * String content= writeDoc(fromUser,doc, content)
 * deleteDoc(fromUser,doc)
 * grantGloabalAccess(fromUser,global,doc)
 */

public class User {
    private Integer userId;
    private String username;
    private boolean isOwner;
    private Map<Integer, Set<String>> documentPermissionMap;

    public User(String username, Integer userId, Map<Integer, Set<String>> documentPermissionMap) {
        this.username = username;
        this.userId = userId;
        this.documentPermissionMap = documentPermissionMap;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public Map<Integer, Set<String>> getDocumentPermissionMap() {
        return documentPermissionMap;
    }

    public void setDocumentPermissionMap(Map<Integer, Set<String>> documentPermissionMap) {
        this.documentPermissionMap = documentPermissionMap;
    }
}
