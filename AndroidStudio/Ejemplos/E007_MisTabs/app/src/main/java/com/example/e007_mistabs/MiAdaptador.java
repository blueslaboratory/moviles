package com.example.e007_mistabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


// UT4.3 Tabs
public class MiAdaptador  extends FragmentStateAdapter {
    public MiAdaptador(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: CalcFragment calcFragment = new CalcFragment();
                return calcFragment;
            case 1: ToastFragment toastFragment = new ToastFragment();
                return toastFragment;
            case 2: AlertFragment alertFragment = new AlertFragment();
                return alertFragment;
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public CharSequence getTabTitle(int position){
        switch (position){
            case 0: return "CALCULADORA";
            case 1: return "TOAST";
            case 2: return "ALERT";
            default: return "PRUEBA";
        }
    }
}
