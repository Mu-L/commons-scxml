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
package org.apache.commons.scxml2.model;

import org.apache.commons.scxml2.ActionExecutionContext;
import org.apache.commons.scxml2.SCXMLExpressionException;

/**
 * The class in this SCXML object model that corresponds to the
 * &lt;elseif&gt; SCXML element.
 */
public class ElseIf extends Action {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * An conditional expression which can be evaluated to true or false.
     */
    private String cond;

    /**
     * Constructs a new instance.
     */
    public ElseIf() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {
        // nothing to do, the <if> container will take care of this
    }

    /**
     * Gets the conditional expression.
     *
     * @return the cond.
     */
    public final String getCond() {
        return cond;
    }

    /**
     * Sets the conditional expression.
     *
     * @param cond The cond to set.
     */
    public final void setCond(final String cond) {
        this.cond = cond;
    }

}

