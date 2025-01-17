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

import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.ReaderBuilder;

class ReactiveReaderAdapterFactory {

	private final Supplier<PulsarClient> pulsarClientSupplier;

	ReactiveReaderAdapterFactory(Supplier<PulsarClient> pulsarClientSupplier) {
		this.pulsarClientSupplier = pulsarClientSupplier;
	}

	<T> ReactiveReaderAdapter<T> create(Function<PulsarClient, ReaderBuilder<T>> readerBuilderFactory) {
		return new ReactiveReaderAdapter<T>(this.pulsarClientSupplier, readerBuilderFactory);
	}

}
