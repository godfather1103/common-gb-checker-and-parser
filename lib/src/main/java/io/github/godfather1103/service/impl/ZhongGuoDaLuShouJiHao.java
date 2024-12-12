package io.github.godfather1103.service.impl;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：中国大陆手机号
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 16:19
 * @since 1.0
 */
public class ZhongGuoDaLuShouJiHao extends BaseCheckAndParse {
    @Override
    public String regex() {
        return "((00|\\+)?86)?1[3-9]\\d{9}";
    }

    @Override
    protected Boolean doGuoBiaoCheck(String content) {
        return content.matches(regex());
    }

    @Override
    public String desc() {
        return "中国大陆手机号";
    }

    @Override
    public String key() {
        return Constant.CHECK_ZGDLSHJ;
    }
}
