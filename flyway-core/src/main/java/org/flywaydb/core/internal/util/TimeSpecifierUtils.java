/*
 * Copyright (C) Red Gate Software Ltd 2010-2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.util;

import org.flywaydb.core.api.FlywayException;

public final class TimeSpecifierUtils {
    public static Long parseToSeconds(String value) {
        if (value.isEmpty()) {
            throw new NumberFormatException("time specifier is empty");
        }

        char specifier = value.charAt(value.length() - 1);
        if (Character.isDigit(specifier)) {
            return Long.parseLong(value); // No specifier assumes seconds
        }

        long number = Long.parseLong(value.substring(0, value.length() - 1));
        switch (specifier) {
            case 's' -> {
                return number;
            }
            case 'm' -> {
                return number * 60;
            }
            case 'h' -> {
                return number * 60 * 60;
            }
            case 'd' -> {
                return number * 60 * 60 * 24;
            }
        }
        throw new NumberFormatException("unknown time specifier " + specifier);
    }

    public static Long tryParseToSeconds(String value) {
        try {
            return value != null ? parseToSeconds(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}