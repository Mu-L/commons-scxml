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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.scxml2.ActionExecutionContext;
import org.apache.commons.scxml2.Context;
import org.apache.commons.scxml2.Evaluator;
import org.apache.commons.scxml2.EventBuilder;
import org.apache.commons.scxml2.SCXMLExpressionException;
import org.apache.commons.scxml2.TriggerEvent;
import org.apache.commons.scxml2.semantics.ErrorConstants;

/**
 * The class in this SCXML object model that corresponds to the
 * &lt;if&gt; SCXML element, which serves as a container for conditionally
 * executed elements. &lt;else&gt; and &lt;elseif&gt; can optionally
 * appear within an &lt;if&gt; as immediate children, and serve to partition
 * the elements within an &lt;if&gt;.
 */
public class If extends Action implements ActionsContainer {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * An conditional expression which can be evaluated to true or false.
     */
    private String cond;

    /**
     * The set of executable elements (those that inheriting from
     * Action) that are contained in this &lt;if&gt; element.
     */
    private final List<Action> actions;

    /**
     * The boolean value that dictates whether the particular child action
     * should be executed.
     */
    private boolean execute;

    /**
     * Constructs a new instance.
     */
    public If() {
        this.actions = new ArrayList<>();
        this.execute = false;
    }

    /**
     * Add an Action to the list of executable actions contained in
     * this &lt;if&gt;.
     *
     * @param action The action to add.
     */
    @Override
    public final void addAction(final Action action) {
        if (action != null) {
            this.actions.add(action);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {
        final EnterableState parentState = getParentEnterableState();
        final Context ctx = exctx.getContext(parentState);
        final Evaluator eval = exctx.getEvaluator();
        Boolean rslt;
        try {
            rslt = eval.evalCond(ctx, cond);
            if (rslt == null) {
                if (exctx.getAppLog().isDebugEnabled()) {
                    exctx.getAppLog().debug("Treating as false because the cond expression was evaluated as null: '"
                            + cond + "'");
                }
                rslt = Boolean.FALSE;
            }
        } catch (final SCXMLExpressionException e) {
            rslt = Boolean.FALSE;
            exctx.getInternalIOProcessor().addEvent(new EventBuilder(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT).build());
            exctx.getErrorReporter().onError(ErrorConstants.EXPRESSION_ERROR, "Treating as false due to error: "
                    + e.getMessage(), this);
        }
        execute = rslt;
        // The "if" statement is a "container"
        for (final Action aa : actions) {
            if (execute && !(aa instanceof ElseIf)) {
                aa.execute(exctx);
            } else if (execute && aa instanceof ElseIf) {
                break;
            } else if (aa instanceof Else) {
                execute = true;
            } else if (aa instanceof ElseIf) {
                execute = eval.evalCond(ctx, ((ElseIf) aa).getCond());
            }
        }
    }

    /**
     * Gets the executable actions contained in this &lt;if&gt;.
     *
     * @return the actions.
     */
    @Override
    public final List<Action> getActions() {
        return actions;
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

