package milai.meishipintu.com.faxianlite.presenter;

import android.content.pm.PackageInfo;

/**
 * Created by pc on 2016/4/26.
 */
public interface Presenter extends BasicPresenter {
    void loadInstallApps();
    void launchApp(PackageInfo packageInfo);
}
