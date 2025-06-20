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
import org.apache.commons.scxml2.EventBuilder;
import org.apache.commons.scxml2.SCXMLExpressionException;
import org.apache.commons.scxml2.TriggerEvent;

/**
 * The class in this SCXML object model that corresponds to the
 * &lt;raise&gt; SCXML element.
 *
 * @since 2.0
 */
public class Raise extends Action {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The event to be generated.
     */
    private String event;

    /**
     * Constructs a new instance.
     */
    public Raise() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {

        if (exctx.getAppLog().isDebugEnabled()) {
            exctx.getAppLog().debug("<raise>: Adding event '" + event + "' to list of derived events.");
        }
        final TriggerEvent ev = new EventBuilder(event, TriggerEvent.SIGNAL_EVENT).build();
        exctx.getInternalIOProcessor().addEvent(ev);

    }

    /**
     * Gets the event.
     *
     * @return the event.
     */
    public final String getEvent() {
        return event;
    }

    /**
     * Sets the event.
     *
     * @param event The event to set.
     */
    public final void setEvent(final String event) {
        this.event = event;
    }
}