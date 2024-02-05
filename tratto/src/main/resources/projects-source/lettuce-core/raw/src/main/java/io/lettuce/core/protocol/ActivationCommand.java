/*
 * Copyright 2023-2024 the original author or authors.
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
package io.lettuce.core.protocol;

/**
 * Command wrapper to identify activation commands.
 *
 * @author Mark Paluch
 */
class ActivationCommand<K, V, T> extends CommandWrapper<K, V, T> {

    public ActivationCommand(RedisCommand<K, V, T> command) {
        super(command);
    }

    public static boolean isActivationCommand(RedisCommand<?, ?, ?> command) {

        if (command instanceof ActivationCommand) {
            return true;
        }

        while (command instanceof CommandWrapper) {
            command = ((CommandWrapper<?, ?, ?>) command).getDelegate();

            if (command instanceof ActivationCommand) {
                return true;
            }
        }

        return false;
    }

}
