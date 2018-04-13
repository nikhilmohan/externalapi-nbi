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
package org.onap.nbi.apis.serviceorder.model.orchestrator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ServiceOrderInfoJson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long internalId;

    private String serviceOrderId;

    @Lob
    private String serviceOrderInfoJson;

    public ServiceOrderInfoJson() {}

    public ServiceOrderInfoJson(String serviceOrderId, String serviceOrderInfoJson) {
        this.serviceOrderId = serviceOrderId;
        this.serviceOrderInfoJson = serviceOrderInfoJson;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceOrderInfoJson() {
        return serviceOrderInfoJson;
    }

    public void setServiceOrderInfoJson(String serviceOrderInfoJson) {
        this.serviceOrderInfoJson = serviceOrderInfoJson;
    }
}
