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
package org.apache.commons.scxml2.issues;

import java.util.Set;

import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.SCXMLTestHelper;
import org.apache.commons.scxml2.model.EnterableState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for issue 62.
 * FIXED
 */
class Issue62Test {

    private void fragmenttest(final SCXMLExecutor exec) throws Exception {
        final Set<EnterableState> currentStates = exec.getStatus().getStates();
        Assertions.assertEquals(1, currentStates.size());
        Assertions.assertEquals("s1", currentStates.iterator().next().getId());
        SCXMLTestHelper.assertPostTriggerState(exec, "foo", "e1.1.1");
        SCXMLTestHelper.assertPostTriggerState(exec, "bar", "e1.1.2");
        SCXMLTestHelper.assertPostTriggerState(exec, "baz", "s3");
        Assertions.assertTrue(exec.getStatus().isFinal());
    }

    @Test
    void test01issue62() throws Exception {
        final SCXMLExecutor exec = SCXMLTestHelper.getExecutor("org/apache/commons/scxml2/issues/issue62-01.xml");
        exec.go();
        final Set<EnterableState> currentStates = exec.getStatus().getStates();
        Assertions.assertEquals(1, currentStates.size());
        Assertions.assertEquals("s1.1", currentStates.iterator().next().getId());
        SCXMLTestHelper.assertPostTriggerState(exec, "foo", "s1.1");
    }

    @Test
    void test02issue62() throws Exception {
        final SCXMLExecutor exec = SCXMLTestHelper.getExecutor("org/apache/commons/scxml2/issues/issue62-02.xml");
        exec.go();
        fragmenttest(exec);
    }

    @Test
    void test03issue62() throws Exception {
        final SCXMLExecutor exec = SCXMLTestHelper.getExecutor("org/apache/commons/scxml2/issues/issue62-03.xml");
        exec.go();
        fragmenttest(exec);
    }
}

