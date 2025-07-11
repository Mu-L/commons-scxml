/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.scxml2.env.groovy;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.MissingPropertyException;

/**
 * Delegating Groovy Binding class which delegates all variables access to its GroovyContext
 */
public class GroovyContextBinding extends Binding implements Serializable {

    private static final long serialVersionUID = 1L;

    private final GroovyContext context;

    public GroovyContextBinding(final GroovyContext context) {
        if (context == null) {
            throw new IllegalArgumentException("Parameter context may not be null");
        }
        this.context = context;
    }

    GroovyContext getContext() {
        return context;
    }

    @Override
    public Object getProperty(final String property) {
        return getVariable(property);
    }

    @Override
    public Object getVariable(final String name) {
        final Object result = context.get(name);
        if (result == null && !context.has(name)) {
            throw new MissingPropertyException(name, this.getClass());
        }
        return result;
    }

    @Override
    public Map<String, Object> getVariables() {
        return new LinkedHashMap<>(context.getVars());
    }

    @Override
    public boolean hasVariable(final String name) {
        return context.has(name);
    }

    @Override
    public void setProperty(final String property, final Object newValue) {
        setVariable(property, newValue);
    }

    @Override
    public void setVariable(final String name, final Object value) {
        if (context.has(name)) {
            context.set(name, value);
        } else {
            context.setLocal(name, value);
        }
    }
}
