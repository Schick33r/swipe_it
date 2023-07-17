package zandb.software.swipeit.data.property.dto;

public class PropertyDTO {
    private long propertyId;

    private String name;

    private String description;

    private String country;

    private String city;

    private String postcode;

    private String address;

    private String addressNumber;

    private String size;

    private String numberOfRooms;

    private String rentCold;

    private String rentWarm;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getRentCold() {
        return rentCold;
    }

    public void setRentCold(String rentCold) {
        this.rentCold = rentCold;
    }

    public String getRentWarm() {
        return rentWarm;
    }

    public void setRentWarm(String rentWarm) {
        this.rentWarm = rentWarm;
    }
}
