package com.parting_soul.coordincatorlayoutdemo.base;

/**
 * @author parting_soul
 * @date 2019/3/4
 */
public class ItemBean {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NORMAL_TWO = 1;

    private String name;
    private int type;

    public ItemBean(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
