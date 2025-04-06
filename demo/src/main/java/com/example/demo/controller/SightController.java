// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.demo.controller;

import com.example.demo.crawler.KeelungSightsCrawler;
import com.example.demo.model.Sight;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/SightAPI"})
public class SightController {
    private KeelungSightsCrawler crawler = new KeelungSightsCrawler();

    public SightController() {
    }

    @GetMapping
    public List<Sight> getSightsByZone(@RequestParam String zone) {
        List<Sight> sightsArray = this.crawler.getItems(zone);
        List<Sight> sightsList = new ArrayList();
        Iterator var4 = sightsArray.iterator();

        while(var4.hasNext()) {
            Sight sight = (Sight)var4.next();
            sightsList.add(sight);
        }

        return sightsList;
    }
}
