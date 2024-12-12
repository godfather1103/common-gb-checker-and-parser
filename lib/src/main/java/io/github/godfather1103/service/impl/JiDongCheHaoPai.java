package io.github.godfather1103.service.impl;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：机动车号牌（GA 36-2018）
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 16:19
 * @since 1.0
 */
public class JiDongCheHaoPai extends BaseCheckAndParse {
    @Override
    public String regex() {
        return "([京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新]([A-HJ-NP-Z])(([A-HJ-NP-Z])|\\d){4,5}([A-HJ-NP-Z\\d挂学警港澳])(?![A-HJ-NP-Z\\d挂学警港澳]))|([京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新]((\\d{5}领(?![领]))|(领\\d{5}(?![\\d]))))|((?<!\\d)\\d{6}使)";
    }

    @Override
    protected Boolean doGuoBiaoCheck(String content) {
        return content.matches(regex());
    }

    @Override
    public String desc() {
        return "机动车号牌（GA 36-2018）";
    }

    @Override
    public String key() {
        return Constant.CHECK_JDCHP;
    }
}
