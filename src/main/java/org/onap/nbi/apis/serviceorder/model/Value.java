/**
 *     Copyright (c) 2018 Orange
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
/*
 * API ServiceOrder serviceOrder API designed for ONAP Beijing Release. This API is build from TMF
 * open API16.5 + applied TMF guideline 3.0
 *
 * OpenAPI spec version: 0.1.1_inProgress
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */


package org.onap.nbi.apis.serviceorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * Value is a descriptive structure for service characteristic
 */
@ApiModel(description = "Value is a descriptive structure for service characteristic")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen",
        date = "2018-02-19T14:00:30.767Z")
public class Value {
    @JsonProperty("@type")
    private String type = null;

    @JsonProperty("@schemaLocation")
    private String schemaLocation = null;

    @JsonProperty("serviceCharacteristicValue")
    private String serviceCharacteristicValue = null;

    public Value type(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     **/
    @JsonProperty("@type")
    @ApiModelProperty(value = "")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value schemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
        return this;
    }

    /**
     * @return schemaLocation
     **/
    @JsonProperty("@schemaLocation")
    @ApiModelProperty(value = "")
    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public Value serviceCharacteristicValue(String serviceCharacteristicValue) {
        this.serviceCharacteristicValue = serviceCharacteristicValue;
        return this;
    }

    /**
     * @return serviceCharacteristicValue
     **/
    @JsonProperty("serviceCharacteristicValue")
    @ApiModelProperty(value = "")
    public String getServiceCharacteristicValue() {
        return serviceCharacteristicValue;
    }

    public void setServiceCharacteristicValue(String serviceCharacteristicValue) {
        this.serviceCharacteristicValue = serviceCharacteristicValue;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value value = (Value) o;
        return Objects.equals(this.type, value.type) && Objects.equals(this.schemaLocation, value.schemaLocation)
                && Objects.equals(this.serviceCharacteristicValue, value.serviceCharacteristicValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, schemaLocation, serviceCharacteristicValue);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Value {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
        sb.append("    serviceCharacteristicValue: ").append(toIndentedString(serviceCharacteristicValue)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

