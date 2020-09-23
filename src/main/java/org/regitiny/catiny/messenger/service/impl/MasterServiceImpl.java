package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MasterService;
import org.regitiny.catiny.messenger.domain.Master;
import org.regitiny.catiny.messenger.repository.MasterRepository;
import org.regitiny.catiny.messenger.repository.search.MasterSearchRepository;
import org.regitiny.catiny.messenger.service.dto.MasterDTO;
import org.regitiny.catiny.messenger.service.mapper.MasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Master}.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService {

    private final Logger log = LoggerFactory.getLogger(MasterServiceImpl.class);

    private final MasterRepository masterRepository;

    private final MasterMapper masterMapper;

    private final MasterSearchRepository masterSearchRepository;

    public MasterServiceImpl(MasterRepository masterRepository, MasterMapper masterMapper, MasterSearchRepository masterSearchRepository) {
        this.masterRepository = masterRepository;
        this.masterMapper = masterMapper;
        this.masterSearchRepository = masterSearchRepository;
    }

    @Override
    public MasterDTO save(MasterDTO masterDTO) {
        log.debug("Request to save Master : {}", masterDTO);
        Master master = masterMapper.toEntity(masterDTO);
        master = masterRepository.save(master);
        MasterDTO result = masterMapper.toDto(master);
        masterSearchRepository.save(master);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Masters");
        return masterRepository.findAll(pageable)
            .map(masterMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MasterDTO> findOne(Long id) {
        log.debug("Request to get Master : {}", id);
        return masterRepository.findById(id)
            .map(masterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Master : {}", id);
        masterRepository.deleteById(id);
        masterSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MasterDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Masters for query {}", query);
        return masterSearchRepository.search(queryStringQuery(query), pageable)
            .map(masterMapper::toDto);
    }
}
