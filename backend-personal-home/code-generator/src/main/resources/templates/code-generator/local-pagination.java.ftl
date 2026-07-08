package ${paginationPackageName};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 分页参数
 * @author: ${author}
 * @date: ${date}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${paginationClassName} {

    /**
     * 当前页码，前端按 1-based 传入。
     */
    private Integer page;

    /**
     * 每页数量。
     */
    private Integer size;
}
