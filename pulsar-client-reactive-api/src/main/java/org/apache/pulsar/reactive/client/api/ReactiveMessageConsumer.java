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

package org.apache.pulsar.reactive.client.api;

import java.util.function.Function;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.reactive.client.internal.api.ApiImplementationFactory;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive message consumer interface.
 *
 * @param <T> the message payload type.
 * @author Lari Hotari
 * @author Christophe Bornet
 */
public interface ReactiveMessageConsumer<T> {

	/**
	 * Consumes one message.
	 * @param messageHandler a {@link Function} to apply to the consumed message that
	 * returns a {@link MessageResult} which contains the acknowledgement or negative
	 * acknowledgement referencing the message id of the input message together with an
	 * optional return value object
	 * @return the value contained by the {@link MessageResult} returned by the message
	 * handler
	 * @param <R> the type of MessageResult returned by the message handler
	 */
	<R> Mono<R> consumeOne(Function<Message<T>, Publisher<MessageResult<R>>> messageHandler);

	/**
	 * Consumes messages continuously.
	 * @param messageHandler a {@link Function} to apply to the consumed messages that
	 * returns {@link MessageResult}s, each containing the acknowledgement or negative
	 * acknowledgement referencing the message id of the corresponding input messages
	 * together with an optional return value object
	 * @return the values contained by the {@link MessageResult}s returned by the message
	 * handler
	 * @param <R> the type of MessageResult returned by the message handler
	 */
	<R> Flux<R> consumeMany(Function<Flux<Message<T>>, Publisher<MessageResult<R>>> messageHandler);

	/**
	 * Creates a builder for building a {@link ReactiveMessagePipeline}.
	 * @return a builder for building a {@link ReactiveMessagePipeline}
	 */
	default ReactiveMessagePipelineBuilder<T> messagePipeline() {
		return ApiImplementationFactory.createReactiveMessageHandlerPipelineBuilder(this);
	}

	/**
	 * Creates the Pulsar Consumer and immediately closes it. This is useful for creating
	 * the Pulsar subscription that is related to the consumer. Nothing happens unless the
	 * returned Mono is subscribed.
	 * @return a Mono for consuming nothing
	 */
	Mono<Void> consumeNothing();

}
