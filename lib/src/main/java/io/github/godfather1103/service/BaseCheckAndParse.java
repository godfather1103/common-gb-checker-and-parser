package io.github.godfather1103.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 11:39
 * @since 1.0
 */
@Slf4j
public abstract class BaseCheckAndParse implements IChecker, IParser {

    private Pattern pattern;

    /**
     * 基础正则表达式<BR>
     *
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:43
     */
    public abstract String regex();

    /**
     * desensitizationRegex<BR>
     *
     * @return 结果
     * @author 作者: chu.chuanbao E-mail: chu.chuanbao@trs.com.cn
     * @date 创建时间：2025/8/4 11:14
     */
    public String desensitizationRegex() {
        return regex();
    }

    /**
     * desensitizationFormat<BR>
     *
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2025/8/4 11:17
     */
    public String desensitizationFormat() {
        return "*";
    }

    /**
     * 数据符合基础正则之后进行国标校验<BR>
     *
     * @param content 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:54
     */
    protected abstract Boolean doGuoBiaoCheck(String content);

    /**
     * getPattern<BR>
     *
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:49
     */
    public Pattern getPattern() {
        if (Objects.isNull(pattern)) {
            pattern = Pattern.compile(regex());
        }
        return pattern;
    }

    @Override
    public final List<String> parse(String content, Boolean distinct) {
        if (Objects.isNull(content) || content.isEmpty()) {
            return Collections.emptyList();
        }
        Matcher matcher = getPattern().matcher(content);
        List<String> result = new ArrayList<>(0);
        while (matcher.find()) {
            String tmp = matcher.group();
            if (check(tmp)) {
                if (!result.contains(tmp) || !distinct) {
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    @Override
    public final boolean check(String content) {
        // 为空时不满足标准
        if (Objects.isNull(content) || content.isEmpty()) {
            return false;
        }
        // 不符合基础正则表达式
        if (!content.matches(regex())) {
            return false;
        }
        // 进行国标校验
        return doGuoBiaoCheck(content);
    }

    @Override
    public String desensitizationData(String content) {
        return desensitizationData(content, false);
    }

    @Override
    public String desensitizationData(String content, boolean notCheck) {
        if (!notCheck && !check(content)) {
            throw new RuntimeException("待脱敏的数据异常");
        }
        if (Objects.isNull(content)) {
            return null;
        }
        return content.replaceFirst(desensitizationRegex(), desensitizationFormat());
    }
}
