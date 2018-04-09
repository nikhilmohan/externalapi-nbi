/**
 *
 *     Copyright (c) 2017 Orange.  All rights reserved.
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

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExecutionTask {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long internalId;

    private String orderItemId;

    private String reliedTasks;

    private int nbRetries;

    private Date lastAttemptDate;

    @OneToOne
    private ServiceOrderInfoJson serviceOrderInfoJson;

    public ServiceOrderInfoJson getServiceOrderInfoJson() {
        return serviceOrderInfoJson;
    }

    public void setServiceOrderInfoJson(ServiceOrderInfoJson serviceOrderInfoJson) {
        this.serviceOrderInfoJson = serviceOrderInfoJson;
    }

    public Date getLastAttemptDate() {
        return lastAttemptDate;
    }

    public void setLastAttemptDate(Date lastAttemptDate) {
        this.lastAttemptDate = lastAttemptDate;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getReliedTasks() {
        return reliedTasks;
    }

    public void setReliedTasks(String reliedTasks) {
        this.reliedTasks = reliedTasks;
    }

    public int getNbRetries() {
        return nbRetries;
    }

    public void setNbRetries(int nbRetries) {
        this.nbRetries = nbRetries;
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExecutionTask that = (ExecutionTask) o;
        return nbRetries == that.nbRetries && Objects.equals(internalId, that.internalId)
                && Objects.equals(orderItemId, that.orderItemId) && Objects.equals(reliedTasks, that.reliedTasks)
                && Objects.equals(lastAttemptDate, that.lastAttemptDate)
                && Objects.equals(serviceOrderInfoJson, that.serviceOrderInfoJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, orderItemId, reliedTasks, nbRetries, lastAttemptDate, serviceOrderInfoJson);
    }
}
