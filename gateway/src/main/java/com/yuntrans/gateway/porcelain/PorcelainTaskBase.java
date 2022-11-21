/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:31
 */
package com.yuntrans.gateway.porcelain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.measure.MeasureRecord;
import com.yuntrans.common.status.YuntransStatus;
import com.yuntrans.common.utils.Json;
import com.yuntrans.common.utils.JsonException;
import com.yuntrans.gateway.core.config.GatewayStatus;
import com.yuntrans.gateway.core.context.GatewayTaskContext;
import com.yuntrans.gateway.core.service.GatewayTaskBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class PorcelainTaskBase extends GatewayTaskBase<PorcelainEvent> implements PorcelainTask {


    protected final PorcelainTaskListener listener;

    protected PorcelainTaskBase(GatewayTaskContext context, PorcelainTaskListener listener) {
        super(context, listener);
        this.listener = listener;
    }

    public List<MeasureRecord> getMeasures() {
        return null;
    }

    protected <TDirective> TDirective parseDirective(PorcelainDirective directive, Class<TDirective> clazz) throws YuntransException {
        if (directive.getPayload() == null)
            throw YuntransStatus.MESSAGE_INVALID.modify(
                    String.format("Missing payload for directive '%s'!", directive.getName())).toException();
        try {
            return (TDirective) Json.toObject((JsonNode)directive.getPayload(), clazz);
        } catch (JsonException e) {
            String msg = "Invalid payload for event '" + directive.fullName() + "'!";
            throw GatewayStatus.DIRECTIVE_INVALID.modify(msg, e).toException();
        }
    }

    protected void triggerPorcelainEvent(String name, boolean isFinal) {
        PorcelainEvent evt = new PorcelainEvent(name, YuntransStatus.SUCCESS, null);
        if (isFinal) {
            triggerOnCompleted(evt);
        } else if (this.listener != null) {
            this.listener.onEvent(evt);
        }
    }

    protected void triggerPorcelainEvent(String name, boolean isFinal, Object event) {
        ObjectNode payload = Json.toTreeObjectOrNull(event);
        if (payload == null)
            log.error("Failed to convert event to json tree: name={}, value={}", name, Json.toStringOrEmpty(event));
        PorcelainEvent evt = new PorcelainEvent(name, YuntransStatus.SUCCESS, payload);
        if (isFinal) {
            triggerOnCompleted(evt);
        } else if (this.listener != null) {
            this.listener.onEvent(evt);
        }
    }
}
