/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.repository.common.configuration.swapper;

import org.apache.shardingsphere.infra.yaml.swapper.YamlSwapper;
import org.apache.shardingsphere.orchestration.repository.common.configuration.config.YamlOrchestrationRepositoryConfiguration;
import org.apache.shardingsphere.orchestration.repository.common.configuration.config.YamlOrchestrationConfiguration;
import org.apache.shardingsphere.orchestration.repository.api.config.OrchestrationRepositoryConfiguration;
import org.apache.shardingsphere.orchestration.repository.api.config.OrchestrationConfiguration;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Orchestration instance configuration YAML swapper.
 */
public final class OrchestrationConfigurationYamlSwapper implements YamlSwapper<YamlOrchestrationConfiguration, OrchestrationConfiguration> {
    
    @Override
    public YamlOrchestrationConfiguration swapToYamlConfiguration(final OrchestrationConfiguration configuration) {
        OrchestrationRepositoryConfigurationYamlSwapper swapper = new OrchestrationRepositoryConfigurationYamlSwapper();
        Map<String, YamlOrchestrationRepositoryConfiguration> configurationMap =
                configuration.getInstanceConfigurationMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> swapper.swapToYamlConfiguration(entry.getValue())));
        return new YamlOrchestrationConfiguration(configurationMap);
    }
    
    @Override
    public OrchestrationConfiguration swapToObject(final YamlOrchestrationConfiguration configuration) {
        OrchestrationRepositoryConfigurationYamlSwapper swapper = new OrchestrationRepositoryConfigurationYamlSwapper();
        Map<String, OrchestrationRepositoryConfiguration> instanceConfigurationMap =
                configuration.getOrchestrationRepositoryConfigurationMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> swapper.swapToObject(entry.getValue())));
        return new OrchestrationConfiguration(instanceConfigurationMap);
    }
}
