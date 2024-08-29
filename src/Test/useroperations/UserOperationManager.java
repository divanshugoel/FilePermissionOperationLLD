package Test.useroperations;

import Test.document.Document;
import Test.user.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserOperationManager {
    Map<Integer, User> userMap;
    Map<Integer, Document> doucmentMap;

    public UserOperationManager() {
        this.userMap = new HashMap<>();
        this.doucmentMap = new HashMap<>();
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public Map<Integer, Document> getDoucmentMap() {
        return doucmentMap;
    }

    public void setDoucmentMap(Map<Integer, Document> doucmentMap) {
        this.doucmentMap = doucmentMap;
    }

    public void createDocument(User fromUser, Document document) {
        if (fromUser.getDocumentPermissionMap().containsKey(document.getDocId())) {
            System.out.println("Document already created by User:" + fromUser.getUsername());
            return;
        }

        document.setOwnerId(fromUser.getUserId());
        Set<String> permissions = new HashSet<>();
        permissions.add("create");
        permissions.add("read");
        permissions.add("update");
        permissions.add("delete");
        fromUser.getDocumentPermissionMap().put(document.getDocId(), permissions);
        this.doucmentMap.put(document.getDocId(), document);
        System.out.println(fromUser.getUsername() + " is now the owner of the document " + document.getName());
    }

    public void grantAccess(User fromUser, User toUser, Document document, String permission) {
        if (document.getOwnerId() != fromUser.getUserId()) {
            System.out.println(fromUser.getUsername() + " is not allowed to perform grant operation as user is not the owner of " + document.getName());
            return;
        }
        else if (document.hasGlobalGrant()) {
            System.out.println(document.getName() + " is already having global access");
            return;
        }
        //TODO:: create and delete permissions can't be granted
        if (!toUser.getDocumentPermissionMap().containsKey(document.getDocId())) {
            toUser.getDocumentPermissionMap().put(document.getDocId(), new HashSet<String>());
        }
        toUser.getDocumentPermissionMap().get(document.getDocId()).add(permission);
        System.out.println(toUser.getUsername() + " is now allowed " + permission + " operation for document " + document.getName() + " given by user " + fromUser.getUsername());
    }

    public String readDoc(User fromUser, Document document) {
        if (document.hasGlobalGrant()) {
            System.out.println(document.getName() + " has global access so user " + fromUser.getUsername() + " can perform read operation");
            return document.getContent();
        }
        if (!fromUser.getDocumentPermissionMap().containsKey(document.getDocId())) {
            System.out.println(fromUser.getUsername() + " is not allowed to read " + document.getName());
        } else {
            Set<String> permissionSet = fromUser.getDocumentPermissionMap().get(document.getDocId());
            for (String permission : permissionSet) {
                if ("read".equalsIgnoreCase(permission)) {
                    for (Map.Entry<Integer, Document> entry : doucmentMap.entrySet()) {
                        if (document.getDocId() == entry.getKey()) {
                            return entry.getValue().getContent();
                        } else {
//                                System.out.println("Document does not exist");
                        }
                    }
                }
            }
        }
        System.out.println(fromUser.getUsername() + " is not allowed read permission for document " + document.getName());

        return "";
    }

    public String writeDoc(User fromUser, Document document, String content) {
        if (document.hasGlobalGrant()) {
            System.out.println(document.getName() + " has global access so user " + fromUser.getUsername() + " can perform write operation");
            for (Map.Entry<Integer, Document> entry : doucmentMap.entrySet()) {
                if (document.getDocId() == entry.getKey()) {
                    entry.getValue().setContent(content);
                    return entry.getValue().getContent();
                }
            }
        }
        //TODO :: check wheter the doc exist in the db
        if (!fromUser.getDocumentPermissionMap().containsKey(document.getDocId())) {
            System.out.println(fromUser.getUsername() + " is not allowed to perform write operation on " + document.getName());
        } else {
            Set<String> permissionSet = fromUser.getDocumentPermissionMap().get(document.getDocId());
            for (String permission : permissionSet) {
                if ("update".equalsIgnoreCase(permission)) {
                    for (Map.Entry<Integer, Document> entry : doucmentMap.entrySet()) {
                        if (document.getDocId() == entry.getKey()) {
                            entry.getValue().setContent(content);
                            return entry.getValue().getContent();
                        }
                    }
                }
            }
        }
        System.out.println(fromUser.getUsername() + " is not allowed to perform write operation on " + document.getName());
        return "";
    }

    public void deleteDoc(User fromUser, Document document) {
        if (document.getOwnerId() == fromUser.getUserId()) {
            for (Map.Entry<Integer, Document> entry : doucmentMap.entrySet()) {
                if (document.getDocId() == entry.getKey()) {
                    doucmentMap.remove(document.getDocId());
                }
            }
            if (fromUser.getDocumentPermissionMap().containsKey(document.getDocId())) {
                fromUser.getDocumentPermissionMap().remove(document.getDocId());
            }
        } else {
            System.out.println(fromUser.getUsername() + " is not allowed to perform delete operation on " + document.getName());
        }
    }

    public void grantGlobalAccess(User fromUser, Document document) {
        if (document.getOwnerId() == fromUser.getUserId()) {
            document.setGlobalGrant(true);
            for (Map.Entry<Integer, Document> entry : doucmentMap.entrySet()) {
                if (document.getDocId() == entry.getKey()) {
                    doucmentMap.put(document.getDocId(),document);
                }
            }
            System.out.println(fromUser.getUsername() + " granted global access to document " + document.getName());
        } else {
            System.out.println(fromUser.getUsername() + " is not allowed to perform global grant operation on " + document.getName());
        }
    }
}


