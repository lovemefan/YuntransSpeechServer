/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:08
 */
package com.yuntrans.common.utils;

import com.google.common.base.Splitter;
import java.util.List;
import java.util.Optional;
public class Endpoint {
    public static final String DELIMITER = ";";

    private String name;

    private EndpointType type;

    private String protocol;

    private String path;

    public Endpoint(String name, EndpointType type, String protocol, String path) {
        this.name = name;
        this.type = type;
        this.protocol = protocol;
        this.path = path;
    }

    public static EndpointBuilder builder() {
        return new EndpointBuilder();
    }

    public static class EndpointBuilder {
        private String name;

        private EndpointType type;

        private String protocol;

        private String path;

        public EndpointBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EndpointBuilder type(EndpointType type) {
            this.type = type;
            return this;
        }

        public EndpointBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public EndpointBuilder path(String path) {
            this.path = path;
            return this;
        }

        public Endpoint build() {
            return new Endpoint(this.name, this.type, this.protocol, this.path);
        }

        public String toString() {
            return "Endpoint.EndpointBuilder(name=" + this.name + ", type=" + this.type + ", protocol=" + this.protocol + ", path=" + this.path + ")";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(EndpointType type) {
        this.type = type;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Endpoint))
            return false;
        Endpoint other = (Endpoint)o;
        if (!other.canEqual(this))
            return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
            return false;
        Object this$type = getType(), other$type = other.getType();
        if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
            return false;
        Object this$protocol = getProtocol(), other$protocol = other.getProtocol();
        if ((this$protocol == null) ? (other$protocol != null) : !this$protocol.equals(other$protocol))
            return false;
        Object this$path = getPath(), other$path = other.getPath();
        return !((this$path == null) ? (other$path != null) : !this$path.equals(other$path));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Endpoint;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $type = getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        Object $protocol = getProtocol();
        result = result * 59 + (($protocol == null) ? 43 : $protocol.hashCode());
        Object $path = getPath();
        return result * 59 + (($path == null) ? 43 : $path.hashCode());
    }

    public Endpoint() {}

    public String getName() {
        return this.name;
    }

    public EndpointType getType() {
        return this.type;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getPath() {
        return this.path;
    }

    public static Endpoint parse(String endpoint) {
        String[] parts = endpoint.split(";");
        String name = parts[0];
        EndpointType type = EndpointType.HOST_PORT;
        int paramCount = 0;
        if (parts.length > ++paramCount)
            type = EndpointType.valueOf(parts[paramCount]);
        String protocol = null;
        if (parts.length > ++paramCount)
            protocol = parts[paramCount];
        String path = null;
        if (parts.length > ++paramCount)
            path = parts[paramCount];
        return new Endpoint(name, type, protocol, path);
    }

    public Optional<String> getGroup() {
        List<String> serviceInfo = Splitter.on('/').splitToList(this.path);
        return (serviceInfo.size() > 1) ? Optional.<String>of(serviceInfo.get(1)) : Optional.<String>empty();
    }

    public String toString() {
        return this.name + ";" + this.type + ";" + this.protocol + ";" + this.path;
    }
}

