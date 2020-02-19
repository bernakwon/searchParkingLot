package com.berna.global.common.object;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public class PagingParam {


    @ApiModelProperty(
            example = "1",
            required = true,
            value = "필수값. 몇번째 페이지를 요청할 것인지를 나타냅니다. 이 값은 반드시 1 이상이어야 합니다.",
            hidden = false
    )
    protected int page;
    @ApiModelProperty(
            example = "10",
            required = true,
            value = "필수값. 한 페이지당 불러올 데이터의 갯수를 나타냅니다. 10이면 10개의 항목이 1개의 페이지에 나올 수 있는 최대 갯수로 보고 리스트를 가져옵니다.",
            hidden = false
    )
    protected int pageSize;

    @ApiModelProperty(
            example = "DESC",
            required = true,
            value = "정렬 정보",
            hidden = false
    )
    protected Sort sort;
}
