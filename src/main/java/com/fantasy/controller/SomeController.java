package com.fantasy.controller;

import com.fantasy.objects.XmlManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Djelu on 20.09.2017.
 */

@RestController
public class SomeController {
    private XmlManager xmlManager;

    public void setXmlManager(XmlManager xmlManager) {
        this.xmlManager = xmlManager;
    }

    @RequestMapping("/tagbody")
    public String getTagBody(String name){
        String result;

        if(xmlManager==null){
            xmlManager = new XmlManager();
        }
        result = xmlManager.getValueFromTag(name);

        return result;
    }
}
