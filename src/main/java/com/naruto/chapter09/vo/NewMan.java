package com.naruto.chapter09.vo;

import java.util.Optional;

public class NewMan {

    /**
     * 不是每个男人都有女神,所以可能为空
     * 使用 Optional 包装,但是女神都有名字,所以Godness的name没必要使用Optional包装
     */
    private Optional<Godness> godness = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
