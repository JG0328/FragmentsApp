package com.pucmm.fragmentsapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = getView().findViewById(R.id.list);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            // Getting selected item
            String item = adapterView.getAdapter().getItem(i).toString();

            Bundle bundle = new Bundle();
            // Getting definition, by selected item
            bundle.putString("def", getDefinition(item));

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            DefinitionFragment definitionFragment = new DefinitionFragment();
            definitionFragment.setArguments(bundle);

            // For landscape, we replace the frame for definition, in portrait, we just replace the main frame layout
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                fragmentTransaction.replace(R.id.frame_definition, definitionFragment);
            else
                fragmentTransaction.replace(R.id.frame_main, definitionFragment);

            fragmentTransaction.addToBackStack(null); // Allows to go "back"
            fragmentTransaction.commit();
        });
    }


    private String getDefinition(String option) {
        String value = "";

        switch (option.toLowerCase()) {
            case "activity":
                value = "Activity: es un componente crucial de una aplicación de Android, y la forma en que se inician y combinan las actividades es una parte fundamental del modelo de aplicación de la plataforma.";
                break;
            case "intent":
                value = "Intent: Un Intent es un objeto de mensajería que puede utilizar para solicitar una acción de otro componente de la aplicación (Activities, Services, Broadcast receivers, Content providers).";
                break;
            case "listview":
                value = "ListView: es una vista que agrupa varios elementos y los muestra en una lista de desplazamiento vertical. Los elementos de la lista se insertan automáticamente en la lista mediante un adaptador que extrae contenido de una fuente como una matriz o una base de datos. Es uno de los componentes de interfaz de usuario básicos y más utilizados de Android.";
                break;
            case "adapter":
                value = "Adapter: crea un puente entre los componentes de la interfaz de usuario y la fuente de datos que completan los datos en el componente de la interfaz de usuario. El adaptador retiene los datos y envía los datos a la vista del adaptador, la vista puede tomar los datos de la vista del adaptador y muestra los datos en diferentes vistas como ruleta, vista de lista, vista de cuadrícula, etc.";
                break;
            case "fragment":
                value = "Fragment: representa un comportamiento o una parte de la interfaz de usuario en a FragmentActivity. Puede combinar varios fragmentos en una sola actividad para crear una interfaz de usuario de varios paneles y reutilizar un fragmento en varias actividades.";
                break;
        }

        return value;
    }
}