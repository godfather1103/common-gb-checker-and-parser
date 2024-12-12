package io.github.godfather1103.service.impl;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;
import io.github.godfather1103.utils.CheckAndParseFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：公民身份号码校验跟提取工具(GB 11643-1999)
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 11:39
 * @since 1.0
 */
public class GongMinShenFenHaoMa extends BaseCheckAndParse {

    private static final List<String> CHECK_SUM_NUM = Arrays.asList(
            "1",
            "0",
            "X",
            "9",
            "8",
            "7",
            "6",
            "5",
            "4",
            "3",
            "2"
    );

    private static final List<Integer> CHECK_SUM_WEIGHT = Arrays.asList(
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1
    );

    @Override
    public String regex() {
        return "\\d{10}(0[1-9]|1[0-2])[0-3]\\d{4}[\\dXx]";
    }

    /**
     * isValidDate<BR>
     *
     * @param csrq 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 14:31
     */
    private Boolean isValidDate(String csrq) {
        if (Objects.isNull(csrq) || csrq.isEmpty()) {
            return false;
        }
        ParsePosition p = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置解析日期格式是否严格解析日期
        sdf.setLenient(false);
        sdf.parse(csrq, p);
        return Objects.equals(p.getIndex(), csrq.length());
    }

    /**
     * 计算校验和<BR>
     *
     * @param content 参数
     * @return 结果
     * @author 作者: chu.chuanbao E-mail: chu.chuanbao@trs.com.cn
     * @date 创建时间：2024/12/12 14:34
     */
    private Boolean checksum(String content) {
        if (Objects.isNull(content) || content.isEmpty()) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < content.length() - 1; i++) {
            sum += CHECK_SUM_WEIGHT.get(i) * Integer.parseInt(String.valueOf(content.charAt(i)));
        }
        return CHECK_SUM_NUM.get(sum % 11).equalsIgnoreCase(String.valueOf(content.charAt(17)));
    }

    @Override
    protected Boolean doGuoBiaoCheck(String content) {
        String xzqh = content.substring(0, 6);
        // 行政区划不符合标准
        if (!CheckAndParseFactory.find(Constant.CHECK_XZQH)
                .orElseThrow(() -> new IllegalArgumentException("未找到校验器"))
                .check(xzqh)) {
            return false;
        }
        String csrq = content.substring(6, 14);
        // 出生日期不符合标准
        if (!isValidDate(csrq)) {
            return false;
        }
        // 计算校验和
        return checksum(content);
    }

    @Override
    public String desc() {
        return "GB 11643-1999";
    }

    @Override
    public String key() {
        return Constant.CHECK_ZJHM;
    }
}
