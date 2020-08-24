package ci.ahmadfauzirahman.sianokapp;

import android.graphics.Typeface;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ci.ahmadfauzirahman.sianokapp.fragment.AkunFragment;
import ci.ahmadfauzirahman.sianokapp.fragment.AntrianFragment;
import ci.ahmadfauzirahman.sianokapp.fragment.NotifikasiFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    @StringRes

//    String font = Typeface.createFromAsset(getAssets())
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                NotifikasiFragment notifikasiFragment = new NotifikasiFragment();
                return notifikasiFragment;
            case 1:
                AntrianFragment antrianFragment = new AntrianFragment();
                return antrianFragment;
            case 2:
                AkunFragment akunFragment = new AkunFragment();
                return akunFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return mNoOfTabs;

    }
}
