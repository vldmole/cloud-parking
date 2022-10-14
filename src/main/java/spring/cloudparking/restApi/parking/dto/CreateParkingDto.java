package spring.cloudparking.restApi.parking.dto;

public class CreateParkingDto
{
    private String license;
    private String state;
    private String model;
    private String color;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public CreateParkingDto(String license, String state, String model, String color)
    {
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }
}
