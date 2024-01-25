package com.lib.demolibrary.service.impl;

import com.lib.demolibrary.dto.PatronsDto;
import com.lib.demolibrary.entity.Patrons;
import com.lib.demolibrary.exception.ResourceAlreadyExistException;
import com.lib.demolibrary.exception.ResourceNotFoundException;
import com.lib.demolibrary.mapper.PatronsMapper;
import com.lib.demolibrary.repository.PatronsRepository;
import com.lib.demolibrary.service.IPatronService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lib.demolibrary.mapper.PatronsMapper.mapToPatron;
import static com.lib.demolibrary.mapper.PatronsMapper.mapToPatronDto;

@Service
@AllArgsConstructor
public class PatronsService implements IPatronService {

    private PatronsRepository patronsRepository;

    @Override
    public void createPatron(PatronsDto patronsDto) {
        Patrons patrons = mapToPatron(patronsDto, new Patrons());
        Optional<Patrons> optionalPatrons = patronsRepository.findByPatronID(patrons.getPatronID());
        if (optionalPatrons.isPresent()) {
            throw new ResourceAlreadyExistException("Patron already inserted with the given ID = " + patrons.getPatronID());
        }
        patronsRepository.save(patrons);
    }

    @Override
    public List<PatronsDto> listOfPatron() {
        List<Patrons> listOfPatron = patronsRepository.findAll();
        List<PatronsDto> patronsDtos = new ArrayList<>();
        if (!listOfPatron.isEmpty()) {
            for (Patrons patrons : listOfPatron) {
                patronsDtos.add(mapToPatronDto(patrons, new PatronsDto()));
            }
        }
        return patronsDtos;
    }

    @Override
    @Cacheable("patrons")
    public PatronsDto fetchPatron(Long id) {
        Patrons patrons = patronsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patron", "id", id.toString())
        );
        return mapToPatronDto(patrons, new PatronsDto());
    }

    @Override
    public boolean updatePatron(Long id, PatronsDto patronsDto) {
        Optional<Patrons> patrons = patronsRepository.findById(id);
        if (patrons.isEmpty()) {
            throw new ResourceNotFoundException("Patron", "ID", id.toString());
        }
        Patrons updatedPatron = mapToPatron(patronsDto, patrons.get());
        patronsRepository.save(updatedPatron);
        return true;
    }

    @Override
    public boolean deletePatron(Long id) {
        boolean isDeleted = false;
        Patrons patrons = patronsRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Patron","id",id.toString())
        );
        patronsRepository.delete(patrons);
        isDeleted = true;
        return isDeleted;
    }
}
