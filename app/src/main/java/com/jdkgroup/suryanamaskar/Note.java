package com.jdkgroup.suryanamaskar;

public class Note
{
   /* public static void replaceFragment(Activity activity, Fragment fragment, Bundle bundle) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager fragmentManager = ((BaseDrawerActivity) activity).getSupportFragmentManager();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.replace(R.id.frameLayout, fragment, backStateName);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }*/
}
