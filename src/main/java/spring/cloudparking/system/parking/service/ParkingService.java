package spring.cloudparking.system.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import spring.cloudparking.system.exception.ParkingExceptionFactory;
import spring.cloudparking.system.parking.model.Parking;
import spring.cloudparking.system.parking.repository.ParkingRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService
{
    ParkingRepository repository;
    ParkingExceptionFactory parkingExceptionFactory;
    //-------------------------------------------------------------------------------------
    static UUID uuid = UUID.randomUUID();
    static private
    String getUUID()
    {
        return uuid.toString();
    }

    //-------------------------------------------------------------------------------------
    @Autowired
    public ParkingService(ParkingRepository repository, ParkingExceptionFactory parkingExceptionFactory)
    {
        this.repository = repository;
        this.parkingExceptionFactory = parkingExceptionFactory;
    }

    //-------------------------------------------------------------------------------------
    public final
    List<Parking> findAll()
    {
        return repository.findAll();
    }

    //-------------------------------------------------------------------------------------
    public final
    Parking add(@NonNull Parking parking)
    {
        parking.setEntry(LocalDateTime.now());

        return repository.save(parking);
    }

    //-------------------------------------------------------------------------------------
    public final
    Parking getById(@NonNull Long id)
    {
        Optional<Parking> optional = repository.findById(id);
        if(optional.isEmpty())
            throw this.parkingExceptionFactory.createNotFoundException("Parking", "id", id);

        return optional.get();
    }

    //-------------------------------------------------------------------------------------
    public final
    boolean existsById(@NonNull Long id)
    {
        return repository.existsById(id);
    }

    //-------------------------------------------------------------------------------------
    public final
    Parking updateById(@NonNull Long id, @NonNull Parking parking)
    {
        Parking oldParking = this.getById(id);

        oldParking.setEntry(parking.getEntry());
        oldParking.setColor(parking.getColor());
        oldParking.setState(parking.getState());
        oldParking.setModel(parking.getModel());
        oldParking.setBill(parking.getBill());

        return repository.save(oldParking);
    }

    //-------------------------------------------------------------------------------------
    public final
    Parking parkingExit(@NonNull Long id)
    {
        Parking parking = this.getById(id);

        parking.setExit(LocalDateTime.now());
        parking.setBill(getBill(parking.getEntry(), parking.getExit()));

        return repository.save(parking);
    }

    //-------------------------------------------------------------------------------------
    private static double getBill(LocalDateTime entry, LocalDateTime exit)
    {
        Duration duration = Duration.between(entry, exit);

        long seconds = duration.getSeconds();

        long hours = seconds / 3600;
        long minutes = ((seconds % 3600) / 60);

        final float HOUR_PRICE = 10;
        return ( HOUR_PRICE * hours + (HOUR_PRICE/4 * (int)(minutes/15L)) );
    }
}
