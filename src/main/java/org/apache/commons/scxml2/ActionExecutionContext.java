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
package org.apache.commons.scxml2;

import org.apache.commons.logging.Log;
import org.apache.commons.scxml2.model.EnterableState;
import org.apache.commons.scxml2.model.SCXML;

/**
 * ActionExecutionContext providing restricted access to the SCXML model, instance and services needed
 * for the execution of {@link org.apache.commons.scxml2.model.Action} instances
 */
public class ActionExecutionContext {

    /**
     * The SCXML execution context this action exection context belongs to
     */
    private final SCXMLExecutionContext exctx;

    /**
     * Constructor
     * @param exctx The SCXML execution context this action execution context belongs to
     */
    public ActionExecutionContext(final SCXMLExecutionContext exctx) {
        this.exctx = exctx;
    }

    /**
     * @return the SCXML Execution Logger for the application
     */
    public Log getAppLog() {
        return exctx.getAppLog();
    }

    /**
     * @param state an EnterableState
     * @return the context for an EnterableState
     */
    public Context getContext(final EnterableState state) {
        return exctx.getScInstance().getContext(state);
    }

    /**
     * @return the error reporter
     */
    public ErrorReporter getErrorReporter() {
        return exctx.getErrorReporter();
    }

    /**
     * @return The evaluator.
     */
    public Evaluator getEvaluator() {
        return exctx.getEvaluator();
    }

    /**
     * @return the event dispatcher
     */
    public EventDispatcher getEventDispatcher() {
        return exctx.getEventDispatcher();
    }

    /**
     * @return the global context
     */
    public Context getGlobalContext() {
        return exctx.getScInstance().getGlobalContext();
    }

    /**
     * @return the I/O Processor for the internal event queue
     */
    public SCXMLIOProcessor getInternalIOProcessor() {
        return exctx;
    }

    /**
     * @return the state machine
     */
    public SCXML getStateMachine() {
        return exctx.getStateMachine();
    }
}