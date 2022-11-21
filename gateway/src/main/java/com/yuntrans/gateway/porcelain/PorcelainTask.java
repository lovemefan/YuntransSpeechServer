/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:30
 */
package com.yuntrans.gateway.porcelain;

import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.measure.MeasureRecord;
import com.yuntrans.gateway.core.service.GatewayTask;

import java.util.List;
import java.util.Map;

public interface PorcelainTask extends GatewayTask<PorcelainEvent> {
    boolean isLeadingDirective(PorcelainDirective paramPorcelainDirective);

    Map<String, String> determineGroups(PorcelainDirective paramPorcelainDirective) throws YuntransException;

    void processDirective(PorcelainDirective paramPorcelainDirective) throws YuntransException;

    void processStream(byte[] paramArrayOfbyte) throws YuntransException;

    List<MeasureRecord> getMeasures();
}
