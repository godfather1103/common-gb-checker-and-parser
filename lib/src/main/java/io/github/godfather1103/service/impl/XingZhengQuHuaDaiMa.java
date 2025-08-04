package io.github.godfather1103.service.impl;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：中华人民共和国行政区划代码(GB/T 2260-2007)
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 12:06
 * @since 1.0
 */
public class XingZhengQuHuaDaiMa extends BaseCheckAndParse {
    @Override
    public String regex() {
        return "(?<!\\d)(1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-6]|71|8[1-3])\\d{4}(?![\\dxX])";
    }

    @Override
    protected Boolean doGuoBiaoCheck(String content) {
        return true;
    }

    @Override
    public String desensitizationRegex() {
        return "(\\d{4})\\d{2}";
    }

    @Override
    public String desensitizationFormat() {
        return "$1**";
    }

    @Override
    public String desc() {
        return "中华人民共和国行政区划代码(GB/T 2260-2007)";
    }

    @Override
    public String key() {
        return Constant.CHECK_XZQH;
    }
}
