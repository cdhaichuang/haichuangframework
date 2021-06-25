package pro.haichuang.framework.base.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 简单分页查询
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ApiModel("简单分页查询")
public class SimplePageRequest extends PageRequest {
    private static final long serialVersionUID = -148062733244961091L;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SimplePageRequest{" +
                "keyword='" + keyword + '\'' +
                "} " + super.toString();
    }
}
