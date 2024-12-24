package io.github.godfather1103.utils;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

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

    private static final Map<String, BaseCheckAndParse> PARSER_MAP = new HashMap<>(0);

    static {
        log.info("开始通过SPI加载BaseCheckAndParse.class");
        ServiceLoader<BaseCheckAndParse> loader = ServiceLoader.load(BaseCheckAndParse.class);
        for (BaseCheckAndParse checkAndParse : loader) {
            log.info("加载了[{}][{}]", checkAndParse.desc(), checkAndParse.getClass().getName());
            PARSER_MAP.put(checkAndParse.key(), checkAndParse);
        }
        if (!PARSER_MAP.containsKey(Constant.CHECK_PERSON)
                && PARSER_MAP.containsKey(Constant.CHECK_ZJHM)) {
            PARSER_MAP.put(Constant.CHECK_PERSON, PARSER_MAP.get(Constant.CHECK_ZJHM));
        }
        if (!PARSER_MAP.containsKey(Constant.CHECK_CAR)
                && PARSER_MAP.containsKey(Constant.CHECK_JDCHP)) {
            PARSER_MAP.put(Constant.CHECK_CAR, PARSER_MAP.get(Constant.CHECK_JDCHP));
        }
        if (!PARSER_MAP.containsKey(Constant.CHECK_PHONE)
                && PARSER_MAP.containsKey(Constant.CHECK_ZGDLSHJ)) {
            PARSER_MAP.put(Constant.CHECK_PHONE, PARSER_MAP.get(Constant.CHECK_ZGDLSHJ));
        }
    }

    /**
     * findOpt<BR>
     *
     * @param key 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 14:08
     */
    public static Optional<BaseCheckAndParse> findOpt(String key) {
        return Optional.ofNullable(PARSER_MAP.get(key));
    }

    /**
     * find<BR>
     *
     * @param key 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 17:14
     */
    public static BaseCheckAndParse find(String key) {
        return find(key, true);
    }

    /**
     * find<BR>
     *
     * @param key       参数
     * @param needExist 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 17:14
     */
    public static BaseCheckAndParse find(String key, Boolean needExist) {
        Optional<BaseCheckAndParse> opt = findOpt(key);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            if (needExist) {
                throw new IllegalArgumentException("未找到校验器" + key);
            } else {
                return null;
            }
        }
    }

    /**
     * findAllMap<BR>
     *
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/24 10:06
     */
    public static Map<String, BaseCheckAndParse> findAllMap() {
        return PARSER_MAP;
    }

    /**
     * findAll<BR>
     *
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/24 10:05
     */
    public static List<BaseCheckAndParse> findAll() {
        return PARSER_MAP.values().stream().distinct().collect(Collectors.toList());
    }
}
