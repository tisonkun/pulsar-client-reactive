/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.pulsar.reactive.client.internal.adapter;

import java.util.Objects;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.impl.conf.ProducerConfigurationData;

final class ProducerCacheKey {

	private final PulsarClient pulsarClient;

	private final ProducerConfigurationData producerConfigurationData;

	private final Schema<?> schema;

	ProducerCacheKey(final PulsarClient pulsarClient, final ProducerConfigurationData producerConfigurationData,
			final Schema<?> schema) {
		this.pulsarClient = pulsarClient;
		this.producerConfigurationData = producerConfigurationData;
		this.schema = schema;
	}

	String getTopicName() {
		return (this.producerConfigurationData != null) ? this.producerConfigurationData.getTopicName() : null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProducerCacheKey that = (ProducerCacheKey) o;
		return (Objects.equals(this.pulsarClient, that.pulsarClient)
				&& Objects.equals(this.producerConfigurationData, that.producerConfigurationData)
				&& Objects.equals(this.schema, that.schema));
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.pulsarClient, this.producerConfigurationData, this.schema);
	}

}
