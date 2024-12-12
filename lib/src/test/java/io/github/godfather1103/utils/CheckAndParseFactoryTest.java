package io.github.godfather1103.utils;

import io.github.godfather1103.constant.Constant;
import io.github.godfather1103.service.BaseCheckAndParse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckAndParseFactoryTest {

    @Test
    void testZjhm() {
        BaseCheckAndParse checkAndParse = CheckAndParseFactory.find(Constant.CHECK_ZJHM)
                .orElseThrow(RuntimeException::new);
        // 妈祖身份证
        assertTrue(
                checkAndParse.check("350321096003237001"),
                "妈祖身份证校验出错了"
        );
        assertTrue(
                checkAndParse.check("440524188001010014"),
                "身份校验出错"
        );
        assertTrue(
                checkAndParse.check("11010519491231002X"),
                "身份校验出错"
        );
        assertTrue(
                checkAndParse.check("11010519491231002x"),
                "身份校验出错"
        );
        assertFalse(
                checkAndParse.check("110105194912310022"),
                "这个应该是错误的证件号码"
        );

        System.out.println(checkAndParse.parse("说来很多人怕是不知道！妈祖不仅能够乘飞机、坐动车，人家还有身份证！\n" +
                "据南方都市报报道，妈祖的身份证号是350321096003237001，姓名为林默。\n"));
    }

    @Test
    void testHaoPai(){
        BaseCheckAndParse checkAndParse = CheckAndParseFactory.find(Constant.CHECK_JDCHP)
                .orElseThrow(RuntimeException::new);

        assertTrue(
                checkAndParse.check("川A10TF1"),
                "机动车川A10TF1"
        );

        assertTrue(
                checkAndParse.check("川A10TF11"),
                "机动车川A10TF11"
        );

        assertTrue(
                checkAndParse.check("川A10TF学"),
                "机动车川A10TF学"
        );

        assertTrue(
                checkAndParse.check("川A10TF警"),
                "机动车川A10TF警"
        );
        assertFalse(
                checkAndParse.check("川A10TF警1"),
                "这个应该是错误的车牌"
        );
    }

}