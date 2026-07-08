package common.lll44556.top.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求参数。
 *
 * @param <T> 查询条件类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationReqVO<T> {

    /**
     * 分页参数。
     */
    private Pagination pagination;

    /**
     * 查询条件。
     */
    private T params;

    /**
     * 是否分页，false 时由业务层使用 Pageable.unpaged() 查询全部。
     */
    private Boolean whetherPagination;
}
