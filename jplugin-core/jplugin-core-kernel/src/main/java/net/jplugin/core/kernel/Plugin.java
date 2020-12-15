package net.jplugin.core.kernel;

import net.jplugin.common.kits.StringKit;
import net.jplugin.common.kits.http.filter.IHttpClientFilter;
import net.jplugin.core.kernel.api.AbstractPlugin;
import net.jplugin.core.kernel.api.AutoBindExtensionManager;
import net.jplugin.core.kernel.api.BindBean;
import net.jplugin.core.kernel.api.BindStartup;
import net.jplugin.core.kernel.api.CoreServicePriority;
import net.jplugin.core.kernel.api.Extension;
import net.jplugin.core.kernel.api.Beans;
import net.jplugin.core.kernel.api.ExtensionKernelHelper;
import net.jplugin.core.kernel.api.ExtensionPoint;
import net.jplugin.core.kernel.api.IAnnoForAttrHandler;
import net.jplugin.core.kernel.api.IExeRunnableInitFilter;
import net.jplugin.core.kernel.api.IExecutorFilter;
import net.jplugin.core.kernel.api.IPluginEnvInitFilter;
import net.jplugin.core.kernel.api.IScheduledExecutionFilter;
import net.jplugin.core.kernel.api.IStartup;
import net.jplugin.core.kernel.api.PluginAnnotation;
import net.jplugin.core.kernel.api.PluginEnvirement;
import net.jplugin.core.kernel.impl.AnnoForBeanHandler;
import net.jplugin.core.kernel.impl.AnnoForExtensionMapHandler;
import net.jplugin.core.kernel.impl.AnnoForExtensionsHandler;
import net.jplugin.core.kernel.impl.HttpClientFilterManager;
import net.jplugin.core.kernel.kits.ExecutorKitFilterManager;
import net.jplugin.core.kernel.kits.RunnableInitFilterManager;
import net.jplugin.core.kernel.kits.scheduled.ScheduledFilterManager;

/**
 *
 * @author: LiuHang
 * @version 创建时间：2015-2-15 下午01:07:22
 **/

public class Plugin extends AbstractPlugin{

	public static final String EP_STARTUP = "EP_STARTUP";
	public static final String EP_ANNO_FOR_ATTR = "EP_ANNO_FOR_ATTR";
	public static final String EP_EXECUTOR_FILTER = "EP_EXECUTOR_FILTER";
	public static final String EP_HTTP_CLIENT_FILTER = "EP_HTTP_CLIENT_FILTER";
	public static final String EP_EXE_RUN_INIT_FILTER = "EP_EXE_RUN_INIT_FILTER";
	public static final String EP_PLUGIN_ENV_INIT_FILTER = "EP_PLUGIN_ENV_INIT_FILTER";
	public static final String EP_EXE_SCHEDULED_FILTER = "EP_EXE_SCHEDULED_FILTER";
	public static final String EP_BEAN = "EP_BEAN";

	static{
		AutoBindExtensionManager.INSTANCE.addBindExtensionHandler((p)->{
			ExtensionKernelHelper.autoBindExtension(p, "");
		});
		
		AutoBindExtensionManager.INSTANCE.addBindExtensionTransformer(BindStartup.class, (plugin,clazz,anno)->{
			BindStartup bsAnno = (BindStartup) anno;
			plugin.addExtension(Extension.create(EP_STARTUP, clazz));
			
			if (StringKit.isNotNull(bsAnno.id())) {
				Beans.setLastId(bsAnno.id());
			}
		});
		AutoBindExtensionManager.INSTANCE.addBindExtensionTransformer(BindBean.class, (plugin,clazz,anno)->{
			BindBean bsAnno = (BindBean) anno;
			String id = bsAnno.id();
			if (StringKit.isNull(id)) {
				throw new RuntimeException("[id] attribute for BindBean must not null");
			}
			ExtensionKernelHelper.addBeanExtension(plugin, id, clazz);
		});
	}
	
	public Plugin(){
		addExtensionPoint(ExtensionPoint.create(EP_STARTUP, IStartup.class));
		addExtensionPoint(ExtensionPoint.create(EP_ANNO_FOR_ATTR, IAnnoForAttrHandler.class));
		addExtensionPoint(ExtensionPoint.create(EP_EXECUTOR_FILTER,IExecutorFilter.class));
		addExtensionPoint(ExtensionPoint.create(EP_EXE_RUN_INIT_FILTER,IExeRunnableInitFilter.class));
		addExtensionPoint(ExtensionPoint.create(EP_HTTP_CLIENT_FILTER,IHttpClientFilter.class));	
		addExtensionPoint(ExtensionPoint.create(EP_PLUGIN_ENV_INIT_FILTER,IPluginEnvInitFilter.class));	
		addExtensionPoint(ExtensionPoint.create(EP_EXE_SCHEDULED_FILTER,IScheduledExecutionFilter.class));
		addExtensionPoint(ExtensionPoint.create(EP_BEAN, Object.class,true));
		
		ExtensionKernelHelper.addAnnoAttrHandlerExtension(this, AnnoForExtensionsHandler.class);
		ExtensionKernelHelper.addAnnoAttrHandlerExtension(this, AnnoForExtensionMapHandler.class);
		ExtensionKernelHelper.addAnnoAttrHandlerExtension(this, AnnoForBeanHandler.class);
	}
	/* (non-Javadoc)
	 * @see net.luis.common.kernel.api.AbstractPlugin#getPrivority()
	 */
	@Override
	public int getPrivority() {
		return CoreServicePriority.KERNEL;
	}

	/* (non-Javadoc)
	 * @see net.luis.common.kernel.api.IPlugin#init()
	 */
	public void onCreateServices() {
		HttpClientFilterManager.init();
		ExecutorKitFilterManager.init();
		RunnableInitFilterManager.init();
		PluginEnvirement.getInstance().initStartFilter();
		ScheduledFilterManager.init();
	}
	
	@Override
	public void init() {
		
	}
	@Override
	public boolean searchClazzForExtension() {
		return false;
	}

}
