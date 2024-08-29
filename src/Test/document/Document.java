package Test.document;

public class Document {
    private Integer docId;
    private String name;
    private String content;
    private Integer ownerId;
    private boolean hasGlobalGrant;

    public Document(Integer docId, String name, String content) {
        this.docId = docId;
        this.name = name;
        this.content = content;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public boolean hasGlobalGrant() {
        return hasGlobalGrant;
    }

    public void setGlobalGrant(boolean globalGrant) {
        hasGlobalGrant = globalGrant;
    }
}
