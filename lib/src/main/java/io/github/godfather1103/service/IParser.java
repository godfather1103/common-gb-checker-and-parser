package io.github.godfather1103.service;

import java.util.List;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2025</p>
 * <p>Company:      <a href="https://github.com/godfather1103">https://github.com/godfather1103</a></p>
 * 类描述：
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2025/8/4 11:08
 * @since 1.0
 */
public interface IParser extends IKey {

    /**
     * 从内容中解析对象<BR>
     *
     * @param content 内容
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:40
     */
    default List<String> parse(String content) {
        return parse(content, true);
    }

    /**
     * 从内容中解析对象<BR>
     *
     * @param content  内容
     * @param distinct 是否排重
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:40
     */
    List<String> parse(String content, Boolean distinct);

    /**
     * 对数据进行脱敏<BR>
     *
     * @param content 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2025/8/4 11:08
     */
    String desensitizationData(String content);

    /**
     * desensitizationData<BR>
     *
     * @param content  参数
     * @param notCheck 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2025/8/4 11:35
     */
    String desensitizationData(String content, boolean notCheck);
}
