package com.microservicesws.controller;

import com.microservicesws.model.CatalogItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        log.info("MovieCatalogController calling getCatalog {}", userId);

        // get all the rated movie IDs
        // for each movie Id, call the movie info service and get details
        // lastly, put them all together

        return Collections.singletonList(
                new CatalogItem("Transformers", "Test", 4)
        );
    }

}
