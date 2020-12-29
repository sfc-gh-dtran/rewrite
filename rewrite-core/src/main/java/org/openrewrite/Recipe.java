/*
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite;

import java.util.Set;
import java.util.regex.Pattern;

public interface Recipe {
    String getName();

    default FilterReply accept(EvalVisitor<?> visitor) {
        return visitor.validate().isValid() ? FilterReply.ACCEPT : FilterReply.DENY;
    }

    default <T extends Tree, R extends EvalVisitor<T>> R configure(R visitor) {
        return visitor;
    }

    Set<Pattern> getInclude();
    Set<Pattern> getExclude();

    enum FilterReply {
        ACCEPT, DENY, NEUTRAL
    }
}
