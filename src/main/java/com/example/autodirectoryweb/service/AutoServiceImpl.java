package com.example.autodirectoryweb.service;

import com.example.autodirectoryweb.entity.Auto;
import com.example.autodirectoryweb.repository.AutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AutoServiceImpl implements AutoService {

    private final AutoRepository repository;

    public AutoServiceImpl(AutoRepository repository) {
        this.repository = repository;
    }

    public List<Auto> getFilteredAll(Auto filter) {
        List<Auto> fullCars = getAll();


        return fullCars.stream().filter((c) -> {
            boolean booleanBrand = true;
            boolean booleanModel = true;
            boolean booleanCategory = true;
            boolean booleanRegistrationNumber = true;
            boolean booleanManufacturingYear = true;
            boolean booleanType = true;


            if (!Objects.equals(filter.getBrand(), "")) booleanBrand = Objects.equals(c.getBrand(), filter.getBrand());
            if (!Objects.equals(filter.getModel(), "")) booleanModel = Objects.equals(c.getModel(), filter.getModel());
            if (!Objects.equals(filter.getCategory(), "")) booleanCategory = Objects.equals(c.getCategory(), filter.getCategory());
            if (!Objects.equals(filter.getRegistrationNumber(), ""))
                booleanRegistrationNumber = Objects.equals(c.getRegistrationNumber(), filter.getRegistrationNumber());
            if (filter.getManufacturingYear() != null)
                booleanManufacturingYear = Objects.equals(c.getManufacturingYear(), filter.getManufacturingYear());
            if (filter.getType() != null) booleanType = Objects.equals(c.getType(), filter.getType());

            return booleanBrand && booleanModel && booleanCategory && booleanRegistrationNumber && booleanManufacturingYear && booleanType;
        }).toList();
    }

    @Override
    public List<Auto> getAll() {
        return repository.findAll();
    }

    @Override
    public Auto getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void create(Auto auto) {
        if (checkRegistrationNumberRepeat(auto)) {
            repository.create(auto);
        }


    }

    @Override
    public void update(Long id, Auto auto)
    {
        auto.setId(id);
        if (checkRegistrationNumberRepeat(auto)) {
            repository.update(id, auto);


        }
    }

    @Override
    public void delete(Long id)
    {
        repository.delete(id);
    }

    private boolean checkRegistrationNumberRepeat(Auto auto) {
        Auto findCar = repository.findByRegistrationNumber(auto.getRegistrationNumber());
        if (findCar.getId() == null) return true;
        return Objects.equals(auto.getId(), findCar.getId());

    }

}
