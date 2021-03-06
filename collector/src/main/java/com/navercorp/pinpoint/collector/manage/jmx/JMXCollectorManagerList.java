/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.collector.manage.jmx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.navercorp.pinpoint.collector.manage.CollectorManager;
import com.navercorp.pinpoint.collector.manage.HandlerManager;

/**
 * @author Taejin Koo
 */
public class JMXCollectorManagerList {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{pinpoint_collector_properties['collector.admin.api.jmx.active'] ?: false}")
    private boolean isActive;

    @Autowired
    private HandlerManager handlerManager;

    public List<CollectorManager> getSupportList() {
        if (!isActive) {
            logger.warn("not activing jmx api for admin.");
            return Collections.emptyList();
        }
        
        List<CollectorManager> supportManagerList = new ArrayList<CollectorManager>();

        if (handlerManager != null) {
            supportManagerList.add(handlerManager);
        }

        return supportManagerList;
    }
    
}
