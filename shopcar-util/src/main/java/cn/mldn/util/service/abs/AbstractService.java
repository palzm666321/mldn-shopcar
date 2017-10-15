package cn.mldn.util.service.abs;

public abstract class AbstractService {
	/**
	 * 如果现在设置的字符串不是空则表示需要进行模糊查询处理
	 * @param str 要验证的字符串
	 * @return 如果为空返回false，如果不为空返回true（表示需要进行模糊查询）
	 */
	public boolean isLikeSearch(String str) {
		if (str == null || "".equals(str)) {
			return false ;
		}
		return true ;
	}
}
