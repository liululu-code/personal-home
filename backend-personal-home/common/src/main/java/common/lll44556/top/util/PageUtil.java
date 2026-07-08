package common.lll44556.top.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 分页工具。
 */
public class PageUtil {

    private static final int DEFAULT_PAGE = 1;

    private static final int DEFAULT_SIZE = 10;

    private PageUtil() {
    }

    /**
     * 将前端 1-based 页码转换为 Spring Data 0-based Pageable。
     *
     * @param page 前端页码
     * @param size 每页数量
     * @return Spring Data 分页对象
     */
    public static Pageable checkPage(Integer page, Integer size) {
        int safePage = page == null || page < DEFAULT_PAGE ? DEFAULT_PAGE : page;
        int safeSize = size == null || size < 1 ? DEFAULT_SIZE : size;
        return PageRequest.of(safePage - 1, safeSize);
    }
}
