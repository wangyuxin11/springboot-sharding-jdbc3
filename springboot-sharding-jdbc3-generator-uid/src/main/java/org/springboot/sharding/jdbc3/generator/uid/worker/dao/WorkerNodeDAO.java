package org.springboot.sharding.jdbc3.generator.uid.worker.dao;

import org.springboot.sharding.jdbc3.generator.uid.worker.entity.WorkerNodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * DAO for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Repository
public interface WorkerNodeDAO {

    /**
     * Get {@link WorkerNodeEntity} by node host
     * 
     * @param host
     * @param port
     * @return
     */
    WorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * Add {@link WorkerNodeEntity}
     * 
     * @param workerNodeEntity
     */
    void addWorkerNode(WorkerNodeEntity workerNodeEntity);

}
