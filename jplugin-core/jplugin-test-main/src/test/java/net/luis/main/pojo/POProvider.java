package net.luis.main.pojo;
import java.util.List;

import net.jplugin.core.das.hib.api.IPersistObjDefinition;
/**
 *
 * @author: LiuHang
 * @version ����ʱ�䣺2015-3-18 ����05:28:51
 **/

public class POProvider implements IPersistObjDefinition{

	/* (non-Javadoc)
	 * @see net.luis.plugin.das.api.IPersistObjDefinition#getClasses()
	 */
	public Class[] getClasses() {
		return new Class[]{UserBean.class,BeanWithTypes.class};
	}

}
