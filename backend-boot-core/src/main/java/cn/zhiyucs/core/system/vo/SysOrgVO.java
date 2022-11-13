package cn.zhiyucs.core.system.vo;

import cn.zhiyucs.core.utils.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 机构列表
 *
 * @author zhiyu1998
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "机构")
public class SysOrgVO extends TreeNode<SysOrgVO> {

	@Schema(description = "机构名称", required = true)
	@NotBlank(message = "机构名称不能为空")
	private String name;

	@Schema(description = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;

	@Schema(description = "创建时间")
	private Date createTime;

	@Schema(description = "上级名称")
	private String parentName;

}