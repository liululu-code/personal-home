package common.lll44556.top.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 当前页码，按前端 1-based 约定返回。
     */
    private Integer page;

    /**
     * 每页数量。
     */
    private Integer pageSize;

    /**
     * 总记录数。
     */
    private Long total;

    /**
     * 当前页记录。
     */
    private List<T> records = new ArrayList<>();
}
