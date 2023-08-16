package com.charot.dhwebflux.controller;

import com.charot.dhwebflux.domain.model.ResourceObject;
import com.charot.dhwebflux.service.ResourceObjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceControllerTest {

    @Mock
    private ResourceObjectService service;

    @InjectMocks
    private ResourceController controller;


    @Test
    @DisplayName("Should return empty when the id does not exist")
    void getResourceObjectWhenIdDoesNotExist() {
        int id = 1;
        ResourceObject resourceObject = new ResourceObject(1, "value", "path");

        when(service.get(id)).thenReturn(Mono.empty());

        Mono<ResourceObject> result = controller.getResourceObject(id);

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();

        verify(service, times(1)).get(id);
    }

    @Test
    void getResourceObjectWhenIdExists() {
        int id = 1;
        ResourceObject resourceObject = new ResourceObject(id, "value", "path");

        when(service.get(id)).thenReturn(Mono.just(resourceObject));

        Mono<ResourceObject> result = controller.getResourceObject(id);

        StepVerifier.create(result)
                .expectNext(resourceObject)
                .verifyComplete();

        verify(service, times(1)).get(id);
    }

    @Test
    void getResourcesReturnsAllResourceObjects() {
        ResourceObject resource1 = new ResourceObject(1L, "Value 1", "/path1");
        ResourceObject resource2 = new ResourceObject(2L, "Value 2", "/path2");
        ResourceObject resource3 = new ResourceObject(3L, "Value 3", "/path3");

        when(service.getAll()).thenReturn(Flux.just(resource1, resource2, resource3));

        Flux<ResourceObject> result = controller.getResources();

        StepVerifier.create(result)
                .expectNext(resource1)
                .expectNext(resource2)
                .expectNext(resource3)
                .verifyComplete();

        verify(service, times(1)).getAll();
    }

    @Test
    void createResourceObjectWhenSaveFailsThenReturnInternalServerError() {
        ResourceObject object = new ResourceObject(1, "value", "path");

        when(service.save(object)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Long>> result = controller.createResourceObject(object);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                .verifyComplete();

        verify(service, times(1)).save(object);
    }

    @Test
    void createResourceObjectWhenSaveIsSuccessful() {
        ResourceObject object = new ResourceObject(1L, "value", "path");

        when(service.save(object)).thenReturn(Mono.just(1L));

        Mono<ResponseEntity<Long>> response = controller.createResourceObject(object);

        StepVerifier.create(response)
                .expectNextMatches(entity -> {
                    assertEquals(HttpStatus.OK, entity.getStatusCode());
                    assertEquals(1L, entity.getBody());
                    return true;
                })
                .verifyComplete();

        verify(service, times(1)).save(object);
    }
}