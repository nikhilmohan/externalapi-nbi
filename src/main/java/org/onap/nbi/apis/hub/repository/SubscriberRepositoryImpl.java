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

package org.onap.nbi.apis.hub.repository;

import org.onap.nbi.apis.hub.model.Event;
import org.onap.nbi.apis.hub.model.Subscriber;
import org.onap.nbi.apis.hub.service.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class SubscriberRepositoryImpl implements SubscriberFinder {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @Override
    public List<Subscriber> findSubscribersUsingEvent(Event event) {
        Criteria criteria = new Criteria();
        criteria.and("query.eventType").is(event.getEventType());
        criteriaBuilder.adjust(criteria, event);
        return mongoOperations.find(new Query(criteria), Subscriber.class);
    }
}
