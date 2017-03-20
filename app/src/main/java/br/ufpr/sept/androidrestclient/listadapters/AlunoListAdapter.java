package br.ufpr.sept.androidrestclient.listadapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufpr.sept.androidrestclient.R;
import br.ufpr.sept.androidrestclient.domain.Aluno;

/**
 * Created by Eric on 19/03/2017.
 */

public class AlunoListAdapter  extends BaseAdapter{

    private Context context;
    private List<Aluno> alunos;
    public AlunoListAdapter(Context context, List alunos){
        this.context = context;
        this.alunos = alunos;
    }
    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getMatricula();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_alunos_list, null);
        Aluno aluno = alunos.get(position);
        ((TextView)view.findViewById(R.id.matricula)).setText(String.format("%05d", aluno.getMatricula()));
        ((TextView)view.findViewById(R.id.nome)).setText(aluno.getNome());
        ((TextView)view.findViewById(R.id.endereco)).setText(aluno.getEnderecos().get(0).toString());
        return view;
    }
}
