package zandb.software.swipeit.data.user.type;

public enum UserType {

  CLIENT("CLIENT"), SUPPLIER("SUPPLIER");

  private String type;

  private UserType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
