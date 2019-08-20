package org.springboot.sharding.jdbc3.dangdang.service;

import javax.annotation.Resource;

import org.springboot.sharding.jdbc3.generator.uid.UidGenerator;
import org.springframework.stereotype.Service;

@Service
public class UidGenService {

    @Resource
    private UidGenerator uidGenerator;

    public long getUid() {
        return uidGenerator.getUID();
    }
    
    
    
}