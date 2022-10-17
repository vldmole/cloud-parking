package spring.cloudparking.restApi.parking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ParkingDto
{
    private Long id;
    private String license;
    private String state;
    private String model;
    private String color;
    @JsonFormat(pattern = "dd/mm/yyy hh:mm")
    private LocalDateTime entry;
    @JsonFormat(pattern = "dd/mm/yyy hh:mm")
    private LocalDateTime exit;
    private Double bill;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }

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

    public LocalDateTime getEntry()
    {
        return entry;
    }

    public void setEntry(LocalDateTime entry)
    {
        this.entry = entry;
    }

    public LocalDateTime getExit()
    {
        return exit;
    }

    public void setExit(LocalDateTime exit)
    {
        this.exit = exit;
    }

    public Double getBill()
    {
        return bill;
    }

    public void setBill(Double bill)
    {
        this.bill = bill;
    }
}
