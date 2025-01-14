/**
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
package io.streamnative.pulsar.handlers.mqtt;

import io.streamnative.pulsar.handlers.mqtt.support.MQTTMetricsProvider;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.broker.PulsarService;
import org.apache.pulsar.broker.authentication.AuthenticationProvider;

/**
 * Main class for mqtt service.
 */
@Slf4j
public class MQTTService {

    @Getter
    private MQTTServerConfiguration serverConfiguration;
    @Getter
    private PulsarService pulsarService;
    @Getter
    private Map<String, AuthenticationProvider> authProviders;

    @Getter
    private final MQTTMetricsProvider metricsProvider;

    public MQTTService(PulsarService pulsarService, MQTTServerConfiguration serverConfiguration,
                       Map<String, AuthenticationProvider> authProviders) {
        this.serverConfiguration = serverConfiguration;
        this.pulsarService = pulsarService;
        this.authProviders = authProviders;
        this.metricsProvider = new MQTTMetricsProvider(serverConfiguration);
        this.pulsarService.addPrometheusRawMetricsProvider(metricsProvider);
    }
}
