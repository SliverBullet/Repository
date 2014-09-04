package framwork.easy.android.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;
import framwork.easy.android.task.TaskPool;
import framwork.easy.android.task.TaskQueue;
import framwork.easy.android.utils.AppUtil;

/**
 * @author duzhihua
 *
 */
public abstract class BaseApplication extends Application {
	private ActivityStackManager mActivityStackManager;
	private static ExecutorService mExecutorService;
	private static TaskPool mTaskPool;
	private static TaskQueue mTaskQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		setmExecutorService(Executors
				.newFixedThreadPool(AppUtil.getNumCores() * 5));
		setmTaskPool(TaskPool.getInstance());
		setmTaskQueue(TaskQueue.getInstance());
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

	/**
	 * @return the mExecutorService
	 */
	public static ExecutorService getExecutorService() {
		return mExecutorService;
	}

	/**
	 * @param mExecutorService
	 *            the mExecutorService to set
	 */
	private void setmExecutorService(ExecutorService mExecutorService) {
		BaseApplication.mExecutorService = mExecutorService;
	}

	/**
	 * @return the mTaskPool
	 */
	public static TaskPool getTaskPool() {
		return mTaskPool;
	}

	/**
	 * @param mTaskPool
	 *            the mTaskPool to set
	 */
	private void setmTaskPool(TaskPool mTaskPool) {
		BaseApplication.mTaskPool = mTaskPool;
	}

	/**
	 * @return the mTaskQueue
	 */
	public static TaskQueue getTaskQueue() {
		return mTaskQueue;
	}

	/**
	 * @param mTaskQueue
	 *            the mTaskQueue to set
	 */
	private void setmTaskQueue(TaskQueue mTaskQueue) {
		BaseApplication.mTaskQueue = mTaskQueue;
	}
}
