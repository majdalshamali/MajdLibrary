package com.lib.demolibrary.service;

import com.lib.demolibrary.dto.PatronsDto;

import java.util.List;

public interface IPatronService {

    void createPatron(PatronsDto patronsDto);

    List<PatronsDto> listOfPatron();

    PatronsDto fetchPatron(Long id);

    boolean updatePatron(Long id, PatronsDto patronsDto);

    boolean deletePatron(Long id);
}
