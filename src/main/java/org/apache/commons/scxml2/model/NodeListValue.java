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

import java.util.List;

import org.w3c.dom.Node;

/**
 * List of XML DOM Nodes {@link ParsedValue} implementation
 */
public class NodeListValue implements ParsedValue {

    /**
     * The list of nodes
     */
    private final List<Node> nodeList;

    public NodeListValue(final List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public final ValueType getType() {
        return ValueType.NODE_LIST;
    }

    @Override
    public final List<Node> getValue() {
        return nodeList;
    }
}
