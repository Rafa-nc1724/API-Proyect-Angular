package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.TramoCreateUpdateDto;
import com.example.apihermandad.application.dto.TramoDto;
import com.example.apihermandad.application.mapper.TramoMapper;
import com.example.apihermandad.domain.entity.Tramo;
import com.example.apihermandad.domain.repository.TramoRepository;
import com.example.apihermandad.utils.MethodUtils;
import com.example.apihermandad.utils.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TramoService {

    private final TramoRepository trRepo;
    private final TramoMapper trMapper;

    TramoService(TramoRepository trRepo, TramoMapper trMapper) {
        this.trRepo = trRepo;
        this.trMapper = trMapper;
    }

    public List<TramoDto> findAll() {
        return trRepo.findAll()
                .stream()
                .map(trMapper::toDto)
                .toList();
    }

    public TramoDto findById(Integer id) {
        Tramo tr = trRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_TR
                ));
        return trMapper.toDto(tr);
    }

    public TramoDto create(TramoCreateUpdateDto dto){
        MethodUtils.validateDates(dto.getGoOut(), dto.getEnter());

        List<Tramo> existTr = trRepo.findAll();
        MethodUtils.validateNoOvverlap(dto.getGoOut(),dto.getEnter(),existTr);

        Tramo tr = Tramo.builder()
                .goOut(dto.getGoOut())
                .enter(dto.getEnter())
                .description(dto.getDescription())
                .build();

        return  trMapper.toDto(trRepo.save(tr));
    }

    public TramoDto update(Integer id, TramoCreateUpdateDto dto) {
        Tramo tr = trRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_TR
                ));
        MethodUtils.validateDates(dto.getGoOut(), dto.getEnter());
        List<Tramo> otherTr = trRepo.findAll();
        MethodUtils.validateNoOvverlap(dto.getGoOut(),dto.getEnter(),otherTr);

        tr.setGoOut(dto.getGoOut());
        tr.setEnter(dto.getEnter());
        tr.setDescription(dto.getDescription());

        return  trMapper.toDto(trRepo.save(tr));
    }

    public void delete(Integer id) {

        if(!trRepo.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    HttpMessage.NOT_FOUND_TR
            );
        }
        trRepo.deleteById(id);
    }
}
