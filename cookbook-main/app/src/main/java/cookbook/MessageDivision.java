package cookbook;

/**
 * MessageDivision creates a object containing the message data.
 */
public class MessageDivision {

    private final int id;
    private final String receiver;
    private final String recipe;
    private final String text;
    private final String sender_id;

    public MessageDivision (int id, String recipe, String receiver, String text, String sender_id) {
        this.id = id;
        this.receiver = receiver;
        this.recipe = recipe;
        this.text = text;
        this.sender_id = sender_id;
    }

    // Return the message id
    public int getID() {
        return id;
    }

    // Return the receiver display name
    public String getReceiver() {
        return receiver;
    }

    // Return the recipe name
    public String getRecipe() {
        return recipe;
    }

    // Return the message text
    public String getText() {
        return text;
    }

    // Return the sender id
    public String getSenderID() {
        return sender_id;
    }
}
