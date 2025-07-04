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
package org.apache.commons.scxml2.env;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StopWatchTest {

    private StopWatch stopWatch;

    /**
     * Sets up instance variables required by this test case.
     */
    @BeforeEach
    public void setUp() throws Exception {
        stopWatch = new StopWatch();
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @AfterEach
    public void tearDown() {
        stopWatch = null;
    }

    @Test
    void testStopWatch() {
        Assertions.assertEquals("reset", stopWatch.getCurrentState());
        stopWatch.fireEvent(StopWatch.EVENT_START);
        Assertions.assertEquals("running", stopWatch.getCurrentState());
        stopWatch.fireEvent(StopWatch.EVENT_SPLIT);
        Assertions.assertEquals("paused", stopWatch.getCurrentState());
        stopWatch.fireEvent(StopWatch.EVENT_UNSPLIT);
        Assertions.assertEquals("running", stopWatch.getCurrentState());
        stopWatch.fireEvent(StopWatch.EVENT_STOP);
        Assertions.assertEquals("stopped", stopWatch.getCurrentState());
        stopWatch.fireEvent(StopWatch.EVENT_RESET);
        Assertions.assertEquals("reset", stopWatch.getCurrentState());
    }

}

