package org.springboot.sharding.jdbc3.generator.uid;

import org.springboot.sharding.jdbc3.generator.uid.exception.UidGenerateException;

/**
 * Represents a unique id generator.
 *
 * @author yutianbao
 */
public interface UidGenerator {

    /**
     * Get a unique ID
     *
     * @return UID
     * @throws UidGenerateException
     */
    long getUID() throws UidGenerateException;

    /**
     * Parse the UID into elements which are used to generate the UID. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @param uid
     * @return Parsed info
     */
    String parseUID(long uid);

}
