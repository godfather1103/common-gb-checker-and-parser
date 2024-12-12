package io.github.godfather1103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 数据符合基础正则之后进行国标校验<BR>
     *
     * @param content 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:54
     */
    public abstract Boolean doGuoBiaoCheck(String content);

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
}
