package common.lll44556.top.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {

    /**
     * 当前页码，前端按 1-based 传入。
     */
    private Integer page;

    /**
     * 每页数量。
     */
    private Integer size;
}
