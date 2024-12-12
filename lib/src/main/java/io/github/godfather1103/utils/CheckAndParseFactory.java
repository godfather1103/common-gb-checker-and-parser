package io.github.godfather1103.utils;

import io.github.godfather1103.service.BaseCheckAndParse;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 14:11
 * @since 1.0
 */
@Slf4j
public class CheckAndParseFactory {

    private static final List<BaseCheckAndParse> PARSER_LIST = new ArrayList<>(0);

    static {
        log.info("开始通过SPI加载BaseCheckAndParse.class");
        ServiceLoader<BaseCheckAndParse> loader = ServiceLoader.load(BaseCheckAndParse.class);
        for (BaseCheckAndParse checkAndParse : loader) {
            log.info("加载了[{}]", checkAndParse.getClass().getName());
            PARSER_LIST.add(checkAndParse);
        }
    }

    /**
     * find<BR>
     *
     * @param key 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 14:08
     */
    public static Optional<BaseCheckAndParse> find(String key) {
        return PARSER_LIST.stream()
                .filter(it -> Objects.equals(it.key(), key))
                .findFirst();
    }
}
