package com.acs560.Movie_Analyzer_Vaadin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import com.acs560.Movie_Analyzer_Vaadin.repositories.TypeRepository;
import com.acs560.Movie_Analyzer_Vaadin.requests.TypeRequest;
import com.acs560.Movie_Analyzer_Vaadin.services.TypeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<TypeEntity> getAllTypes() {
        return StreamSupport.stream(typeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void addType(TypeEntity typeEntity) {
        typeRepository.save(typeEntity);
    }

    @Override
    public void updateType(int id, TypeEntity typeEntity) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot update. Type not found with ID: " + id);
        }
        typeEntity.setTypeId(id);
        typeRepository.save(typeEntity);
    }

    @Override
    public void deleteType(int id) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Type not found with ID: " + id);
        }
        typeRepository.deleteById(id);
    }

    @Override
    public List<TypeEntity> getByType(String type) {
        return StreamSupport.stream(typeRepository.findAll().spliterator(), false)
                .filter(typeEntity -> typeEntity.getType().toLowerCase().contains(type.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public TypeEntity getTypeById(int id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found with ID: " + id));
    }

    @Override
    public void addType(TypeRequest tr) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setType(tr.getName()); 
        typeRepository.save(typeEntity);
    }

    @Override
    public void updateType(int id, TypeRequest tr) {
        TypeEntity existingType = getTypeById(id); 
        existingType.setType(tr.getName());
        typeRepository.save(existingType);
    }
}
