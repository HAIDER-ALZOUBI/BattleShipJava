import java.io.Serializable;

public class Message implements Serializable {
    public Message(String message){
        this.message = message;
    }
    static final long serialVersionUID = 42L;
    private String message;
    private String sender;
    private String recipient;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }
    //    Client recipient;
    public String toString(){
        return message;
    }
    public String getSender(){
        return sender;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setSender(String sender){
        this.sender = sender;
    }


}
