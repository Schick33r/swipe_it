package zandb.software.swipeit.data.property.exceptions;

public class PropertyDoesNotBelongToLoggedInUser extends Exception {

    public PropertyDoesNotBelongToLoggedInUser(String message) {
        super(message);
    }
}
