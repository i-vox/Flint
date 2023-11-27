package com.vox.exposure.flint_core.hook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.Scope;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class FragmentLancet {
    @Proxy("onCreateView")
    @TargetClass(value = "androidx.fragment.app.Fragment", scope = Scope.LEAF)
    public View hookOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) Origin.call();
        //todo Build level flag
        return view;
    }

}
