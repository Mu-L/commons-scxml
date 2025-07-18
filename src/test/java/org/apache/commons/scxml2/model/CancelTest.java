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

import org.apache.commons.scxml2.EventBuilder;
import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.SCXMLTestHelper;
import org.apache.commons.scxml2.TriggerEvent;
import org.apache.commons.scxml2.env.SimpleDispatcher;
import org.junit.jupiter.api.Test;

class CancelTest {

    @Test
    void testCancelBySendId() throws Exception {
        final SCXML scxml = SCXMLTestHelper.parse("org/apache/commons/scxml2/model/cancel-test-01.xml");
        final SCXMLExecutor exec = SCXMLTestHelper.getExecutor(scxml, null, new SimpleDispatcher());
        exec.go();
        final TriggerEvent te = new EventBuilder("event.foo", TriggerEvent.SIGNAL_EVENT).build();
        SCXMLTestHelper.fireEvent(exec, te);
        Thread.sleep(3000);
        exec.triggerEvents();
        SCXMLTestHelper.assertState(exec, "twenty");
    }

    @Test
    void testCancelBySendIdExpr() throws Exception {
        final SCXML scxml = SCXMLTestHelper.parse("org/apache/commons/scxml2/model/cancel-test-02.xml");
        final SCXMLExecutor exec = SCXMLTestHelper.getExecutor(scxml, null, new SimpleDispatcher());
        exec.go();
        final TriggerEvent te = new EventBuilder("event.foo", TriggerEvent.SIGNAL_EVENT).build();
        SCXMLTestHelper.fireEvent(exec, te);
        Thread.sleep(3000);
        exec.triggerEvents();
        SCXMLTestHelper.assertState(exec, "twenty");
    }
}
