package com.isoftstone.precentpositionlayout;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.PositionLayout;
import ohos.agp.utils.Point;
import ohos.agp.window.service.Display;
import ohos.agp.window.service.DisplayManager;
import ohos.app.Context;

import java.util.Optional;

public class PrecentPositionLayout extends PositionLayout {
    public PrecentPositionLayout(Context context) {
        super(context);
    }

    public PrecentPositionLayout(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    public PrecentPositionLayout(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }


    /* 调整各组件的大小，按照百分比调整
     *  将原来组件的起始位置，宽度和高度都视作相对于整个屏幕的百分比值，然后根据屏幕的分辨率转换为实际的像素值。
     *  注：考虑到使用0-100配置百分比的话，范围太小不够精确，因此配置范围设置为0-1000，
     *  比如当前屏幕是1920 * 1060, 某个组件的宽度和高度配置的是200，则表示改组件的宽和高都占整个屏幕的20%。
     *  因此，调整后改组件的实际大小为384 * 212.
     */
    public void AutoSize() {
        // 获取屏幕分辨率
        Optional<Display> display = DisplayManager.getInstance().getDefaultDisplay(this.getContext());
        Point pt = new Point();
        display.get().getSize(pt);

        // 去除上面标题栏和下面导航栏的高度
        pt.modify(pt.getPointX(), pt.getPointY() - 160);

        // 调增各组件的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            Component component = getComponentAt(i);
            ComponentContainer.LayoutConfig config = component.getLayoutConfig();
            component.setLeft(config.getMarginLeft() * pt.getPointXToInt() / 1000);
            component.setTop(config.getMarginTop() * pt.getPointYToInt() / 1000);
            component.setWidth(config.width * pt.getPointXToInt()  / 1000);
            component.setHeight(config.height * pt.getPointYToInt()  / 1000);
        }
    }
}
