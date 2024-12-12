package io.github.godfather1103;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2024</p>
 * <p>Company:      https://github.com/godfather1103</p>
 * 类描述：
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * @version 1.0
 * @date 创建时间：2024/12/12 10:38
 * @since 1.0
 */
public interface IChecker extends IKey {

    /**
     * 检测数据是否符合标准<BR>
     *
     * @param content 参数
     * @return 结果
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * @date 创建时间：2024/12/12 10:38
     */
    boolean check(String content);
}
