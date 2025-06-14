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
import org.apache.commons.scxml2.Context;
import org.apache.commons.scxml2.Evaluator;
import org.apache.commons.scxml2.SCXMLExpressionException;

/**
 * The class in this SCXML object model that corresponds to the
 * &lt;assign&gt; SCXML element.
 */
public class Assign extends Action implements ParsedValueContainer {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Left hand side expression evaluating to a location within
     * a previously defined XML data tree.
     */
    private String location;

    /**
     * The source location where the new source data for this location exists.
     */
    private String src;

    /**
     * Expression evaluating to the new value of the variable.
     */
    private String expr;

    /**
     * The assign element body value, or the value from external {@link #getSrc()}, may be null.
     */
    private ParsedValue assignValue;

    /**
     * Constructs a new instance.
     */
    public Assign() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {
        final EnterableState parentState = getParentEnterableState();
        final Context ctx = exctx.getContext(parentState);
        final Evaluator evaluator = exctx.getEvaluator();
        Object data = null;
        if (expr != null) {
            data = evaluator.eval(ctx, expr);
        }
        else if (assignValue != null) {
            data = evaluator.cloneData(assignValue.getValue());
        }

        evaluator.evalAssign(ctx, location, data);
        if (exctx.getAppLog().isDebugEnabled()) {
            exctx.getAppLog().debug("<assign>: '" + location + "' updated");
        }
        // TODO: introduce a optional 'trace.change' setting or something alike to enable .change events,
       //        but don't do this by default as it can interfere with transitions not expecting such events
        /*
            TriggerEvent ev = new TriggerEvent(location + ".change", TriggerEvent.CHANGE_EVENT);
            exctx.getInternalIOProcessor().addEvent(ev);
        */
    }

    /**
     * Gets the expr that will evaluate to the new value.
     *
     * @return the expr.
     */
    public String getExpr() {
        return expr;
    }

    /**
     * Gets the location for a previously defined XML data tree.
     *
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the assign value
     *
     * @return The assign value
     */
    @Override
    public final ParsedValue getParsedValue() {
        return assignValue;
    }

    /**
     * Gets the source where the new XML instance for this location exists.
     *
     * @return the source.
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the expr that will evaluate to the new value.
     *
     * @param expr The expr to set.
     */
    public void setExpr(final String expr) {
        this.expr = expr;
    }

    /**
     * Sets the location for a previously defined XML data tree.
     *
     * @param location The location.
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Sets the assign value
     *
     * @param assignValue The assign value
     */
    @Override
    public final void setParsedValue(final ParsedValue assignValue) {
        this.assignValue = assignValue;
    }
    /**
     * Sets the source where the new XML instance for this location exists.
     *
     * @param src The source.
     */
    public void setSrc(final String src) {
        this.src = src;
    }
}
