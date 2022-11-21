/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:23
 */
package com.yuntrans.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class GatewayContextUtil {

    public static final String FIELD_MODE_NEAR = "nearfield";

    public static final String FIELD_MODE_FAR = "farfield";

    private static final String DEVICE_UUID_PATH = "/device/uuid";

    private static final String DEBUG_FLAG_PATH = "/custom/debug";

    private static final String NUI_FIELD_MODE_PATH = "/custom/nui_fieldmode";

    private static final String DIALECT_CONFIG_PATH = "/custom/dialect";

    private static final String DEVICE_RID_PATH = "/custom/rid";

    public static final String ALIPAY_UID_PATH = "/custom/alipay_uid";

    public static final String ENCRYPT_DEVICE_INFO_PATH = "/device/e";

    public static final String SDK_VERSION_PATH = "/sdk/version";

    public static final String CLIENT_TIME_PATH = "/system/time";

    public static final String ENCRYPT_SIGN_PATH = "/device/s";

    private static final String BRIDGE_GROUP_CONFIG_PATH = "/bridge/group";

    private static final String BRIDGE_NAMESPACE_CONFIG_PATH = "/bridge/namespace";

    private static final String BRIDGE_LOCALE_CONFIG_PATH = "/bridge/locale";

    private static final String BRIDGE_MODEL_CONFIG_PATH = "/bridge/model";

    public static final String FIELD_CUSTOM = "custom";

    public static final String FIELD_NBEST = "nbest";

    public static final String CUSTOM_NBEST_PATH = "/custom/nbest";

    private static final String DEFAULT_BRIDGE_GROUP = "default";

    private static final String DEFAULT_BRIDGE_LOCALE = "zh-CN";

    private static final String DEFAULT_BRIDGE_MODEL = "default";

    public static String getString(ObjectNode clientContext, String nodePath) {
        if (clientContext == null)
            return "";
        try {
            JsonNode fieldNode = clientContext.at(nodePath);
            return Optional.<String>ofNullable((fieldNode == null) ? "" : fieldNode.textValue()).orElse("");
        } catch (Exception e) {
            log.error("Bad json path " + nodePath, e);
            return "";
        }
    }

    private static boolean getBoolean(ObjectNode clientContext, String nodePath) {
        if (clientContext == null)
            return false;
        JsonNode jsonNode = clientContext.at(nodePath);
        return (jsonNode != null && "true".equalsIgnoreCase(jsonNode.textValue()));
    }

    public static String getDeviceId(ObjectNode clientContext) {
        return getString(clientContext, "/device/uuid");
    }

    public static boolean isDebugOn(ObjectNode clientContext) {
        return getBoolean(clientContext, "/custom/debug");
    }

    public static String getNuiFieldMode(ObjectNode clientContext) {
        return getString(clientContext, "/custom/nui_fieldmode");
    }

    public static String getDialect(ObjectNode clientContext) {
        return getString(clientContext, "/custom/dialect");
    }

    public static String getSourceFrom(ObjectNode clientContext) {
        String sourceFrom = getString(clientContext, "/custom/autonav/source_from");
        if (sourceFrom == null || sourceFrom.length() == 0)
            return getString(clientContext, "/custom/source_from");
        return sourceFrom;
    }

    public static boolean useBridgeService(ObjectNode clientContext) {
        if (null == clientContext)
            return false;
        JsonNode bridgeNamespace = clientContext.at("/bridge/namespace");
        return (null != bridgeNamespace && !bridgeNamespace.isMissingNode());
    }

    public static String getBridgeGroup(ObjectNode clientContext) {
        String bridgeGroup = getString(clientContext, "/bridge/group");
        if (bridgeGroup == null || bridgeGroup.isEmpty())
            return "default";
        return bridgeGroup;
    }

    public static String getBridgeNamespace(ObjectNode clientContext) {
        return getString(clientContext, "/bridge/namespace");
    }

    public static String getBridgeLocale(ObjectNode clientContext) {
        String bridgeLocale = getString(clientContext, "/bridge/locale");
        if (bridgeLocale == null || bridgeLocale.isEmpty())
            return "zh-CN";
        return bridgeLocale;
    }

    public static String getBridgeModel(ObjectNode clientContext) {
        String bridgeModel = getString(clientContext, "/bridge/model");
        if (bridgeModel == null || bridgeModel.isEmpty())
            return "default";
        return bridgeModel;
    }

    public static String getRid(ObjectNode clientContext) {
        return getString(clientContext, "/custom/rid");
    }

    public static String getNbest(ObjectNode clientContext) {
        return getString(clientContext, "/custom/nbest");
    }

    public static ObjectNode setNbest(ObjectNode clientContext, String nbestJson) {
        clientContext = Optional.<ObjectNode>ofNullable(clientContext).orElse(Json.newTreeObject());
        ObjectNode customNode = (ObjectNode) Optional.ofNullable(clientContext.get("custom")).orElse(Json.newTreeObject());
        customNode.put("nbest", nbestJson);
        clientContext.replace("custom", (JsonNode)customNode);
        return clientContext;
    }
}
