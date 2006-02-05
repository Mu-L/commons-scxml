/*
 *
 *   Copyright 2006 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.commons.scxml.env.jexl;

import java.util.regex.Pattern;

import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.scxml.Context;
import org.apache.commons.scxml.Evaluator;
import org.apache.commons.scxml.SCXMLExpressionException;

/**
 * Evaluator implementation enabling use of JEXL expressions in
 * SCXML documents.
 *
 */
public class JexlEvaluator implements Evaluator {

    /** Error message if evaluation context is not a JexlContext. */
    private static final String ERR_CTX_TYPE = "Error evaluating JEXL "
        + "expression, Context must be a org.apache.commons.jexl.JexlContext";

    /** Pattern for recognizing the SCXML In() special predicate. */
    private static Pattern inFct = Pattern.compile("In\\(");

    /** Constructor. */
    public JexlEvaluator() {
        super();
    }

    /**
     * Evaluate an expression.
     *
     * @param ctx variable context
     * @param expr expression
     * @return a result of the evaluation
     * @throws SCXMLExpressionException For a malformed expression
     * @see Evaluator#eval(Context, String)
     */
    public Object eval(final Context ctx, final String expr)
    throws SCXMLExpressionException {
        JexlContext jexlCtx = null;
        if (ctx instanceof JexlContext) {
            jexlCtx = (JexlContext) ctx;
        } else {
            throw new SCXMLExpressionException(ERR_CTX_TYPE);
        }
        Expression exp = null;
        try {
            String evalExpr = inFct.matcher(expr).
                replaceAll("_builtin.isMember(_ALL_STATES, ");
            exp = ExpressionFactory.createExpression(evalExpr);
            return exp.evaluate(jexlCtx);
        } catch (Exception e) {
            throw new SCXMLExpressionException(e);
        }
    }

    /**
     * Create a new child context.
     *
     * @param parent parent context
     * @return new child context
     * @see Evaluator#newContext(Context)
     */
    public Context newContext(final Context parent) {
        return new org.apache.commons.scxml.env.jexl.JexlContext(parent);
    }

    /**
     * @see Evaluator#evalCond(Context, String)
     */
    public Boolean evalCond(final Context ctx, final String expr)
    throws SCXMLExpressionException {
        JexlContext jexlCtx = null;
        if (ctx instanceof JexlContext) {
            jexlCtx = (JexlContext) ctx;
        } else {
            throw new SCXMLExpressionException(ERR_CTX_TYPE);
        }
        Expression exp = null;
        try {
            String evalExpr = inFct.matcher(expr).
                replaceAll("_builtin.isMember(_ALL_STATES, ");
            exp = ExpressionFactory.createExpression(evalExpr);
            return (Boolean) exp.evaluate(jexlCtx);
        } catch (Exception e) {
            throw new SCXMLExpressionException(e);
        }
    }

}

