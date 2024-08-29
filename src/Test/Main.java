package Test;

import Test.document.Document;
import Test.user.User;
import Test.useroperations.UserOperationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Map<Integer, Set<String>> documentPermissionMapForUser1 = new HashMap<>();
        Map<Integer, Set<String>> documentPermissionMapForUser2 = new HashMap<>();
        User user1 = new User("divanshu", 1, documentPermissionMapForUser1);
        User user2 = new User("debanshu", 2, documentPermissionMapForUser2);

        Document document1 = new Document(1, "divanshuDoc", "divanshu,document,1");
        Document document2 = new Document(2, "debanshuDoc", "debanshu");

        UserOperationManager userOperationManager = new UserOperationManager();

        userOperationManager.getDoucmentMap().put(1, document1);
        userOperationManager.getDoucmentMap().put(2, document2);

        userOperationManager.getUserMap().put(1, user1);
        userOperationManager.getUserMap().put(2, user2);

        System.out.println("Expected: ");
        System.out.println(" divanshu is the owner of the divanshuDoc");
        System.out.println("output");
        userOperationManager.createDocument(user1, document1);

        System.out.println("Expected: ");
        System.out.println(" debanshu is the owner of the debanshu");
        System.out.println("output");
        userOperationManager.createDocument(user2, document2);

        System.out.println();
        System.out.println("grant access calls");

        System.out.println();

        //grant access calls
        System.out.println("Expected: ");
        System.out.println(" debanshu has read access of the divanshuDoc");
        System.out.println("output");
        userOperationManager.grantAccess(user1, user2, document1, "read");

        System.out.println("Expected: ");
        System.out.println(" divanshu has read access of the debanshu");
        System.out.println("output");
        userOperationManager.grantAccess(user2, user1, document2, "read");

        System.out.println("Expected: ");
        System.out.println(" debanshu is not allowed to grant read access for divanshuDoc");
        System.out.println("output");
        userOperationManager.grantAccess(user2, user1, document1, "read");


        //read calls
        System.out.println();
        System.out.println("read calls");
        System.out.println();


        userOperationManager.readDoc(user1, document1);
        userOperationManager.readDoc(user2, document2);
        userOperationManager.readDoc(user2, document1);

        System.out.println();
        System.out.println("write calls");
        System.out.println();

        userOperationManager.writeDoc(user1, document1, "divanshu,document,2");
        userOperationManager.writeDoc(user2, document1, "divanshu,document,2");

        System.out.println();
        System.out.println("delete calls");
        System.out.println();
        userOperationManager.deleteDoc(user2, document2);
        userOperationManager.deleteDoc(user2, document1);

        System.out.println();
        System.out.println("global grant calls");
        System.out.println();
        userOperationManager.grantGlobalAccess(user1, document1);

    }
}
