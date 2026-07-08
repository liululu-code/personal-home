package ${paginationReqVoPackageName};

import ${paginationPackageName}.${paginationClassName};
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 分页请求参数
 * @author: ${author}
 * @date: ${date}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${paginationReqVoClassName}<T> {

    /**
     * 分页参数。
     */
    private ${paginationClassName} pagination;

    /**
     * 查询条件。
     */
    private T params;

    /**
     * 是否分页，false 时使用 Pageable.unpaged() 查询全部。
     */
    private Boolean whetherPagination;
}
