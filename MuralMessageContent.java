public class MuralMessageContent {
    private int senderID;
    private String senderUsername;
    private String message;


    public MuralMessageContent() {
    }


    public MuralMessageContent(int senderID, String senderUsername, String message) {
        this.senderID = senderID;
        this.senderUsername = senderUsername;
        this.message = message;
    }


    public int getSenderID() {
        return this.senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getSenderUsername() {
        return this.senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return 
            "-------------------------------------\n" +
            senderUsername + " disse:\n" +
            message +"\n" + 
            "-------------------------------------\n";
    }

}