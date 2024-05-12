package com.aula.sqlitetest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_list.view.*

class ListData (nameList: List<Detail>, private var ctx: Context, private val callbacks: (Int) -> Unit): RecyclerView.Adapter<ListData.ViewHolder>() {

    private var nameList: List<Detail> = ArrayList<Detail>()
    init {
        this.nameList = nameList
    }

    //  viewholder is created from the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.detail_list, parent, false)
        return ViewHolder(view)
    }

    // item of the viewholder is modified
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = nameList[position]

        holder.name.text = name.nome
        if(position % 2 == 0) holder.name.setBackgroundColor(Color.BLUE)
        else holder.name.setBackgroundColor(Color.WHITE)
        holder.name.setOnClickListener {
            val intent = Intent(ctx, NameActivity::class.java)
            intent.putExtra("edit", true)
            intent.putExtra("position", name.id)
            ctx.startActivity(intent)
        }
        holder.btn.setOnClickListener {
            val databaseHandler = DatabaseHandler(ctx)
            databaseHandler.deletePessoa(name.id)
            callbacks(position)
        }
    }

    // Returns the number of items in the nameList
    override fun getItemCount(): Int {
        return nameList.size
    }

    // Here is the creation of the viewholder items
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.tvAdpNome
        var btn: Button = view.btnAdpDel
    }
}