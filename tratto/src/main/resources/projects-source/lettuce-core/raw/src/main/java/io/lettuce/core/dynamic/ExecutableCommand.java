/*
 * Copyright 2017-2024 the original author or authors.
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
package io.lettuce.core.dynamic;

import java.util.concurrent.ExecutionException;

/**
 * An executable command that can be executed calling {@link #execute(Object[])}.
 *
 * @author Mark Paluch
 * @since 5.0
 */
interface ExecutableCommand {

    /**
     * Executes the {@link ExecutableCommand} with the given parameters.
     *
     * @param parameters
     * @return
     */
    Object execute(Object[] parameters) throws ExecutionException, InterruptedException;

    /**
     * Returns the {@link CommandMethod}.
     *
     * @return
     */
    CommandMethod getCommandMethod();

}
