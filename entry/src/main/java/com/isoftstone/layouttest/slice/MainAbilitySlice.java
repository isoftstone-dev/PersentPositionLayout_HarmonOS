package com.isoftstone.layouttest.slice;

import com.isoftstone.layouttest.ResourceTable;
import com.isoftstone.precentpositionlayout.PrecentPositionLayout;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.LayoutScatter;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 解析xml文件
        PrecentPositionLayout precentPositionLayout = (PrecentPositionLayout)LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_ability_main, null, false);

        // 调用precentPositionLayout 的Autosize方法
        precentPositionLayout.AutoSize();

        super.setUIContent(precentPositionLayout);

        //super.setUIContent(ResourceTable.Layout_ability_main);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
