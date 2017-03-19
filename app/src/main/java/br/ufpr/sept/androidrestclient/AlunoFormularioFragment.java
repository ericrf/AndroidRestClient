package br.ufpr.sept.androidrestclient;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Endereco;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.EnderecoBuscaCEP;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.BuscaCEPRequestTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlunoFormularioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlunoFormularioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlunoFormularioFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public AlunoFormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_aluno_formulario, container, false);
        ((Button)view.findViewById(R.id.buscarCEP)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = ((EditText)view.findViewById(R.id.cep)).getText().toString();
                new BuscaCEPRequestTask(){
                    @Override
                    protected void onPostExecute(EnderecoBuscaCEP endereco) {
                        if(endereco != null){
                            ((EditText)view.findViewById(R.id.logradouro)).setText(endereco.getLogradouro());
                            ((EditText)view.findViewById(R.id.bairro)).setText(endereco.getBairro());
                            ((EditText)view.findViewById(R.id.cidade)).setText(endereco.getCidade());
                            ((EditText)view.findViewById(R.id.estado)).setText(endereco.getEstado());

                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "CEP não encontrado", Toast.LENGTH_LONG);
                        }
                    }
                }.execute(cep);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
