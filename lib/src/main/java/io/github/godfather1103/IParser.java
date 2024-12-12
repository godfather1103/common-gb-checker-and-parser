package io.github.godfather1103;

import java.util.List;

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
}
