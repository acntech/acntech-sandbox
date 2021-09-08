package no.acntech.sandbox.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.CreateQueryDto;
import no.acntech.sandbox.model.QueryDto;
import no.acntech.sandbox.model.QueryEntity;
import no.acntech.sandbox.model.UpdateQueryDto;
import no.acntech.sandbox.repository.QueryRepository;

@Service
public class QueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
    private final ObjectMapper objectMapper;
    private final ConversionService conversionService;
    private final QueryRepository queryRepository;

    public QueryService(final ObjectMapper objectMapper,
                        final ConversionService conversionService,
                        final QueryRepository queryRepository) {
        this.objectMapper = objectMapper;
        this.conversionService = conversionService;
        this.queryRepository = queryRepository;
    }

    public QueryDto get(final Long id) {
        try {
            final var queryEntity = queryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No query found for ID " + id));
            return conversionService.convert(queryEntity, QueryDto.class);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public QueryDto get(final String name) {
        try {
            final var queryEntity = queryRepository.findByName(name)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No query found for Name " + name));
            return conversionService.convert(queryEntity, QueryDto.class);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public List<QueryDto> find() {
        try {
            final var queryEntities = queryRepository.findAll();
            return queryEntities.stream()
                    .map(queryEntity -> conversionService.convert(queryEntity, QueryDto.class))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Transactional
    public QueryDto create(final CreateQueryDto createQuery) {
        try {
            final var queryEntity = conversionService.convert(createQuery, QueryEntity.class);
            Assert.notNull(queryEntity, "Query entity is null");
            final var savedQueryEntity = queryRepository.save(queryEntity);
            return conversionService.convert(savedQueryEntity, QueryDto.class);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Query with that name already exists", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Query with that name already exists", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Transactional
    public QueryDto update(final Long id, final UpdateQueryDto updateQuery) {
        try {
            final var queryString = objectMapper.writeValueAsString(updateQuery.getQuery());
            final var queryEntity = queryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No query found for ID " + id));
            queryEntity.setQuery(queryString);
            final var savedQueryEntity = queryRepository.save(queryEntity);
            return conversionService.convert(savedQueryEntity, QueryDto.class);
        } catch (IOException e) {
            LOGGER.error("Query is not correctly formatted JSON", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Query is not correctly formatted JSON", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Transactional
    public void delete(final Long id) {
        queryRepository.deleteById(id);
    }
}
