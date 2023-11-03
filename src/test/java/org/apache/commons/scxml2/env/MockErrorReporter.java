/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.scxml2.env;

import org.apache.commons.scxml2.ErrorReporter;

public class MockErrorReporter implements ErrorReporter {

    private String errCode;
    private String errDetail;
    private Object errCtx;

    public String getErrCode() {
        return errCode;
    }

    public Object getErrCtx() {
        return errCtx;
    }

    public String getErrDetail() {
        return errDetail;
    }

    @Override
    public void onError( final String errCode, final String errDetail, final Object errCtx ) {
        this.errCode = errCode;
        this.errDetail = errDetail;
        this.errCtx = errCtx;
    }

    public void setErrCode( final String errCode ) {
        this.errCode = errCode;
    }

    public void setErrCtx( final Object errCtx ) {
        this.errCtx = errCtx;
    }

    public void setErrDetail( final String errDetail ) {
        this.errDetail = errDetail;
    }

}
