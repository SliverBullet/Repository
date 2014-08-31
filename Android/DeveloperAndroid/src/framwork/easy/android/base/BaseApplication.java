package framwork.easy.android.base;

import android.app.Application;

/**
 * @author duzhihua
 *
 */
public abstract class BaseApplication extends Application {
	private ActivityStackManager mActivityStackManager;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public ActivityStackManager getActivityStackManager() {
		if (mActivityStackManager == null) {
			mActivityStackManager = ActivityStackManager.getInstance();
		}
		return mActivityStackManager;
	}

	public void exitApplication() {
		getActivityStackManager().exitApplication(false);
	}

	public void exitApplication(boolean isbackground) {
		getActivityStackManager().exitApplication(isbackground);
	}
}
