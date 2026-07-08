package common.lll44556.top.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "基础业务 Bean")
public class BaseBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键 ID")
    private String id;

    @Schema(title = "创建时间")
    private Long createdTime;

    @Schema(title = "更新时间")
    private Long updatedTime;

    @Schema(title = "操作者")
    private String operator;

    @Schema(title = "有效性")
    private Integer valid;
}
