package com.philippevienne.assoapi;

import org.springframework.batch.item.ItemProcessor;

public class AssociationProcessor implements ItemProcessor<Association, Association> {
    @Override
    public Association process(Association association) throws Exception {
        return association;
    }
}
