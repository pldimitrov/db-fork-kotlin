// Copyright (c) 2011, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

package com.google.dart.compiler.backend.js.ast;

import org.jetbrains.annotations.NotNull;

/**
 * Used in object literals to specify property values by name.
 */
public class JsPropertyInitializer extends JsNodeImpl {
    private JsExpression labelExpr;
    private JsExpression valueExpr;

    public JsPropertyInitializer(@NotNull JsExpression labelExpr) {
        this.labelExpr = labelExpr;
    }

    public JsPropertyInitializer(@NotNull JsExpression labelExpr, @NotNull JsExpression valueExpr) {
        this(labelExpr);
        this.valueExpr = valueExpr;
    }

    public JsExpression getLabelExpr() {
        return labelExpr;
    }

    public JsExpression getValueExpr() {
        return valueExpr;
    }

    public boolean hasSideEffects() {
        return labelExpr.hasSideEffects() || valueExpr.hasSideEffects();
    }

    public void setValueExpr(@NotNull JsExpression valueExpr) {
        this.valueExpr = valueExpr;
    }

    @Override
    public void accept(JsVisitor v, JsContext context) {
        v.visitPropertyInitializer(this, context);
    }

    @Override
    public void acceptChildren(JsVisitor visitor, JsContext context) {
        labelExpr = visitor.accept(labelExpr);
        valueExpr = visitor.accept(valueExpr);
    }
}
