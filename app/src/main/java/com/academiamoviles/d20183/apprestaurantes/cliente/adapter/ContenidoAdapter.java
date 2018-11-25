package com.academiamoviles.d20183.apprestaurantes.cliente.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContenidoAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentos = new ArrayList<>();

    public ContenidoAdapter(FragmentManager fm) {
        super(fm);
    }

    public void agregarPagina(Fragment pagina) {
        fragmentos.add(pagina);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }
}
