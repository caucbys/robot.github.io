package com.gdyunst.coderobot.transfer;

/**
 * 业务层返回数据包装类;
 * @author Jieyq
 * @date 2016年9月13日 下午2:36:11
 */
public class Result {
	private Integer sta;	// 状态  0失败  1成功  默认失败;
	private Integer level;	// 等级;
	private String  msg;	// 消息提示;
	private String code;	// 错误代码;
	private Long total;     // 总记录数;
	private Object data;	// 数据;
	
	public static final int RESULT_STA_0=0;  // 失败;
	public static final int RESULT_STA_1=1;  // 成功;
	public static final int RESULT_SIG_0=0;  // 不需要提示  默认;
	public static final int RESULT_SIG_1=1;  // 需要提示;
	public static final int RESULT_CODE_200=200;  //
	public static final int RESULT_CODE_201=201;  //
	public static final int RESULT_CODE_400=400;  //
	public static final int RESULT_CODE_401=401;  //没有权限
	public static final int RESULT_CODE_403=403;  //
	public static final int RESULT_CODE_404=404;  //
	public static final int RESULT_CODE_407=407;  //
	public static final int RESULT_CODE_408=408;  //未登录
	public static final int RESULT_CODE_500=500;  //
	
	// Getter and Setter;
	public Long getTotal()
	{
		return total;
	}
	
	public void setTotal(Long total)
	{
		this.total = total;
	}
	
	public Integer getSta() {
		if(sta==null){
			sta=RESULT_STA_0;
		}
		return sta;
	}
	
	public void setSta(Integer sta) {
		if(sta==null){
			sta=RESULT_STA_0;
		}
		this.sta = sta;
	}

	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Result [sta=" + sta + ", level=" + level + ", msg=" + msg + ", code=" + code + ", page="
			+ ", " + ", data=" + data + "]";
	}
	
}
