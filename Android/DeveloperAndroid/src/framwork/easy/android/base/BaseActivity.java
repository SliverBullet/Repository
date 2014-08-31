package framwork.easy.android.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import framwork.easy.android.utils.PrintUtil;
import framwork.easy.android.viewinject.ViewInjector;
import framwork.easy.android.viewinject.view.annotation.ContentView;

/**
 * @author duzhihua
 *
 */
public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		onSetContentView();
		setContentView();
		getBaseApplication().getActivityStackManager().addActivity(this);
		PrintUtil.squarePrint("onCreate--->" + this.getClass().getName());
		ViewInjector.inject(this);
		onAfterOnCreate();
	}

	/**
	 * 通过注解自动导入布局文件
	 */
	private void setContentView() {
		Class<?> activity = this.getClass();
		ContentView contentview = activity.getAnnotation(ContentView.class);
		if (contentview != null) {
			setContentView(contentview.value());
		}
	}

	/**
	 * 在此使用{@link #setContentView(int layoutResID)}指定布局文件, 若此处不使用
	 * {@link #setContentView(int layoutResID)}
	 * ,则需要在Activity上面加@ContentView(ResId)自动导入布局,两种必须选一种
	 */
	protected abstract void onSetContentView();

	protected abstract void onAfterOnCreate();

	public BaseApplication getBaseApplication() {
		return (BaseApplication) getApplication();
	}

	@Override
	protected void onDestroy() {
		getBaseApplication().getActivityStackManager().removeActivity(this);
		PrintUtil.squarePrint("onDestroy--->" + this.getClass().getName());
		super.onDestroy();
	}
}
